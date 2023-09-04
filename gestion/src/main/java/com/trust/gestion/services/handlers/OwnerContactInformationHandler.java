package com.trust.gestion.services.handlers;

import com.trust.gestion.services.domain.OwnerContactInformationDto;
import com.trust.gestion.services.entities.OwnerContactInformationEntity;
import com.trust.gestion.services.mappers.OwnerContactInformationMapper;
import com.trust.gestion.services.mappers.OwnerContactInformationMapperImpl;
import com.trust.gestion.services.resources.OwnerContactInformationRessource;

import java.time.Instant;
import java.util.Optional;

public class OwnerContactInformationHandler {
    public OwnerContactInformationDto handle(OwnerContactInformationRessource resource, Optional<OwnerContactInformationEntity> optionalEntity){
        return optionalEntity.isPresent() ? this.update(resource, optionalEntity.get()) : this.create(resource);
    }

    private OwnerContactInformationDto create(OwnerContactInformationRessource resource) {
        OwnerContactInformationMapper mapper = new OwnerContactInformationMapperImpl();
        OwnerContactInformationDto dto = mapper.fromResourceToDto(resource);
        return dto.toBuilder()
                .registrationDate(Instant.now())
                .lastUpdated(Instant.now())
                .build();
    }

    private OwnerContactInformationDto update(OwnerContactInformationRessource resource, OwnerContactInformationEntity entity) {
        OwnerContactInformationMapper mapper = new OwnerContactInformationMapperImpl();
        OwnerContactInformationDto dto = mapper.fromResourceToDto(resource);
        dto.toBuilder()
                .registrationDate(entity.getRegistrationDate())
                .lastUpdated(Instant.now())
                .id(entity.getId())
                .build();
        OwnerContactInformationEntity updatedEntity = mapper.partialUpdate(dto, entity);
        return mapper.toDto(updatedEntity);

    }
}
