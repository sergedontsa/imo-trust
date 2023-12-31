package com.trust.gestion.resources.reponse;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.trust.gestion.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@Builder(toBuilder = true)
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApartmentResponse {
    private String id;
    private String apartmentNumber;
    private Integer numBedrooms;
    private BigDecimal squareFootage;
    private BigDecimal rentAmount;
    private String description;
    private Integer occupant;
    private Status status;
    private List<TenantResponse> tenants;
    private BuildingResponse building;
}
