package net.securustech.esp.streams.model.output;

import lombok.Getter;
import lombok.Setter;
import net.securustech.esp.streams.model.input.VisitEvent;

@Setter
@Getter
public class Payload {
    String action;
    VisitEvent data;
    String app;
}
