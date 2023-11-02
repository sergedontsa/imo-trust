/*
 * Copyright (c) 2023.  - Serge G. Dontsa
 */

package com.trust.gestion.persistence;

import com.trust.gestion.enums.ActionTitle;
import com.trust.gestion.domain.OwnerDto;
import com.trust.gestion.mappers.OwnerAddressMapperImpl;
import com.trust.gestion.mappers.OwnerContactInformationMapper;
import com.trust.gestion.mappers.OwnerContactInformationMapperImpl;
import com.trust.gestion.mappers.OwnerIdentificationMapperImpl;
import com.trust.gestion.mappers.OwnerInformationMapper;
import com.trust.gestion.mappers.OwnerInformationMapperImpl;
import com.trust.gestion.repositories.OwnerContactInformationRepository;
import com.trust.gestion.entities.OwnerAddressEntity;
import com.trust.gestion.entities.OwnerContactInformationEntity;
import com.trust.gestion.entities.OwnerEntity;
import com.trust.gestion.entities.OwnerIdentificationEntity;
import com.trust.gestion.entities.OwnerInformationEntity;
import com.trust.gestion.mappers.OwnerAddressMapper;
import com.trust.gestion.mappers.OwnerIdentificationMapper;
import com.trust.gestion.repositories.OwnerAddressRepository;
import com.trust.gestion.repositories.OwnerIdentificationRepository;
import com.trust.gestion.repositories.OwnerInformationRepository;
import com.trust.gestion.repositories.OwnerRepository;
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
    private final OwnerAddressRepository addressRepository;
    private final OwnerInformationRepository informationRepository;
    private final OwnerContactInformationRepository contactInformationRepository;
    private final OwnerIdentificationRepository identificationRepository;
    private final ActionPersistence actionPersistence;
    private final OwnerAddressPersistence addressPersistence;
    private final OwnerInformationPersistence informationPersistence;
    private final OwnerContactInformationPersistence contactInformationPersistence;
    private final OwnerIdentificationPersistence identificationPersistence;
    public void saved(OwnerEntity entity) {
        this.saveOwnerInBd(ActionTitle.OWNER_CREATE, entity);
    }
    private void saveOwnerInBd(ActionTitle actionTitle, OwnerEntity entity) {
        this.repository.save(entity);
        this.actionPersistence.createAction(actionTitle);
    }
    private void saveOwnerAddress(ActionTitle actionTitle, OwnerDto dto, OwnerEntity entity){
        OwnerAddressMapper mapper = new OwnerAddressMapperImpl();
        if (CollectionUtils.isNotEmpty(dto.getAddress())) {
            List<OwnerAddressEntity> addressEntities = dto.getAddress()
                    .stream().map(mapper::toEntity).toList();
            addressEntities.forEach(addressEntity -> addressEntity.setOwner(entity));
           this.addressRepository.saveAll(addressEntities);
            actionPersistence.createAction(actionTitle);
        }else {
            log.warn("Owner address is empty");
        }
    }
    private void saveOwnerInformation(ActionTitle actionTitle, OwnerDto dto, OwnerEntity entity){
        OwnerInformationMapper informationMapper = new OwnerInformationMapperImpl();
        if (CollectionUtils.isNotEmpty(dto.getInformation())) {
            List<OwnerInformationEntity> informationEntities = dto.getInformation()
                    .stream().map(informationMapper::toEntity).toList();
            informationEntities.forEach(informationEntity -> informationEntity.setOwner(entity));
            this.informationRepository.saveAll(informationEntities);
            actionPersistence.createAction(actionTitle);
        }else {
            log.warn("Owner information is empty");
        }
    }
    private void saveContactInformation(ActionTitle actionTitle, OwnerDto dto, OwnerEntity entity){
        OwnerContactInformationMapper contactInformationMapper = new OwnerContactInformationMapperImpl();
        if (CollectionUtils.isNotEmpty(dto.getContacts())) {
            List<OwnerContactInformationEntity> contactInformationEntities = dto.getContacts()
                    .stream().map(contactInformationMapper::toEntity).toList();
            contactInformationEntities.forEach(contactInformationEntity -> contactInformationEntity.setOwner(entity));
            contactInformationRepository.saveAll(contactInformationEntities);
            actionPersistence.createAction(actionTitle);
        }else {
            log.warn("Owner contact information is empty");
        }
    }
    private void saveOwnerIdentification(ActionTitle actionTitle, OwnerDto dto, OwnerEntity entity){
        if (CollectionUtils.isNotEmpty(dto.getIdentifications())) {
            OwnerIdentificationMapper identificationMapper = new OwnerIdentificationMapperImpl();

            List<OwnerIdentificationEntity> identificationEntities = dto.getIdentifications()
                    .stream().map(identificationMapper::toEntity).toList();
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
