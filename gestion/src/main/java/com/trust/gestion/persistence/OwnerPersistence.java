/*
 * Copyright (c) 2023.  - Serge G. Dontsa
 */

package com.trust.gestion.persistence;

import com.trust.gestion.entities.AddressEntity;
import com.trust.gestion.entities.OwnerEntity;
import com.trust.gestion.entities.PersonEntity;
import com.trust.gestion.enums.ActionTitle;
import com.trust.gestion.exception.NoSuchElementFoundException;
import com.trust.gestion.mappers.OwnerMapper;
import com.trust.gestion.mappers.OwnerMapperImpl;
import com.trust.gestion.repositories.OwnerRepository;
import com.trust.gestion.resources.reponse.AddressResponse;
import com.trust.gestion.resources.reponse.OwnerResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@AllArgsConstructor
@Component
@Slf4j
public class OwnerPersistence {

    private final OwnerRepository repository;
    private final PersonPersistence personPersistence;
    private final AddressPersistence addressPersistence;
    private final ActionPersistence actionPersistence;
    public void saved(OwnerEntity entity, PersonEntity person) {
        this.saveOwnerInBd(entity, person);

    }
    public void saveAddress(List<AddressEntity> address){
        this.addressPersistence.saveAll(address);
    }
    private void saveOwnerInBd(OwnerEntity entity, PersonEntity person) {
        this.repository.save(entity);
        this.personPersistence.save(person);
        this.actionPersistence.createAction(ActionTitle.OWNER_CREATE);
    }
    public OwnerResponse getOne(String ownerId){
        OwnerEntity entity = this.findById(ownerId);
        OwnerMapper mapper = new OwnerMapperImpl();
        PersonEntity person = this.getPersonEntity(ownerId);
        List<AddressResponse> addressResponses = this.addressPersistence.findByEntity(ownerId);
        return mapper.toResponse(entity, person)
                .toBuilder()
                .address(addressResponses)
                .build();
    }
    private OwnerEntity findById(String id) {
        return this.repository.findById(id)
                .orElseThrow(() -> new NoSuchElementFoundException("Owner not found"));
    }
    private PersonEntity getPersonEntity(String id) {
        return this.personPersistence.findById(id);
    }

}
