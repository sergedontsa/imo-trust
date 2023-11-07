/*
 * Copyright (c) 2023.  - Serge G. Dontsa
 */

package com.trust.gestion.services;

import com.trust.gestion.entities.BuildingEntity;
import com.trust.gestion.entities.BuildingOwnerEntity;
import com.trust.gestion.entities.OwnerEntity;
import com.trust.gestion.entities.PersonEntity;
import com.trust.gestion.exception.NoSuchElementFoundException;
import com.trust.gestion.exception.TrustImoException;
import com.trust.gestion.handlers.BuildingHandler;
import com.trust.gestion.mappers.ApartmentMapperImpl;
import com.trust.gestion.mappers.BuildingMapper;
import com.trust.gestion.mappers.BuildingMapperImpl;
import com.trust.gestion.mappers.OwnerMapper;
import com.trust.gestion.mappers.OwnerMapperImpl;
import com.trust.gestion.pages.PageResponse;
import com.trust.gestion.persistence.BuildingPersistence;
import com.trust.gestion.repositories.BuildingOwnerRepository;
import com.trust.gestion.repositories.BuildingRepository;
import com.trust.gestion.repositories.PersonRepository;
import com.trust.gestion.resources.BuildingResource;
import com.trust.gestion.resources.reponse.ApartmentResponse;
import com.trust.gestion.resources.reponse.BuildingResponse;
import com.trust.gestion.resources.reponse.OwnerResponse;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.util.Optional.empty;

@Service
@RequiredArgsConstructor
@Transactional
public class BuildingService {
    private final BuildingRepository repository;
    private final BuildingPersistence persistence;
    private final BuildingOwnerRepository buildingOwnerRepository;
    private final PersonRepository personRepository;

    public PageResponse<BuildingResponse> getById(String id) throws TrustImoException {
        BuildingMapper mapper = new BuildingMapperImpl();

        BuildingEntity entity = this.findById(id);
        List<OwnerResponse> owners = this.getBuildingOwner(entity);
        BuildingResponse response = mapper.toResponse(entity).toBuilder().owners(owners)
                .apartments(this.getApartmentResponse(entity)).build();
        PageResponse<BuildingResponse> pageResponse = new PageResponse<>();
        return pageResponse
                .toBuilder()
                .content(Collections.singletonList(response))
                .build();
    }
    public PageResponse<BuildingResponse> getAll(Integer page, Integer size) {
        BuildingMapper mapper = new BuildingMapperImpl();
        Page<BuildingEntity> entities = this.repository.findAll(PageRequest.of(page, size));
        List<BuildingEntity> buildings = entities.getContent();
        List<BuildingResponse> content = buildings.stream()
                .map(building -> mapper.toResponse(building).toBuilder()
                        .owners(this.getBuildingOwner(building))
                        .apartments(this.getApartmentResponse(building))
                        .build())
                .toList();

       return (new PageResponse<BuildingResponse>()).toBuilder()
                .content(content)
                .totalPages(entities.getTotalPages())
                .totalElements(entities.getTotalElements())
                .size(entities.getSize())
                .number(entities.getNumber())
                .build();
    }
    private List<ApartmentResponse> getApartmentResponse(BuildingEntity entity) {
        return entity.getApartments()
                .stream()
                .map(apartment -> (new ApartmentMapperImpl()).toApartmentResponse(apartment))
                .toList();
    }

    public void create(BuildingResource resource) {
        this.persistence.save((new BuildingHandler().buildingHandler(resource, empty())));
    }

    public void update(BuildingResource resource, String id) throws TrustImoException {
        BuildingEntity entityInBd = this.findById(id);
        BuildingHandler handler = new BuildingHandler();
        BuildingEntity entity = handler.buildingHandler(resource, Optional.of(entityInBd));
        persistence.save(entity);
    }

    private BuildingEntity findById(String id) {
        return this.repository.findById(id).orElseThrow(() -> new NoSuchElementFoundException("Building not found"));
    }
    private List<OwnerResponse> getBuildingOwner(BuildingEntity entity) {
        OwnerMapper ownerMapper = new OwnerMapperImpl();
        List<OwnerEntity> owners = this.buildingOwnerRepository.findByBuilding(entity)
                .stream()
                .map(BuildingOwnerEntity::getOwner)
                .toList();
        return owners.stream()
                .collect(Collectors.toMap(ownerEntity -> ownerEntity,
                        ownerEntity -> this.getPersonEntity(ownerEntity.getId())))
                .entrySet()
                .stream()
                .map(entry -> ownerMapper.toResponse(entry.getKey(), entry.getValue()))
                .toList();
    }
    private PersonEntity getPersonEntity(String id) {
        return this.personRepository.findById(id).orElseThrow(() -> new NoSuchElementFoundException("Person not found"));
    }
}
