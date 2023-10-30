/*
 * Copyright (c) 2023.  - Serge G. Dontsa
 */

package com.trust.gestion.resources;

import com.trust.gestion.enums.Gender;
import com.trust.gestion.exception.validators.OwnerDefaultValidator;
import jakarta.validation.constraints.NotNull;
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
@OwnerDefaultValidator
public class OwnerResource {
    private String id;
    @NotNull
    private String firstName;
    private String middleName;
    @NotNull
    private String lastName;
    @NotNull
    Gender gender;
    private String buildingId;
    private Instant registrationDate;
    private Instant lastUpdated;
    }
