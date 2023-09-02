/*
 * Copyright (c) 2023.  - Serge G. Dontsa
 */

package com.trust.gestion.services.persistence;

import com.trust.gestion.services.domain.OwnerDto;
import com.trust.gestion.services.entities.OwnerAddressEntity;
import com.trust.gestion.services.entities.OwnerContactInformationEntity;
import com.trust.gestion.services.entities.OwnerEntity;
import com.trust.gestion.services.entities.OwnerIdentificationEntity;
import com.trust.gestion.services.entities.OwnerInformationEntity;
import com.trust.gestion.services.mappers.OwnerAddressMapper;
import com.trust.gestion.services.mappers.OwnerContactInformationMapper;
import com.trust.gestion.services.mappers.OwnerIdentificationMapper;
import com.trust.gestion.services.mappers.OwnerInformationMapper;
import com.trust.gestion.services.mappers.OwnerMapper;
import com.trust.gestion.services.repositories.OwnerAddressRepository;
import com.trust.gestion.services.repositories.OwnerContactInformationRepository;
import com.trust.gestion.services.repositories.OwnerIdentificationRepository;
import com.trust.gestion.services.repositories.OwnerInformationRepository;
import com.trust.gestion.services.repositories.OwnerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Component
public class OwnerPersistence {
    private final OwnerRepository repository;
    private final OwnerMapper mapper;
    private final OwnerAddressMapper addressMapper;
    private final OwnerAddressRepository addressRepository;
    private final OwnerInformationMapper informationMapper;
    private final OwnerInformationRepository informationRepository;
    private final OwnerContactInformationMapper contactInformationMapper;
    private final OwnerContactInformationRepository contactInformationRepository;
    private final OwnerIdentificationRepository identificationRepository;
    private final OwnerIdentificationMapper identificationMapper;
    public OwnerDto saved(OwnerDto dto) {
        OwnerEntity entity = this.mapper.toEntity(dto);

        List<OwnerAddressEntity> addressEntities = dto.getAddress()
                .stream().map(this.addressMapper::toEntity).toList();

        List<OwnerInformationEntity> informationEntities = dto.getInformation()
                .stream().map(this.informationMapper::toEntity).toList();

        List<OwnerContactInformationEntity> contactInformationEntities = dto.getContacts()
                .stream().map(this.contactInformationMapper::toEntity).toList();

        List<OwnerIdentificationEntity> identificationEntities = dto.getIdentifications()
                .stream().map(this.identificationMapper::toEntity).toList();

        OwnerEntity saved = this.repository.save(entity);

        addressEntities.forEach(addressEntity -> addressEntity.setOwner(saved));
        informationEntities.forEach(info -> info.setOwner(saved));
        contactInformationEntities.forEach(contact -> contact.setOwner(saved));
        identificationEntities.forEach(identification -> identification.setOwner(saved));

        addressRepository.saveAll(addressEntities);
        informationRepository.saveAll(informationEntities);
        contactInformationRepository.saveAll(contactInformationEntities);
        identificationRepository.saveAll(identificationEntities);

        return this.mapper.toDto(saved);
    }

    public void deleteById(String id) {
        this.findById(id).ifPresent(repository::delete);
    }
    private Optional<OwnerEntity> findById(String id) {
        return this.repository.findById(id);
    }
}
