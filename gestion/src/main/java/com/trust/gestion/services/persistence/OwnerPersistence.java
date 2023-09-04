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
import com.trust.gestion.services.mappers.OwnerAddressMapperImpl;
import com.trust.gestion.services.mappers.OwnerContactInformationMapper;
import com.trust.gestion.services.mappers.OwnerContactInformationMapperImpl;
import com.trust.gestion.services.mappers.OwnerIdentificationMapper;
import com.trust.gestion.services.mappers.OwnerIdentificationMapperImpl;
import com.trust.gestion.services.mappers.OwnerInformationMapper;
import com.trust.gestion.services.mappers.OwnerInformationMapperImpl;
import com.trust.gestion.services.mappers.OwnerMapper;
import com.trust.gestion.services.mappers.OwnerMapperImpl;
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
    private final OwnerAddressRepository addressRepository;
    private final OwnerInformationRepository informationRepository;
    private final OwnerContactInformationRepository contactInformationRepository;
    private final OwnerIdentificationRepository identificationRepository;
    private final ActionPersistence actionPersistence;
    private final OwnerAddressPersistence addressPersistence;
    private final OwnerInformationPersistence informationPersistence;
    private final OwnerContactInformationPersistence contactInformationPersistence;
    private final OwnerIdentificationPersistence identificationPersistence;
    public OwnerDto saved(OwnerDto dto) {
        OwnerMapper mapper = new OwnerMapperImpl();
        if (this.findById(dto.getId()).isPresent()){
            OwnerEntity savedInBd = this.saveOwnerInBd(ActionTitle.OWNER_UPDATE, mapper.toEntity(dto));

            this.saveOwnerAddress(ActionTitle.OWNER_ADDRESS_UPDATE, dto, savedInBd);
            this.saveOwnerInformation(ActionTitle.OWNER_INFORMATION_UPDATE, dto, savedInBd);
            this.saveContactInformation(ActionTitle.OWNER_CONTACT_UPDATE, dto, savedInBd);
            this.saveOwnerIdentification(ActionTitle.OWNER_IDENTIFICATION_UPDATE, dto, savedInBd);

            return mapper.toDto(savedInBd);

        }else {
            OwnerEntity savedInBd = this.saveOwnerInBd(ActionTitle.OWNER_CREATE, mapper.toEntity(dto));

            this.saveOwnerAddress(ActionTitle.OWNER_ADDRESS_CREATE, dto, savedInBd);
            this.saveOwnerInformation(ActionTitle.OWNER_INFORMATION_CREATE, dto, savedInBd);
            this.saveContactInformation(ActionTitle.OWNER_CONTACT_CREATE, dto, savedInBd);
            this.saveOwnerIdentification(ActionTitle.OWNER_IDENTIFICATION_CREATE, dto, savedInBd);

            return mapper.toDto(savedInBd);
        }
    }
    private OwnerEntity saveOwnerInBd(ActionTitle actionTitle, OwnerEntity entity) {
        OwnerEntity saved = this.repository.save(entity);
        actionPersistence.createAction(actionTitle);
        return saved;
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
