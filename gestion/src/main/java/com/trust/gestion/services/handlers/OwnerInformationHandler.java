package com.trust.gestion.services.handlers;

import com.trust.gestion.services.domain.OwnerInformationDto;
import com.trust.gestion.services.entities.OwnerInformationEntity;
import com.trust.gestion.services.mappers.OwnerInformationMapper;
import com.trust.gestion.services.mappers.OwnerInformationMapperImpl;
import com.trust.gestion.services.resources.OwnerInformationResource;

import java.time.Instant;
import java.util.Optional;


public class OwnerInformationHandler {
    public OwnerInformationDto handler(OwnerInformationResource resource, Optional<OwnerInformationEntity> optionalEntity){
        return optionalEntity.isPresent() ? this.update(resource, optionalEntity.get()) : this.create(resource);
    }

    private OwnerInformationDto create(OwnerInformationResource resource) {
        OwnerInformationMapper mapper = new OwnerInformationMapperImpl();
        OwnerInformationDto dto = mapper.fromResourceToDto(resource);
        return dto.toBuilder()
                .registrationDate(Instant.now())
                .lastUpdated(Instant.now())
                .build();
    }

    private OwnerInformationDto update(OwnerInformationResource resource, OwnerInformationEntity ownerInformationEntity) {
        OwnerInformationMapper mapper = new OwnerInformationMapperImpl();
        OwnerInformationDto dto = mapper.fromResourceToDto(resource);
        dto.toBuilder()
                .registrationDate(ownerInformationEntity.getRegistrationDate())
                .lastUpdated(Instant.now())
                .id(ownerInformationEntity.getId())
                .build();
        OwnerInformationEntity updatedEntity = mapper.partialUpdate(dto, ownerInformationEntity);
        return mapper.toDto(updatedEntity);
    }
}
