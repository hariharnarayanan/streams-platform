package net.securustech.esp.streams.model.output;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerOrSite implements Target {
    private String customerId;
    private String inmateId;
}
