/*
 * Copyright (c) 2023.  - Serge G. Dontsa
 */

package com.trust.gestion.services.domain;


import com.trust.gestion.services.entities.ContractorEntity;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.antlr.v4.runtime.misc.NotNull;

import java.io.Serializable;
import java.time.Instant;

/**
 * DTO for {@link ContractorEntity}
 */
@Getter
@Setter
@Builder(toBuilder = true)
@AllArgsConstructor
public class ContractorDto implements Serializable {
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
