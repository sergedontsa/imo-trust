/*
 * Copyright (c) 2023.  - Serge G. Dontsa
 */

package com.trust.gestion.services.domain;


import com.trust.gestion.enums.Status;
import com.trust.gestion.services.entities.ApartmentEntity;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;

/**
 * DTO for {@link ApartmentEntity}
 */
@Getter
@Setter
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class ApartmentDto implements Serializable {
    private String id;
    @Size(max = 20)
    private String apartmentNumber;
    private Integer numBedrooms;
    private BigDecimal squareFootage;
    private BigDecimal rentAmount;
    @Size(max = 250)
    private String description;
    private Integer occupant;

    @NotNull
    private Instant registrationDate;
    @NotNull
    private Instant lastUpdated;
    private Status status;

}
