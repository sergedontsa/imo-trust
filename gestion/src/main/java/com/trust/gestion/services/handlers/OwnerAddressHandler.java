package com.trust.gestion.services.handlers;

import com.trust.gestion.services.domain.OwnerAddressDto;
import com.trust.gestion.services.entities.OwnerAddressEntity;
import com.trust.gestion.services.mappers.OwnerAddressMapper;
import com.trust.gestion.services.mappers.OwnerAddressMapperImpl;
import com.trust.gestion.services.resources.OwnerAddressResource;
import lombok.extern.slf4j.Slf4j;

import java.time.Instant;
import java.util.Optional;

@Slf4j
public class OwnerAddressHandler {
    public OwnerAddressDto handle(OwnerAddressResource resource, Optional<OwnerAddressEntity> optionalEntity){
        return optionalEntity.isPresent() ? this.update(resource, optionalEntity.get()) : this.create(resource);
    }

    private OwnerAddressDto create(OwnerAddressResource resource) {
        OwnerAddressMapper mapper = new OwnerAddressMapperImpl();
        OwnerAddressDto dto = mapper.fromResourceToDto(resource);
        return dto.toBuilder()
                .registrationDate(Instant.now())
                .lastUpdated(Instant.now())
                .build();
    }

    private OwnerAddressDto update(OwnerAddressResource resource, OwnerAddressEntity entity) {
        OwnerAddressMapper mapper = new OwnerAddressMapperImpl();
        OwnerAddressDto dto = mapper.fromResourceToDto(resource);
        dto.toBuilder().registrationDate(entity.getRegistrationDate())
                .lastUpdated(Instant.now())
                .id(entity.getId())
                .build();
        OwnerAddressEntity updatedEntity = mapper.partialUpdate(dto, entity);
        return mapper.toDto(updatedEntity);
    }
}
