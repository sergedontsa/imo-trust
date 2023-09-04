package com.trust.gestion.services.handlers;

import com.trust.gestion.services.domain.OwnerIdentificationDto;
import com.trust.gestion.services.entities.OwnerIdentificationEntity;
import com.trust.gestion.services.mappers.OwnerIdentificationMapper;
import com.trust.gestion.services.mappers.OwnerIdentificationMapperImpl;
import com.trust.gestion.services.resources.OwnerIdentificationRessource;

import java.time.Instant;
import java.util.Optional;

public class OwnerIdentificationHandler {
    public OwnerIdentificationDto handle(OwnerIdentificationRessource resource, Optional<OwnerIdentificationEntity> optionalEntity) {
        return optionalEntity.isPresent() ? this.update(resource, optionalEntity.get()) : this.create(resource);
    }

    private OwnerIdentificationDto update(OwnerIdentificationRessource resource, OwnerIdentificationEntity entity) {
        OwnerIdentificationMapper mapper = new OwnerIdentificationMapperImpl();
        OwnerIdentificationDto dto = mapper.fromResourceToDto(resource);
        dto.toBuilder()
                .registrationDate(entity.getRegistrationDate())
                .lastUpdated(Instant.now())
                .build();
        OwnerIdentificationEntity updateEntity = mapper.partialUpdate(dto, entity);
        return mapper.toDto(updateEntity);
    }

    private OwnerIdentificationDto create(OwnerIdentificationRessource resource) {


        OwnerIdentificationMapper mapper = new OwnerIdentificationMapperImpl();
        OwnerIdentificationDto dto = mapper.fromResourceToDto(resource);
        return dto.toBuilder()
                .registrationDate(Instant.now())
                .lastUpdated(Instant.now())
                .build();
    }
}
