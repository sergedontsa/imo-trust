package com.trust.gestion.resources.reponse;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder(toBuilder = true)
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TelephoneResponse {
    private Integer id;
    private String entityId;
    private String areaCode;
    private String number;
    private String carrier;
    private String country;
}
