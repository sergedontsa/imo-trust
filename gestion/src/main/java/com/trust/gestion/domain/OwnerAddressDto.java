/*
 * Copyright (c) 2023.  - Serge G. Dontsa
 */

package com.trust.gestion.domain;


import com.trust.gestion.enums.AddressType;
import com.trust.gestion.entities.OwnerAddressEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.Instant;

/**
 * DTO for {@link OwnerAddressEntity}
 */
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
@Getter
@Setter
public class OwnerAddressDto implements Serializable {

    Integer id;
    String civicNumber;
    String streetName;
    String aptNumber;
    String city;
    String province;
    String postalCode;
    String country;
    AddressType type;
    Instant registrationDate;
    Instant lastUpdated;
}
