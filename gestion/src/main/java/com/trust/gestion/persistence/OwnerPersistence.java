/*
 * Copyright (c) 2023.  - Serge G. Dontsa
 */

package com.trust.gestion.persistence;

import com.trust.gestion.domain.AddressDto;
import com.trust.gestion.domain.OwnerDto;
import com.trust.gestion.domain.PersonDto;
import com.trust.gestion.entities.OwnerEntity;
import com.trust.gestion.entities.PersonEntity;
import com.trust.gestion.enums.ActionTitle;
import com.trust.gestion.exception.NoSuchElementFoundException;
import com.trust.gestion.mappers.OwnerMapper;
import com.trust.gestion.mappers.OwnerMapperImpl;
import com.trust.gestion.mappers.PersonMapper;
import com.trust.gestion.mappers.PersonMapperImpl;
import com.trust.gestion.pages.PageResponse;
import com.trust.gestion.repositories.OwnerRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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
    private final TelephonePersistence telephonePersistence;
    public void saved(OwnerDto ownerDto, PersonDto personDto) {
        PersonMapper personMapper = new PersonMapperImpl();
        OwnerMapper ownerMapper = new OwnerMapperImpl();
        this.saveOwnerInBd(ownerMapper.toEntity(ownerDto), personMapper.toEntity(personDto));

    }
    public void saveAddress(List<AddressDto> dtos){
        this.addressPersistence.saveAll(dtos);
    }
    private void saveOwnerInBd(OwnerEntity entity, PersonEntity person) {
        this.repository.save(entity);
        this.personPersistence.save(person);
        this.actionPersistence.createAction(ActionTitle.OWNER_CREATE);
    }
    public OwnerDto getOne(String ownerId){
        OwnerEntity entity = this.findById(ownerId);
        OwnerMapper mapper = new OwnerMapperImpl();
        PersonDto person = this.getPersonEntity(ownerId);
        List<AddressDto> address = this.addressPersistence.findByEntityId(ownerId);
        return mapper.toDto(entity, person)
                .toBuilder()
                .address(address)
                .telephones(this.telephonePersistence.getAllByEntityId(entity.getId()))
                .build();
    }
    public PageResponse<OwnerDto> getAll(Integer page, Integer size){
        OwnerMapper mapper = new OwnerMapperImpl();
        Page<OwnerEntity> pages = this.repository.findAll(PageRequest.of(page, size));
        List<OwnerDto> content = pages.getContent()
                .stream()
                .map(entity -> mapper.toDto(entity, this.getPersonEntity(entity.getId()))
                        .toBuilder()
                        .address(this.addressPersistence.findByEntityId(entity.getId()))
                        .telephones(this.telephonePersistence.getAllByEntityId(entity.getId()))
                .build())
                .toList();
         return (new PageResponse<OwnerDto>())
                 .toBuilder()
                 .content(content)
                 .totalPages(pages.getTotalPages())
                 .totalElements(pages.getTotalElements())
                 .size(pages.getSize())
                 .number(pages.getNumber())
                 .build();

    }
    private OwnerEntity findById(String id) {
        return this.repository.findById(id)
                .orElseThrow(() -> new NoSuchElementFoundException("Owner not found"));
    }
    private PersonDto getPersonEntity(String id) {
        return this.personPersistence.findById(id);
    }

}
