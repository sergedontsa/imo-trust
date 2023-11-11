/*
 * Copyright (c) 2023.  - Serge G. Dontsa
 */

package com.trust.gestion.services;

import com.trust.gestion.entities.BuildingEntity;
import com.trust.gestion.exception.NoSuchElementFoundException;
import com.trust.gestion.exception.TrustImoException;
import com.trust.gestion.handlers.BuildingHandler;
import com.trust.gestion.mappers.BuildingMapper;
import com.trust.gestion.mappers.BuildingMapperImpl;
import com.trust.gestion.pages.PageResponse;
import com.trust.gestion.persistence.BuildingPersistence;
import com.trust.gestion.repositories.BuildingOwnerRepository;
import com.trust.gestion.repositories.BuildingRepository;
import com.trust.gestion.repositories.PersonRepository;
import com.trust.gestion.resources.BuildingResource;
import com.trust.gestion.resources.reponse.BuildingResponse;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

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
        PageResponse<BuildingResponse> pageResponse = new PageResponse<>();
        return pageResponse
                .toBuilder()
                .content(Collections.singletonList(this.persistence.getOne(id)))
                .build();
    }
    public PageResponse<BuildingResponse> getAll(Integer page, Integer size) {
        BuildingMapper mapper = new BuildingMapperImpl();
        Page<BuildingEntity> entities = this.repository.findAll(PageRequest.of(page, size));
        List<BuildingResponse> content = entities.getContent().stream()
                .map(building -> mapper.toResponse(building).toBuilder()
                        .owners(this.persistence.getBuildingOwner(building))
                        .apartments(this.persistence.getApartmentResponse(building))
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


}
