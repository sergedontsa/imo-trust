/*
 * Copyright (c) 2023.  - Serge G. Dontsa
 */

package com.trust.gestion.services.resources;

import com.trust.gestion.enums.Gender;
import com.trust.gestion.exception.validators.OwnerDefaultValidator;
import jakarta.validation.constraints.NotNull;
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
@OwnerDefaultValidator
public class OwnerResource {
    String id;
    @NotNull
    String firstName;
    String middleName;
    @NotNull
    String lastName;
    @NotNull
    Gender gender;
    Instant registrationDate;
    Instant lastUpdated;
    List<OwnerAddressResource> address = new ArrayList<>();
    List<OwnerContactInformationRessource> contacts = new ArrayList<>();
    List<OwnerInformationResource> information = new ArrayList<>();
    List<OwnerIdentificationRessource> identifications = new ArrayList<>();


}
