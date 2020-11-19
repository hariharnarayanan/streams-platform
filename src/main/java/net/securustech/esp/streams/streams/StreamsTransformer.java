package net.securustech.esp.streams.streams;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.securustech.esp.streams.model.input.VisitEvent;
import net.securustech.esp.streams.model.output.CustomerOrSite;
import net.securustech.esp.streams.model.output.Payload;
import net.securustech.esp.streams.model.output.VisitTabletEvent;
import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.kstream.KStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Service;


@Service
@EnableBinding(KafkaStreamsBinding.class)
public class StreamsTransformer {

    private static Logger LOGGER = LoggerFactory.getLogger(StreamsTransformer.class);

    @StreamListener(KafkaStreamsBinding.KAFKA_IN)
    @SendTo({KafkaStreamsBinding.KAFKA_OUT})
    public KStream<String, VisitTabletEvent> transform(KStream<String, String> inputPayload) throws Exception {
        inputPayload.peek((key, value) -> LOGGER.info("key>>>" + key + " value " + value));
        KStream<String, VisitTabletEvent> targetPayload = null;
        targetPayload = inputPayload.filter(this::removeInvalidRecords).map(this::processVisits);
        return targetPayload;
    }

    public Boolean removeInvalidRecords(String key, String value) {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        try {
            VisitEvent visitEvent = objectMapper.readValue(value, VisitEvent.class);
            return visitEvent.getInmateId() != null;
        } catch (Exception e) {
            LOGGER.error("@@@@Error while streaming key >>> " + key, e);
            return false;
        }
    }

    public KeyValue<String, VisitTabletEvent> processVisits(String key, String value) {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        VisitTabletEvent visitTabletEvent = new VisitTabletEvent();
        try {
            VisitEvent visitEvent = objectMapper.readValue(value,VisitEvent.class);
            LOGGER.info(visitEvent.toString());
            CustomerOrSite target = new CustomerOrSite();
            target.setCustomerId(visitEvent.getContractId());
            target.setInmateId(visitEvent.getInmateId());
            visitTabletEvent.setTarget(target);

            Payload payload = new Payload();
            payload.setAction("updateAppointment");
            payload.setApp("com.securus.unity.svc");
            payload.setData(visitEvent);
            visitTabletEvent.setPayload(payload);
        } catch (Exception e) {
            LOGGER.error("@@@@Error while streaming key >>> " + key, e);
        }
        return KeyValue.pair(key, visitTabletEvent);
    }
}