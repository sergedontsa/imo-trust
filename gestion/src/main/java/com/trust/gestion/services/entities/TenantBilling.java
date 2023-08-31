/*
 * Copyright (c) 2023.  - Serge G. Dontsa
 */

package com.trust.gestion.services.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "tenant_billing")
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class TenantBilling {
    @Id
    @Size(max = 20)
    @Column(name = "id", nullable = false, length = 20)
    private String id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "tenant_id", nullable = false)
    private TenantEntity tenant;

    @Size(max = 20)
    @NotNull
    @Column(name = "billing_type", nullable = false, length = 20)
    private String billingType;

    @Size(max = 20)
    @NotNull
    @Column(name = "billing_amount", nullable = false, length = 20)
    private String billingAmount;

    @NotNull
    @Column(name = "bill_from", nullable = false)
    private LocalDate billFrom;

    @NotNull
    @Column(name = "bill_to", nullable = false)
    private LocalDate billTo;

    @NotNull
    @Column(name = "bill_due_date", nullable = false)
    private LocalDate billDueDate;

    @Size(max = 250)
    @NotNull
    @Column(name = "description", nullable = false, length = 250)
    private String description;

    @NotNull
    @Column(name = "registration_date", nullable = false)
    private Instant registrationDate;

    @NotNull
    @Column(name = "last_updated", nullable = false)
    private Instant lastUpdated;

}
