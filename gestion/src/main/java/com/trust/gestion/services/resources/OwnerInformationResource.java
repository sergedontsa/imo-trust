/*
 * Copyright (c) 2023.  - Serge G. Dontsa
 */

package com.trust.gestion.services.resources;



import com.trust.gestion.services.entities.OwnerInformationEntity;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Value;
import org.antlr.v4.runtime.misc.NotNull;

import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDate;

/**
 * DTO for {@link OwnerInformationEntity}
 */
@Value
@Builder(toBuilder = true)
public class OwnerInformationResource implements Serializable {
    Integer id;
    @NotNull
    LocalDate dateOfBirth;
    @NotNull
    @Size(max = 50)
    String nationality;
    @NotNull
    @Size(max = 50)
    String countryOfBirth;
    @NotNull
    Instant registrationDate;
    @NotNull
    Instant lastUpdated;
}
