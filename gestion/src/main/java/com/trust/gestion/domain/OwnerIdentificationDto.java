/*
 * Copyright (c) 2023.  - Serge G. Dontsa
 */

package com.trust.gestion.domain;



import com.trust.gestion.entities.OwnerIdentificationEntity;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDate;

/**
 * DTO for {@link OwnerIdentificationEntity}
 */
@Getter
@Setter
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class OwnerIdentificationDto implements Serializable {
    Integer id;
    @NotNull
    @Size(max = 50)
    String typeId;
    @NotNull
    @Size(max = 50)
    String issueCountry;
    @NotNull
    LocalDate validFrom;
    @NotNull
    LocalDate validTo;
    @Size(max = 250)
    String description;
    @NotNull
    Instant registrationDate;
    @NotNull
    Instant lastUpdated;
}
