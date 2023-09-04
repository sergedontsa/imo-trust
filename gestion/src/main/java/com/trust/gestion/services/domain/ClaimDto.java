package com.trust.gestion.services.domain;
/*
 * Copyright (c) 2023.  - Serge G. Dontsa
 */

import com.trust.gestion.enums.StatusType;
import com.trust.gestion.enums.SubjectType;
import com.trust.gestion.services.entities.Claim;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Value;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;

/**
 * DTO for {@link Claim}
 */
@Value
public class ClaimDto implements Serializable {
    @Size(max = 20)
    String id;
    @NotNull
    @Size(max = 20)
    String tile;
    @NotNull
    LocalDate date;
    @NotNull
    SubjectType subjectType;
    @NotNull
    @Size(max = 20)
    String subjectId;
    @NotNull
    @Size(max = 20)
    String severity;
    @NotNull
    StatusType status;
    BigDecimal amount;
    @NotNull
    Instant registrationDate;
    @NotNull
    Instant lastUpdated;
}
