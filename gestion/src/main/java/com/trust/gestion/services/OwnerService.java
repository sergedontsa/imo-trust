package com.trust.gestion.services;


import com.trust.gestion.entities.BuildingOwnerEntity;
import com.trust.gestion.entities.PersonEntity;
import com.trust.gestion.exception.NoSuchElementFoundException;
import com.trust.gestion.domain.OwnerDto;
import com.trust.gestion.entities.BuildingEntity;
import com.trust.gestion.entities.OwnerBuildingLinkEntity;
import com.trust.gestion.entities.OwnerEntity;
import com.trust.gestion.handlers.OwnerHandler;
import com.trust.gestion.mappers.OwnerMapper;
import com.trust.gestion.mappers.OwnerMapperImpl;
import com.trust.gestion.pages.OwnerLinkResponse;
import com.trust.gestion.pages.PageResponse;
import com.trust.gestion.persistence.OwnerPersistence;
import com.trust.gestion.repositories.BuildingOwnerRepository;
import com.trust.gestion.repositories.BuildingRepository;
import com.trust.gestion.repositories.OwnerBuildingLinkRepository;
import com.trust.gestion.repositories.OwnerRepository;
import com.trust.gestion.repositories.PersonRepository;
import com.trust.gestion.resources.OwnerLinkResource;
import com.trust.gestion.resources.OwnerResource;
import com.trust.gestion.resources.reponse.OwnerResponse;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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
    private final OwnerBuildingLinkRepository linkRepository;
    private final BuildingOwnerRepository buildingOwnerRepository;
    private final PersonRepository personRepository;

    public PageResponse<OwnerResponse> getById(String id) {
        OwnerEntity entity = this.findById(id);
        PersonEntity person = this.getPersonEntity(id);
        OwnerMapper mapper = new OwnerMapperImpl();
        PageResponse<OwnerResponse> pageResponse = new PageResponse<>();
        return pageResponse.toBuilder().content(Collections.singletonList(mapper.toResponse(entity, person))).build();

    }
    public PageResponse<OwnerResponse> getAll(Integer page, Integer size){
        OwnerMapper mapper = new OwnerMapperImpl();
        Page<OwnerEntity> pages = this.repository.findAll(PageRequest.of(page, size));
        List<OwnerResponse> content = pages.getContent()
                .stream()
                .map(entity -> mapper.toResponse(entity, this.getPersonEntity(entity.getId())))
                .toList();
        return (new PageResponse<OwnerResponse>()).toBuilder()
                .content(content)
                .totalPages(pages.getTotalPages())
                .totalElements(pages.getTotalElements())
                .size(pages.getSize())
                .number(pages.getNumber())
                .build();

    }


    public void create(OwnerResource resource) {
        BuildingEntity building = this.findBuildingById(resource.getBuildingId());
        this.validateBuilding(resource.getBuildingId());
        OwnerEntity entity = (new OwnerHandler()).ownerHandler(resource, empty());
        building.setAssigned(Boolean.TRUE);
        this.buildingRepository.save(building);
        PersonEntity person = this.getPerson(resource, entity.getId());
        this.persistence.saved(entity, person);


        BuildingOwnerEntity buildingOwnerEntity = this.getBuildingOwnerEntity()
                .owner(entity)
                .building(building)
                .build();
        this.buildingOwnerRepository.save(buildingOwnerEntity);
    }
    private PersonEntity getPerson(OwnerResource resource, String ownerId){
        return PersonEntity.builder()
                .id(ownerId)
                .firstName(resource.getFirstName())
                .middleName(resource.getMiddleName())
                .lastName(resource.getLastName())
                .gender(resource.getGender())
                .registrationDate(Instant.now())
                .lastUpdated(Instant.now())
                .build();
    }

    public PageResponse<OwnerDto> update(String id, OwnerResource resource) {
        return null;
    }

    public void delete(String id) {
        // TODO will work on this
    }

    private BuildingOwnerEntity.BuildingOwnerEntityBuilder getBuildingOwnerEntity() {
        return BuildingOwnerEntity.builder()
                .registrationDate(Instant.now())
                .lastUpdated(Instant.now());
    }
    public OwnerLinkResponse linkOwnerToBuilding(OwnerLinkResource resource) {
        BuildingEntity building = this.findBuildingById(resource.getBuildingId());
        OwnerEntity owner = this.findById(resource.getOwnerId());
        if (building.getAssigned().equals(Boolean.TRUE)){
            List<OwnerBuildingLinkEntity> entities = this.linkRepository.findAllByBuildingAndOwner(building, owner);
            if (CollectionUtils.isNotEmpty(entities) && entities.get(0).getOwner().getId().equals(owner.getId())) {
                throw new NoSuchElementFoundException("Building already assigned");
            }else {
                OwnerBuildingLinkEntity newEntity = OwnerBuildingLinkEntity.builder()
                        .registrationDate(Instant.now())
                        .lastUpdated(Instant.now())
                        .building(building)
                        .owner(owner)
                        .build();
                this.linkRepository.save(newEntity);

            }
        }

        OwnerBuildingLinkEntity entity = OwnerBuildingLinkEntity.builder()
                .registrationDate(Instant.now())
                .lastUpdated(Instant.now())
                .building(building)
                .owner(owner)
                .build();
        building.setAssigned(Boolean.TRUE);
        this.buildingRepository.save(building);
        this.linkRepository.save(entity);
        return null;
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
}
