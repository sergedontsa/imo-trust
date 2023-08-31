/*
 * Copyright (c) 2023.  - Serge G. Dontsa
 */

package com.trust.gestion.services.domain;


import com.trust.gestion.services.entities.BuildingEntity;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.antlr.v4.runtime.misc.NotNull;

import java.io.Serializable;
import java.time.Instant;

/**
 * DTO for {@link BuildingEntity}
 */
@Getter
@Setter
@Builder(toBuilder = true)
@AllArgsConstructor
public class BuildingDto implements Serializable {
    @Size(max = 50)
    String id;
    @Size(max = 250)
    String designation;
    @Size(max = 250)
    String description;
    @NotNull
    @Size(max = 250)
    String category;
    @NotNull
    Integer constructionYear;
    @NotNull
    Integer numberOfFloors;
    @NotNull
    Instant registrationDate;
    @NotNull
    Instant lastUpdated;
}
