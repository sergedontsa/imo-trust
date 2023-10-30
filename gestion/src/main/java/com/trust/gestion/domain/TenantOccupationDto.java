/*
 * Copyright (c) 2023.  - Serge G. Dontsa
 */

package com.trust.gestion.domain;


import com.trust.gestion.entities.TenantOccupationEntity;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;
import java.time.Instant;

/**
 * DTO for {@link TenantOccupationEntity}
 */
@Getter
@Setter
@Builder(toBuilder = true)
@AllArgsConstructor
public class TenantOccupationDto implements Serializable {
    @Size(max = 20)
    String id;
    @NotNull
    @Size(max = 20)
    String occupation;
    @NotNull
    private String employerName;
    @NotNull
    private String employeeAddress;
    @NotNull
    private String employeePhone;

    @NotNull
    @Size(max = 250)
    String description;
    @NotNull
    Instant registrationDate;
    @NotNull
    Instant lastUpdated;
}
