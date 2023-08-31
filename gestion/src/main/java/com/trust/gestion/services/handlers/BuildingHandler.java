/*
 * Copyright (c) 2023.  - Serge G. Dontsa
 */

package com.trust.gestion.services.handlers;



import com.trust.gestion.services.domain.BuildingDto;
import com.trust.gestion.services.entities.BuildingEntity;
import com.trust.gestion.services.resources.BuildingResource;
import com.trust.gestion.utilities.Utilities;

import java.time.Instant;
import java.util.Optional;

public class BuildingHandler {
    public BuildingDto handle(BuildingResource resource, Optional<BuildingEntity> optionalEntity) {
        return optionalEntity.isPresent() ? update(resource, optionalEntity.get()) : create(resource);
    }
    private BuildingDto create(BuildingResource resource) {
        return BuildingDto.builder()
                .id(Utilities.getBuildingID())
                .designation(resource.getDesignation())
                .description(resource.getDescription())
                .category(resource.getCategory())
                .constructionYear(resource.getConstructionYear())
                .numberOfFloors(resource.getNumberOfFloors())
                .registrationDate(Instant.now())
                .lastUpdated(Instant.now())
                .build();
    }
    private BuildingDto update(BuildingResource resource, BuildingEntity entity) {
        return null;
    }
}
