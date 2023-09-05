package com.trust.gestion.services.handlers;

import com.trust.gestion.services.domain.OwnerBuildingLinkDto;
import com.trust.gestion.services.entities.OwnerBuildingLinkEntity;
import com.trust.gestion.services.resources.OwnerLinkResource;

import java.time.Instant;
import java.util.Optional;

public class OwnerBuildingLinkHandler {
    public OwnerBuildingLinkDto handler(OwnerLinkResource resource, Optional<OwnerBuildingLinkEntity> optionalEntity) {
        return optionalEntity.isPresent() ? update(resource, optionalEntity.get()) : create(resource);
    }
    private OwnerBuildingLinkDto create(OwnerLinkResource resource){
        return OwnerBuildingLinkDto.builder()
                .registrationDate(Instant.now())
                .lastUpdated(Instant.now())
                .build();
    }
    private OwnerBuildingLinkDto update(OwnerLinkResource resource, OwnerBuildingLinkEntity entity){
        return null;
    }
}
