/*
 * Copyright (c) 2023.  - Serge G. Dontsa
 */

package com.trust.gestion.services.domain;


import com.trust.gestion.enums.AddressType;
import com.trust.gestion.services.entities.OwnerAddressEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.Value;

import java.io.Serializable;
import java.time.Instant;

/**
 * DTO for {@link OwnerAddressEntity}
 */
@Value
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
