package com.trust.gestion.resources.reponse;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder(toBuilder = true)
@AllArgsConstructor
public class TelephoneResponse {
    private Integer id;
    private String entityId;
    private String areaCode;
    private String number;
    private String carrier;
    private String country;
}
