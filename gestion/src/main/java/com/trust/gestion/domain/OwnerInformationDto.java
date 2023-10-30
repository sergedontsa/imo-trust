/*
 * Copyright (c) 2023.  - Serge G. Dontsa
 */

package com.trust.gestion.domain;



import com.trust.gestion.entities.OwnerInformationEntity;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDate;

/**
 * DTO for {@link OwnerInformationEntity}
 */
@Getter
@Setter
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class OwnerInformationDto implements Serializable {
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
