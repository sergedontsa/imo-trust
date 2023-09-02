/*
 * Copyright (c) 2023.  - Serge G. Dontsa
 */

package com.trust.gestion.services.handlers;


import com.trust.gestion.services.domain.OwnerDto;
import com.trust.gestion.services.entities.OwnerEntity;
import com.trust.gestion.services.resources.OwnerResource;
import com.trust.gestion.utilities.Utilities;

import java.time.Instant;
import java.util.Optional;

public class OwnerHandler {

    public OwnerDto handle(OwnerResource resource, Optional<OwnerEntity> optionalOwnerEntity) {
        return optionalOwnerEntity.isPresent() ? update(resource, optionalOwnerEntity.get()) : create(resource);
    }

    private OwnerDto create(OwnerResource resource) {
        return OwnerDto.builder()
                .id(Utilities.getApartmentExpenseID())
                .lastName(resource.getLastName())
                .firstName(resource.getFirstName())
                .middleName(resource.getMiddleName())
                .lastUpdated(Instant.now())
                .registrationDate(Instant.now())
                .gender(resource.getGender())
                .address(OwnerResourceUtilities.getAddressDtoFromResource(resource.getAddress()))
                .information(OwnerResourceUtilities.getOwnerInformationDtoFromResource(resource.getInformation()))
                .contacts(OwnerResourceUtilities.getOwnerContactInformationFromResource(resource.getContacts()))
                .identifications(OwnerResourceUtilities.getOwnerIdentificationDtoFromResource(resource.getIdentifications()))
                .build();
    }



    private OwnerDto update(OwnerResource resource, OwnerEntity entity) {
//        OwnerMapper mapper = new OwnerMapperImpl();
//        OwnerDto dto = mapper.resourceToDto(resource);
//        OwnerEntity updated = mapper.update(dto, entity);
//        return mapper.toDto(updated);
        return null;
    }
}
