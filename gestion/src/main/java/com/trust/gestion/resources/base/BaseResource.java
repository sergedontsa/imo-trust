/*
 * Copyright (c) 2023.  - Serge G. Dontsa
 */

package com.trust.gestion.resources.base;

import jakarta.validation.constraints.NotNull;

import java.time.Instant;


public class BaseResource {
    @NotNull
    Instant registrationDate;
    @NotNull
    Instant lastUpdated;
}
