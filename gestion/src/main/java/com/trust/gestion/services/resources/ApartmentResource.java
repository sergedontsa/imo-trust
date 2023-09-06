/*
 * Copyright (c) 2023.  - Serge G. Dontsa
 */

package com.trust.gestion.services.resources;


import com.trust.gestion.enums.Status;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class ApartmentResource {
    Integer id;
    @Size(max = 20)
    String apartmentNumber;
    Integer numBedrooms;
    BigDecimal squareFootage;
    BigDecimal rentAmount;
    @Size(max = 250)
    String description;
    @Size(max = 20)
    Status status;
}
