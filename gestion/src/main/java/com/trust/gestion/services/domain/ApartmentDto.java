/*
 * Copyright (c) 2023.  - Serge G. Dontsa
 */

package com.trust.gestion.services.domain;



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
    Integer id;
    @Size(max = 20)
    String apartmentNumber;
    Integer numBedrooms;
    BigDecimal squareFootage;
    BigDecimal rentAmount;
    @Size(max = 250)
    String description;
    @Size(max = 20)
    String availabilityStatus;
    @NotNull
    Instant registrationDate;
    @NotNull
    Instant lastUpdated;
}
