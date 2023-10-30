package com.trust.gestion.handlers;

import com.trust.gestion.domain.OwnerBuildingLinkDto;
import com.trust.gestion.entities.OwnerBuildingLinkEntity;
import com.trust.gestion.resources.OwnerLinkResource;

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
