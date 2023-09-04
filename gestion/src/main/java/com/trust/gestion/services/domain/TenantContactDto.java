/*
 * Copyright (c) 2023.  - Serge G. Dontsa
 */

package com.trust.gestion.services.domain;


import com.trust.gestion.services.entities.TenantContactEntity;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;
import java.time.Instant;

/**
 * DTO for {@link TenantContactEntity}
 */
@Getter
@Setter
@Builder(toBuilder = true)
@AllArgsConstructor
public class TenantContactDto implements Serializable {
    @Size(max = 20)
    String id;
    @NotNull
    @Size(max = 20)
    String phoneNumber1;
    @Size(max = 20)
    String phoneNumber2;
    @NotNull
    @Size(max = 250)
    String description;
    @NotNull
    @Size(max = 20)
    String email;
    @NotNull
    Instant registrationDate;
    @NotNull
    Instant lastUpdated;
}
