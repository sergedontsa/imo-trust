/*
 * Copyright (c) 2023.  - Serge G. Dontsa
 */

package com.trust.gestion.services.resources;



import com.trust.gestion.services.entities.OwnerIdentificationEntity;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDate;

/**
 * DTO for {@link OwnerIdentificationEntity}
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class OwnerIdentificationRessource implements Serializable {
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
