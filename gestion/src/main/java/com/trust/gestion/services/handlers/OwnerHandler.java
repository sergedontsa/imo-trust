/*
 * Copyright (c) 2023.  - Serge G. Dontsa
 */

package com.trust.gestion.services.handlers;


import com.trust.gestion.enums.Gender;
import com.trust.gestion.services.domain.OwnerAddressDto;
import com.trust.gestion.services.domain.OwnerContactInformationDto;
import com.trust.gestion.services.domain.OwnerDto;
import com.trust.gestion.services.domain.OwnerIdentificationDto;
import com.trust.gestion.services.domain.OwnerInformationDto;
import com.trust.gestion.services.entities.OwnerEntity;
import com.trust.gestion.services.entities.OwnerIdentificationEntity;
import com.trust.gestion.services.mappers.OwnerAddressMapper;
import com.trust.gestion.services.mappers.OwnerAddressMapperImpl;
import com.trust.gestion.services.mappers.OwnerContactInformationMapper;
import com.trust.gestion.services.mappers.OwnerContactInformationMapperImpl;
import com.trust.gestion.services.mappers.OwnerIdentificationMapper;
import com.trust.gestion.services.mappers.OwnerIdentificationMapperImpl;
import com.trust.gestion.services.mappers.OwnerInformationMapper;
import com.trust.gestion.services.mappers.OwnerInformationMapperImpl;
import com.trust.gestion.services.resources.OwnerAddressResource;
import com.trust.gestion.services.resources.OwnerContactInformationRessource;
import com.trust.gestion.services.resources.OwnerIdentificationRessource;
import com.trust.gestion.services.resources.OwnerInformationResource;
import com.trust.gestion.services.resources.OwnerResource;
import com.trust.gestion.utilities.Utilities;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
                .address(this.getAddressDtoFromResource(resource.getAddress()))
                .information(this.getOwnerInformationDtoFromResource(resource.getInformation()))
                .contacts(this.getOwnerContactInformationFromResource(resource.getContacts()))
                .identifications(this.getOwnerIdentificationDtoFromResource(resource.getIdentifications()))
                .build();
    }
    private List<OwnerIdentificationDto> getOwnerIdentificationDtoFromResource(List<OwnerIdentificationRessource> resources){
        OwnerIdentificationMapper mapper = new OwnerIdentificationMapperImpl();
        return resources.stream().map(resource -> {
            resource.setRegistrationDate(Instant.now());
            resource.setLastUpdated(Instant.now());
            return resource;
        }).map(mapper::fromResourceToDto).collect(Collectors.toList());
    }
    private List<OwnerContactInformationDto> getOwnerContactInformationFromResource(List<OwnerContactInformationRessource> resources){
        OwnerContactInformationMapper mapper = new OwnerContactInformationMapperImpl();
        return resources.stream().map(resource -> {
            resource.setRegistrationDate(Instant.now());
            resource.setLastUpdated(Instant.now());
            return resource;
        }).map(mapper::fromResourceToDto).collect(Collectors.toList());
    }
    private List<OwnerInformationDto> getOwnerInformationDtoFromResource(List<OwnerInformationResource> resources) {
        OwnerInformationMapper mapper = new OwnerInformationMapperImpl();
        return resources.stream().map(resource -> {
            resource.setRegistrationDate(Instant.now());
            resource.setLastUpdated(Instant.now());
            return resource;
        }).map(mapper::fromResourceToDto).collect(Collectors.toList());

    }

    private List<OwnerAddressDto> getAddressDtoFromResource(List<OwnerAddressResource> resources) {
        OwnerAddressMapper mapper = new OwnerAddressMapperImpl();
        return resources.stream().map(resource -> {
            resource.setRegistrationDate(Instant.now());
            resource.setLastUpdated(Instant.now());
            return resource;
        }).map(mapper::fromResourceToDto).collect(Collectors.toList());
    }


    private OwnerDto update(OwnerResource resource, OwnerEntity entity) {
//        OwnerMapper mapper = new OwnerMapperImpl();
//        OwnerDto dto = mapper.resourceToDto(resource);
//        OwnerEntity updated = mapper.update(dto, entity);
//        return mapper.toDto(updated);
        return null;
    }
}
