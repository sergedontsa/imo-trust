/*
 * Copyright (c) 2023.  - Serge G. Dontsa
 */

package com.trust.gestion.services.domain;


import com.trust.gestion.services.entities.TenantBilling;
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
 * DTO for {@link TenantBilling}
 */
@Getter
@Setter
@Builder(toBuilder = true)
@AllArgsConstructor
public class TenantBillingDto implements Serializable {
    @Size(max = 20)
    String id;
    @NotNull
    @Size(max = 20)
    String billingType;
    @NotNull
    @Size(max = 20)
    String billingAmount;
    @NotNull
    LocalDate billFrom;
    @NotNull
    LocalDate billTo;
    @NotNull
    LocalDate billDueDate;
    @NotNull
    @Size(max = 250)
    String description;
    @NotNull
    Instant registrationDate;
    @NotNull
    Instant lastUpdated;
}
