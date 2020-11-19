package net.securustech.esp.streams.model.output;

import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
public class VisitTabletEvent {
    Target target;
    Payload payload;

}
