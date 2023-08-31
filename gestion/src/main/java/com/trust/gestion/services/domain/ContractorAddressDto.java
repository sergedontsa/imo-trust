/*
 * Copyright (c) 2023.  - Serge G. Dontsa
 */

package com.trust.gestion.services.domain;


import com.trust.gestion.services.entities.ContractorAddress;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.Instant;

/**
 * DTO for {@link ContractorAddress}
 */
@Getter
@Setter
@Builder(toBuilder = true)
@AllArgsConstructor
public class ContractorAddressDto implements Serializable {
    @NotNull
    Integer id;
    @NotNull
    @Size(max = 10)
    String civicNumber;
    @NotNull
    @Size(max = 50)
    String streetName;
    @Size(max = 10)
    String aptNumber;
    @Size(max = 50)
    String city;
    @Size(max = 50)
    String province;
    @Size(max = 10)
    String postalCode;
    @Size(max = 50)
    String country;
    @NotNull
    ContractorDto contractor;
    @NotNull
    @Size(max = 20)
    String type;
    @NotNull
    Instant registrationDate;
    @NotNull
    Instant lastUpdated;
}
