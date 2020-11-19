package net.securustech.esp.streams.streams;

import org.apache.kafka.streams.kstream.KStream;
import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;

interface KafkaStreamsBinding {

    /**
     * Input channel name.
     */
    String KAFKA_IN = "kafkaIn";

    /**
     * Output channel name.
     */
    String KAFKA_OUT = "kafkaOut";

    @Input(KafkaStreamsBinding.KAFKA_IN)
    KStream<String,String> kafkaIn();

    @Output(KafkaStreamsBinding.KAFKA_OUT)
    KStream<String,String> kafkaOut();
}