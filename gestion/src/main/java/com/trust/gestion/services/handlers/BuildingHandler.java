/*
 * Copyright (c) 2023.  - Serge G. Dontsa
 */

package com.trust.gestion.services.handlers;


import com.trust.gestion.services.domain.BuildingDto;
import com.trust.gestion.services.entities.BuildingEntity;
import com.trust.gestion.services.mappers.BuildingMapper;
import com.trust.gestion.services.mappers.BuildingMapperImpl;
import com.trust.gestion.services.resources.BuildingResource;
import com.trust.gestion.utilities.Utilities;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Optional;

import static java.util.Optional.empty;

@Component
@AllArgsConstructor
public class BuildingHandler {

    public BuildingDto handle(BuildingResource resource, Optional<BuildingEntity> optionalEntity) {
        return optionalEntity.isPresent() ? update(resource, optionalEntity.get()) : create(resource);
    }
    private BuildingDto create(BuildingResource resource) {
        BuildingMapper mapper = new BuildingMapperImpl();
        BuildingDto dto = mapper.fromResourceToDto(resource);

        return dto.toBuilder()
                .apartments(
                        resource.getApartments()
                                .stream()
                                .map(apartmentResource -> (new ApartmentHandler()).handle(apartmentResource, empty()))
                                .toList()
                )
        .id(Utilities.getBuildingID())
        .registrationDate(Instant.now())
        .lastUpdated(Instant.now())
        .build();
    }
    private BuildingDto update(BuildingResource resource, BuildingEntity entity) {
        return null;
    }

}
