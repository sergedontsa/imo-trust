/*
 * Copyright (c) 2023.  - Serge G. Dontsa
 */

package com.trust.gestion.resources;



import com.trust.gestion.entities.OwnerInformationEntity;
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
 * DTO for {@link OwnerInformationEntity}
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class OwnerInformationResource implements Serializable {
    Integer id;
    @NotNull
    LocalDate dateOfBirth;
    @NotNull
    @Size(max = 50)
    String nationality;
    @NotNull
    @Size(max = 50)
    String countryOfBirth;
    @NotNull
    Instant registrationDate;
    @NotNull
    Instant lastUpdated;
}
