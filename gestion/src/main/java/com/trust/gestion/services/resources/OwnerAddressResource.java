/*
 * Copyright (c) 2023.  - Serge G. Dontsa
 */

package com.trust.gestion.services.resources;

import com.trust.gestion.enums.AddressType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class OwnerAddressResource {
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
