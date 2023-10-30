package com.trust.gestion.entities;

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

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "tenant_bill_paid")
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class TenantBillPaidEntity {
    @Id
    @Size(max = 20)
    @Column(name = "id", nullable = false, length = 20)
    private String id;

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "tenant_id", nullable = false)
    private TenantEntity tenant;

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "bill_id", nullable = false)
    private TenantBillingEntity bill;

    @Size(max = 20)
    @NotNull
    @Column(name = "amount_paid", nullable = false, length = 20)
    private BigDecimal amountPaid;

    @NotNull
    @Column(name = "payment_date", nullable = false)
    private LocalDate paymentDate;

    @Size(max = 20)
    @Column(name = "payment_method", length = 20)
    private String paymentMethod;

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
