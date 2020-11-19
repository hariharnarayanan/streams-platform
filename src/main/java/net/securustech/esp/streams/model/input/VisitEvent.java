package net.securustech.esp.streams.model.input;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
public class VisitEvent {

    private Long id;
    private String event;
    private String visitType;
    private String contractId;
    private String siteId;
    private String inmateLocationName;
    private String inmateTerminalId;
    private String inmateTerminalName;
    private String inmateDeviceType;
    private String visitorLocationName;
    private String visitorTerminalId;
    private String visitorTerminalName;
    private String startTimeUtc;
    private String endTimeUtc;
    private String siteTimeZone;
    private Integer durationMinutes;
    private Boolean recordedFlag;
    private String statusCd;
    private String inmateId;
    private String inmateName;
    private String visitorName;


}
