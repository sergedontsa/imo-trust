/*
 * Copyright (c) 2023.  - Serge G. Dontsa
 */

package com.trust.gestion.services.domain;


import com.trust.gestion.enums.Status;
import com.trust.gestion.services.entities.TenantEntity;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDate;

/**
 * DTO for {@link TenantEntity}
 */
@Getter
@Setter
@Builder(toBuilder = true)
@AllArgsConstructor
public class TenantDto implements Serializable {
    @Size(max = 20)
    String id;
    @NotNull
    @Size(max = 20)
    String firstName;
    @NotNull
    @Size(max = 20)
    String middleName;
    @NotNull
    @Size(max = 20)
    String lastName;
    @NotNull
    @Size(max = 20)
    String gender;
    @NotNull
    @Size(max = 20)
    Status status;
    @NotNull
    LocalDate dateOfBirth;
    @NotNull
    @Size(max = 20)
    String countryOfOrigin;
    @NotNull
    @Size(max = 20)
    String cityOfOrigin;
    @NotNull
    @Size(max = 250)
    String description;
    @NotNull
    @Size(max = 20)
    String contactType;
    @NotNull
    Instant registrationDate;
    @NotNull
    Instant lastUpdated;
}
