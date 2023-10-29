/*
 * Copyright (c) 2023.  - Serge G. Dontsa
 */

package com.trust.gestion.services.resources;


import com.trust.gestion.enums.BuildingStatus;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class BuildingResource {
    @Size(max = 50)
    private String id;
    @Size(max = 250)
    private String designation;
    @Size(max = 250)
    private String description;
    @NotNull
    @Size(max = 250)
    private String category;
    private Boolean assigned;

    private BuildingStatus status;
    @NotNull
    private Integer constructionYear;
    @NotNull
    private Integer numberOfFloors;
    private Integer numberOfUnits;
    @NotNull
    private Instant registrationDate;
    @NotNull
    private Instant lastUpdated;
}
