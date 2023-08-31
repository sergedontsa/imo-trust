/*
 * Copyright (c) 2023.  - Serge G. Dontsa
 */

package com.trust.gestion.services.resources;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.Value;

import java.time.Instant;
@Value
@Getter
@Setter
@Builder(toBuilder = true)
public class OwnerAddressResource {
    Integer id;
    String civicNumber;
    String streetName;
    String aptNumber;
    String city;
    String province;
    String postalCode;
    String country;
    String type;
    Instant registrationDate;
    Instant lastUpdated;
}
