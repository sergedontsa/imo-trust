/*
 * Copyright (c) 2023.  - Serge G. Dontsa
 */

package com.trust.gestion.services.resources;

import com.trust.gestion.enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class OwnerResource {
    String id;
    String firstName;
    String middleName;
    String lastName;
    Gender gender;
    Instant registrationDate;
    Instant lastUpdated;
    List<OwnerAddressResource> address = new ArrayList<>();
    List<OwnerContactInformationRessource> contacts = new ArrayList<>();
    List<OwnerInformationResource> information = new ArrayList<>();
    List<OwnerIdentificationRessource> identifications = new ArrayList<>();


}
