/*
 * Copyright (c) 2023.  - Serge G. Dontsa
 */

package com.trust.gestion.services.handlers;


import com.trust.gestion.services.domain.OwnerAddressDto;
import com.trust.gestion.services.domain.OwnerContactInformationDto;
import com.trust.gestion.services.domain.OwnerDto;
import com.trust.gestion.services.domain.OwnerIdentificationDto;
import com.trust.gestion.services.domain.OwnerInformationDto;
import com.trust.gestion.services.entities.OwnerAddressEntity;
import com.trust.gestion.services.entities.OwnerContactInformationEntity;
import com.trust.gestion.services.entities.OwnerEntity;
import com.trust.gestion.services.entities.OwnerIdentificationEntity;
import com.trust.gestion.services.entities.OwnerInformationEntity;
import com.trust.gestion.services.mappers.OwnerMapper;
import com.trust.gestion.services.mappers.OwnerMapperImpl;
import com.trust.gestion.services.resources.OwnerAddressResource;
import com.trust.gestion.services.resources.OwnerContactInformationRessource;
import com.trust.gestion.services.resources.OwnerIdentificationRessource;
import com.trust.gestion.services.resources.OwnerInformationResource;
import com.trust.gestion.services.resources.OwnerResource;
import lombok.extern.slf4j.Slf4j;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Slf4j
public class OwnerHandler {

    public OwnerDto handle(OwnerResource resource, Optional<OwnerEntity> optionalEntity) {
        return optionalEntity.isPresent() ? update(resource, optionalEntity.get()) : create(resource);
    }

    private OwnerDto create(OwnerResource resource) {
        OwnerMapper mapper = new OwnerMapperImpl();
        OwnerDto dto = mapper.fromResourceToDto(resource);
        return dto.toBuilder().registrationDate(Instant.now()).lastUpdated(Instant.now()).build();

    }

    private OwnerDto update(OwnerResource resource, OwnerEntity entity) {
        OwnerMapper mapper = new OwnerMapperImpl();
        OwnerDto dto = mapper.fromResourceToDto(resource);

        List<OwnerAddressDto> updatedAddress = this.updatedOwnerAddress(resource, entity);
        List<OwnerInformationDto> updateInformation = this.updateOwnerInformation(resource, entity);
        List<OwnerIdentificationDto> updateIdentification = this.updateOwnerIdentification(resource, entity);
        List<OwnerContactInformationDto> updateContact = this.updateOwnerContactInformation(resource, entity);

        dto = dto.toBuilder()
                .registrationDate(entity.getRegistrationDate())
                .lastUpdated(Instant.now())
                .id(entity.getId())
                .build();

        OwnerDto update = mapper.toDto(mapper.update(dto, entity));
        return update.toBuilder().address(updatedAddress).information(updateInformation).identifications(updateIdentification).contacts(updateContact).build();
    }

    private List<OwnerAddressDto> updatedOwnerAddress(OwnerResource resource, OwnerEntity entity) {
        return resource.getAddress().stream().map(OwnerAddressResource::getId).toList().stream().map(id -> {
            OwnerAddressEntity addressEntity = this.findAddressEntityById(id, entity);
            OwnerAddressResource addressResource = this.findAddressResourceById(id, resource);
            OwnerAddressHandler handler = new OwnerAddressHandler();
            return handler.handle(addressResource, Optional.of(addressEntity));
        }).toList();
    }

    private List<OwnerInformationDto> updateOwnerInformation(OwnerResource resource, OwnerEntity entity) {
        return resource.getInformation().stream().map(OwnerInformationResource::getId).toList().stream().map(id -> {
            OwnerInformationEntity informationEntity = this.findInformationEntityById(id, entity);
            OwnerInformationResource informationResource = this.findInformationResourceById(id, resource);
            OwnerInformationHandler handler = new OwnerInformationHandler();
            return handler.handler(informationResource, Optional.of(informationEntity));

        }).toList();
    }

    private List<OwnerIdentificationDto> updateOwnerIdentification(OwnerResource resource, OwnerEntity entity) {
        return resource.getIdentifications().stream().map(OwnerIdentificationRessource::getId).toList().stream().map(id -> {
            OwnerIdentificationEntity identificationEntity = this.findIdentificationEntityById(id, entity);
            OwnerIdentificationRessource identificationResource = this.findIdentificationResourceById(id, resource);
            OwnerIdentificationHandler handler = new OwnerIdentificationHandler();
            return handler.handle(identificationResource, Optional.of(identificationEntity));
        }).toList();
    }

    private List<OwnerContactInformationDto> updateOwnerContactInformation(OwnerResource resource, OwnerEntity entity) {
        return resource.getContacts().stream().map(OwnerContactInformationRessource::getId).toList().stream().map(id -> {
            OwnerContactInformationEntity contactEntity = this.findContactEntityById(id, entity);
            OwnerContactInformationRessource contactResource = this.findContactResourceById(id, resource);
            OwnerContactInformationHandler handler = new OwnerContactInformationHandler();
            return handler.handle(contactResource, Optional.of(contactEntity));
        }).toList();
    }

    private OwnerContactInformationEntity findContactEntityById(Integer id, OwnerEntity entity) {
        Optional<OwnerContactInformationEntity> optional = entity.getContacts().stream().filter(contact -> contact.getId().equals(id)).findFirst();
        return optional.get();
    }

    private OwnerContactInformationRessource findContactResourceById(Integer id, OwnerResource resource) {
        Optional<OwnerContactInformationRessource> optional = resource.getContacts().stream().filter(contact -> contact.getId().equals(id)).findFirst();
        return optional.get();
    }

    private OwnerInformationEntity findInformationEntityById(Integer id, OwnerEntity entity) {
        Optional<OwnerInformationEntity> optional = entity.getInformation().stream().filter(information -> information.getId().equals(id)).findFirst();
        return optional.get();
    }

    private OwnerInformationResource findInformationResourceById(Integer id, OwnerResource resource) {
        Optional<OwnerInformationResource> optional = resource.getInformation().stream().filter(information -> information.getId().equals(id)).findFirst();
        return optional.get();
    }

    private OwnerIdentificationEntity findIdentificationEntityById(Integer id, OwnerEntity entity) {
        Optional<OwnerIdentificationEntity> optional = entity.getIdentifications().stream().filter(identification -> identification.getId().equals(id)).findFirst();
        return optional.get();
    }

    private OwnerIdentificationRessource findIdentificationResourceById(Integer id, OwnerResource resource) {
        Optional<OwnerIdentificationRessource> optional = resource.getIdentifications().stream().filter(identification -> identification.getId().equals(id)).findFirst();
        return optional.get();
    }

    private OwnerAddressEntity findAddressEntityById(Integer id, OwnerEntity entity) {
        Optional<OwnerAddressEntity> optional = entity.getAddress().stream().filter(address -> address.getId().equals(id)).findFirst();
        return optional.get();
    }

    private OwnerAddressResource findAddressResourceById(Integer id, OwnerResource resource) {
        Optional<OwnerAddressResource> optional = resource.getAddress().stream().filter(address -> address.getId().equals(id)).findFirst();
        return optional.get();
    }


}
