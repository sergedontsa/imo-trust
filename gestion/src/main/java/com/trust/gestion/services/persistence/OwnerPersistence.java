/*
 * Copyright (c) 2023.  - Serge G. Dontsa
 */

package com.trust.gestion.services.persistence;

import com.trust.gestion.enums.ActionTitle;
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
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Component
@Slf4j
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
    private final ActionPersistence actionPersistence;
    public OwnerDto saved(OwnerDto dto) {
        Optional<OwnerEntity> entityOptional = this.findById(dto.getId());
        if (entityOptional.isPresent()){
            log.error("Owner already exist");
            throw new IllegalArgumentException("Owner already exist");
        }else {
            OwnerEntity savedInBd = this.saveOwnerInBd(this.mapper.toEntity(dto));

            this.saveOwnerAddress(dto, savedInBd);
            this.saveOwnerInformation(dto, savedInBd);
            this.saveContactInformation(dto, savedInBd);
            this.saveOwnerIdenfication(dto, savedInBd);

            return this.mapper.toDto(savedInBd);
        }
    }
    private OwnerEntity saveOwnerInBd(OwnerEntity entity) {
        OwnerEntity saved = this.repository.save(entity);
        actionPersistence.createAction(ActionTitle.OWNER_CREATE);
        return saved;
    }
    private void saveOwnerAddress(OwnerDto dto, OwnerEntity entity){
        if (CollectionUtils.isNotEmpty(dto.getAddress())) {
            List<OwnerAddressEntity> addressEntities = dto.getAddress()
                    .stream().map(this.addressMapper::toEntity).toList();
            addressEntities.forEach(addressEntity -> addressEntity.setOwner(entity));
            addressRepository.saveAll(addressEntities);
            actionPersistence.createAction(ActionTitle.OWNER_ADDRESS_CREATE);
        }else {
            log.warn("Owner address is empty");
        }
    }
    private void saveOwnerInformation(OwnerDto dto, OwnerEntity entity){
        if (CollectionUtils.isNotEmpty(dto.getInformation())) {
            List<OwnerInformationEntity> informationEntities = dto.getInformation()
                    .stream().map(this.informationMapper::toEntity).toList();
            informationEntities.forEach(informationEntity -> informationEntity.setOwner(entity));
            informationRepository.saveAll(informationEntities);
            actionPersistence.createAction(ActionTitle.OWNER_INFORMATION_CREATE);
        }else {
            log.warn("Owner information is empty");
        }
    }
    private void saveContactInformation(OwnerDto dto, OwnerEntity entity){
        if (CollectionUtils.isNotEmpty(dto.getContacts())) {
            List<OwnerContactInformationEntity> contactInformationEntities = dto.getContacts()
                    .stream().map(this.contactInformationMapper::toEntity).toList();
            contactInformationEntities.forEach(contactInformationEntity -> contactInformationEntity.setOwner(entity));
            contactInformationRepository.saveAll(contactInformationEntities);
            actionPersistence.createAction(ActionTitle.OWNER_CONTACT_CREATE);
        }else {
            log.warn("Owner contact information is empty");
        }
    }
    private void saveOwnerIdenfication(OwnerDto dto, OwnerEntity entity){
        if (CollectionUtils.isNotEmpty(dto.getIdentifications())) {

            List<OwnerIdentificationEntity> identificationEntities = dto.getIdentifications()
                    .stream().map(this.identificationMapper::toEntity).toList();
            identificationEntities.forEach(identificationEntity -> identificationEntity.setOwner(entity));
            identificationRepository.saveAll(identificationEntities);
            actionPersistence.createAction(ActionTitle.OWNER_IDENTIFICATION_CREATE);
        }else {
            log.warn("Owner identification is empty");
        }
    }
    public void deleteById(String id) {
        this.findById(id).ifPresent(repository::delete);
    }
    private Optional<OwnerEntity> findById(String id) {
        return this.repository.findById(id);
    }
}
