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
        if (this.findById(dto.getId()).isPresent()){
            OwnerEntity savedInBd = this.saveOwnerInBd(ActionTitle.OWNER_UPDATE, this.mapper.toEntity(dto));

            this.saveOwnerAddress(ActionTitle.OWNER_ADDRESS_UPDATE, dto, savedInBd);
            this.saveOwnerInformation(ActionTitle.OWNER_INFORMATION_UPDATE, dto, savedInBd);
            this.saveContactInformation(ActionTitle.OWNER_CONTACT_UPDATE, dto, savedInBd);
            this.saveOwnerIdenfication(ActionTitle.OWNER_IDENTIFICATION_UPDATE, dto, savedInBd);

            return this.mapper.toDto(savedInBd);

        }else {
            OwnerEntity savedInBd = this.saveOwnerInBd(ActionTitle.OWNER_CREATE, this.mapper.toEntity(dto));

            this.saveOwnerAddress(ActionTitle.OWNER_ADDRESS_CREATE, dto, savedInBd);
            this.saveOwnerInformation(ActionTitle.OWNER_INFORMATION_CREATE, dto, savedInBd);
            this.saveContactInformation(ActionTitle.OWNER_CONTACT_CREATE, dto, savedInBd);
            this.saveOwnerIdenfication(ActionTitle.OWNER_IDENTIFICATION_CREATE, dto, savedInBd);

            return this.mapper.toDto(savedInBd);
        }
    }
    private OwnerEntity saveOwnerInBd(ActionTitle actionTitle, OwnerEntity entity) {
        OwnerEntity saved = this.repository.save(entity);
        actionPersistence.createAction(actionTitle);
        return saved;
    }
    private void saveOwnerAddress(ActionTitle actionTitle, OwnerDto dto, OwnerEntity entity){
        if (CollectionUtils.isNotEmpty(dto.getAddress())) {
            List<OwnerAddressEntity> addressEntities = dto.getAddress()
                    .stream().map(this.addressMapper::toEntity).toList();
            addressEntities.forEach(addressEntity -> addressEntity.setOwner(entity));
            addressRepository.saveAll(addressEntities);
            actionPersistence.createAction(actionTitle);
        }else {
            log.warn("Owner address is empty");
        }
    }
    private void saveOwnerInformation(ActionTitle actionTitle, OwnerDto dto, OwnerEntity entity){
        if (CollectionUtils.isNotEmpty(dto.getInformation())) {
            List<OwnerInformationEntity> informationEntities = dto.getInformation()
                    .stream().map(this.informationMapper::toEntity).toList();
            informationEntities.forEach(informationEntity -> informationEntity.setOwner(entity));
            informationRepository.saveAll(informationEntities);
            actionPersistence.createAction(actionTitle);
        }else {
            log.warn("Owner information is empty");
        }
    }
    private void saveContactInformation(ActionTitle actionTitle, OwnerDto dto, OwnerEntity entity){
        if (CollectionUtils.isNotEmpty(dto.getContacts())) {
            List<OwnerContactInformationEntity> contactInformationEntities = dto.getContacts()
                    .stream().map(this.contactInformationMapper::toEntity).toList();
            contactInformationEntities.forEach(contactInformationEntity -> contactInformationEntity.setOwner(entity));
            contactInformationRepository.saveAll(contactInformationEntities);
            actionPersistence.createAction(actionTitle);
        }else {
            log.warn("Owner contact information is empty");
        }
    }
    private void saveOwnerIdenfication(ActionTitle actionTitle, OwnerDto dto, OwnerEntity entity){
        if (CollectionUtils.isNotEmpty(dto.getIdentifications())) {

            List<OwnerIdentificationEntity> identificationEntities = dto.getIdentifications()
                    .stream().map(this.identificationMapper::toEntity).toList();
            identificationEntities.forEach(identificationEntity -> identificationEntity.setOwner(entity));
            identificationRepository.saveAll(identificationEntities);
            actionPersistence.createAction(actionTitle);
        }else {
            log.warn("Owner identification is empty");
        }
    }
    private Optional<OwnerEntity> findById(String id) {
        return this.repository.findById(id);
    }
}
