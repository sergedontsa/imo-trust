/*
 * Copyright (c) 2023.  - Serge G. Dontsa
 */

package com.trust.gestion.services.resources;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder(toBuilder = true)
public class ContractorResource {
    @Size(max = 50)
    String id;
    @NotNull
    @Size(max = 20)
    String firstName;
    @Size(max = 20)
    String middleName;
    @NotNull
    @Size(max = 20)
    String lastName;
    @NotNull
    @Size(max = 255)
    String specialization;
    @NotNull
    Instant registrationDate;
    @NotNull
    Instant lastUpdated;
}
