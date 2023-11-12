package com.trust.gestion.resources;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder(toBuilder = true)
@AllArgsConstructor
public class TelephoneResource {
    private String entityId;
    private String areaCode;
    private String number;
    private String carrier;
    private String country;

}
