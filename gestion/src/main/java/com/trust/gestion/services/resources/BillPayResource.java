package com.trust.gestion.services.resources;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class BillPayResource {
    private String bilId;
    private String tenantId;
    private String apartmentId;
    private BigDecimal amountPaid;
    private String paymentMethod;
    private String description;
}
