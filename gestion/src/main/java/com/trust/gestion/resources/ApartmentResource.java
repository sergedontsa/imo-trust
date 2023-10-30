/*
 * Copyright (c) 2023.  - Serge G. Dontsa
 */

package com.trust.gestion.resources;


import com.trust.gestion.enums.Status;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class ApartmentResource {
    private String id;
    @Size(max = 20)
    private String apartmentNumber;
    private Integer numBedrooms;
    private BigDecimal squareFootage;
    private BigDecimal rentAmount;
    private Integer occupant;
    @Size(max = 250)
    private String description;
    @Size(max = 20)
    private Status status;
    private String buildingId;
}
