package com.trust.gestion.services;


import com.trust.gestion.domain.AddressDto;
import com.trust.gestion.domain.BuildingOwnerDto;
import com.trust.gestion.domain.OwnerDto;
import com.trust.gestion.domain.PersonDto;
import com.trust.gestion.domain.TelephoneDto;
import com.trust.gestion.entities.AddressEntity;
import com.trust.gestion.entities.BuildingEntity;
import com.trust.gestion.entities.BuildingOwnerEntity;
import com.trust.gestion.entities.OwnerEntity;
import com.trust.gestion.entities.PersonEntity;
import com.trust.gestion.exception.NoSuchElementFoundException;
import com.trust.gestion.handlers.OwnerHandler;
import com.trust.gestion.mappers.BuildingMapper;
import com.trust.gestion.mappers.BuildingMapperImpl;
import com.trust.gestion.mappers.OwnerMapper;
import com.trust.gestion.mappers.OwnerMapperImpl;
import com.trust.gestion.pages.PageResponse;
import com.trust.gestion.persistence.BuildingOwnerPersistence;
import com.trust.gestion.persistence.BuildingPersistence;
import com.trust.gestion.persistence.OwnerPersistence;
import com.trust.gestion.persistence.TelephonePersistence;
import com.trust.gestion.repositories.AddressRepository;
import com.trust.gestion.repositories.BuildingRepository;
import com.trust.gestion.repositories.OwnerRepository;
import com.trust.gestion.repositories.PersonRepository;
import com.trust.gestion.resources.AddressResource;
import com.trust.gestion.resources.OwnerResource;
import com.trust.gestion.resources.TelephoneResource;
import com.trust.gestion.resources.reponse.OwnerResponse;
import com.trust.gestion.utilities.AddressUtils;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Collections;
import java.util.List;

import static java.util.Optional.empty;

@Service
@RequiredArgsConstructor
@Transactional
public class OwnerService {
    private final OwnerRepository repository;
    private final BuildingRepository buildingRepository;
    private final OwnerPersistence persistence;
    private final PersonRepository personRepository;
    private final AddressRepository addressRepository;
    private final TelephonePersistence telephonePersistence;
    private final BuildingPersistence buildingPersistence;
    private final BuildingOwnerPersistence buildingOwnerPersistence;

    public PageResponse<OwnerResponse> getById(String id) {
        PageResponse<OwnerResponse> pageResponse = new PageResponse<>();
        OwnerMapper mapper = new OwnerMapperImpl();
        return pageResponse.toBuilder()
                .content(Collections.singletonList(mapper.toResponse(this.persistence.getOne(id))))
                .build();

    }
    public PageResponse<OwnerResponse> getAll(Integer page, Integer size){

        OwnerMapper mapper = new OwnerMapperImpl();
        return (new PageResponse<OwnerResponse>())
                .toBuilder()
                .content(this.persistence.getAll(page, size)
                        .getContent()
                        .stream()
                        .map(mapper::toResponse)
                        .toList())
                .build();

    }
    public void createAddress(String ownerId, List<AddressResource> resources){
        OwnerHandler handler = new OwnerHandler();

        AddressUtils.validateDuplicatedAddressType(resources);
        List<AddressDto> dtos = handler.addressHandler(this.findById(ownerId).getId(), resources, empty());
        List<AddressEntity> addressInBd = this.addressRepository.findByEntityId(ownerId);
        AddressUtils.validateAddressTypeAlreadyExist(resources, addressInBd);
        this.persistence.saveAddress(dtos);
    }


    public void create(OwnerResource resource) {
        BuildingEntity building = this.findBuildingById(resource.getBuildingId());
        this.validateBuilding(resource.getBuildingId());
        OwnerDto dto = (new OwnerHandler()).ownerHandler(resource, empty());
        building.setAssigned(Boolean.TRUE);
        this.buildingRepository.save(building);
        PersonDto person = this.getPerson(resource, dto.getId());
        this.persistence.saved(dto, person);

        BuildingMapper buildingMapper = new BuildingMapperImpl();
        BuildingOwnerDto buildingOwnerDto = BuildingOwnerDto.builder()
                .building(buildingMapper.toDto(building))
                .lastUpdated(Instant.now())
                .registrationDate(Instant.now())
                .owner(dto)
                .build();
        this.buildingOwnerPersistence.save(buildingOwnerDto);

    }
    private PersonDto getPerson(OwnerResource resource, String ownerId){
        return PersonDto.builder()
                .id(ownerId)
                .firstName(resource.getFirstName())
                .middleName(resource.getMiddleName())
                .lastName(resource.getLastName())
                .gender(resource.getGender())
                .registrationDate(Instant.now())
                .lastUpdated(Instant.now())
                .build();
    }
    private BuildingOwnerEntity.BuildingOwnerEntityBuilder getBuildingOwnerEntity() {
        return BuildingOwnerEntity.builder()
                .registrationDate(Instant.now())
                .lastUpdated(Instant.now());
    }
    private BuildingEntity findBuildingById(String id) {
        return this.buildingRepository.findById(id).orElseThrow(() -> new NoSuchElementFoundException("Building not found"));
    }
    private OwnerEntity findById(String id) {
        return this.repository.findById(id).orElseThrow(() -> new NoSuchElementFoundException("Owner not found"));
    }
    private PersonEntity getPersonEntity(String id){
        return this.personRepository.findById(id).orElseThrow(() -> new NoSuchElementFoundException("Person not found"));
    }
    private void validateBuilding(String id) {
        this.findBuildingById(id);

    }

    public void addTelephone(List<TelephoneResource> resources, String ownerId) {
        this.persistence.getOne(ownerId);
        resources.forEach(resource -> resource.setEntityId(ownerId));
        OwnerHandler handler = new OwnerHandler();
        List<TelephoneDto> entities = resources.stream()
                .map(resource -> handler.telephoneHandler(resource, empty()))
                .toList();

        this.telephonePersistence.saveAll(entities);

    }
}
