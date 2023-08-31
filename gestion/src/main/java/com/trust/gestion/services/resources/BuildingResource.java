/*
 * Copyright (c) 2023.  - Serge G. Dontsa
 */

package com.trust.gestion.services.resources;


import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.antlr.v4.runtime.misc.NotNull;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class BuildingResource {
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
    List<ApartmentResource> apartments = new ArrayList<>();
    @NotNull
    Instant registrationDate;
    @NotNull
    Instant lastUpdated;
}
