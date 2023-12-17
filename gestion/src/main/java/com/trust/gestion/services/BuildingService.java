/*
 * Copyright (c) 2023.  - Serge G. Dontsa
 */

package com.trust.gestion.services;

import com.trust.gestion.domain.AddressDto;
import com.trust.gestion.domain.BuildingDto;
import com.trust.gestion.entities.BuildingEntity;
import com.trust.gestion.exception.NoSuchElementFoundException;
import com.trust.gestion.exception.TrustImoException;
import com.trust.gestion.handlers.BuildingHandler;
import com.trust.gestion.mappers.BuildingMapper;
import com.trust.gestion.mappers.BuildingMapperImpl;
import com.trust.gestion.pages.PageResponse;
import com.trust.gestion.persistence.BuildingPersistence;
import com.trust.gestion.repositories.BuildingRepository;
import com.trust.gestion.resources.AddressResource;
import com.trust.gestion.resources.BuildingResource;
import com.trust.gestion.resources.reponse.BuildingResponse;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;

import static java.util.Optional.empty;

@Service
@RequiredArgsConstructor
@Transactional
public class BuildingService {
    private final BuildingRepository repository;
    private final BuildingPersistence persistence;

    public PageResponse<BuildingResponse> getById(String id) throws TrustImoException {
        BuildingDto dto = this.persistence.getOne(id);
        BuildingMapper mapper = new BuildingMapperImpl();
        BuildingResponse response = mapper.toResponse(dto);
        return PageResponse.<BuildingResponse>builder()
                .content(Collections.singletonList(response))
                .build();
    }
    public PageResponse<BuildingResponse> getAll(Integer page, Integer size) {
        return PageResponse.<BuildingResponse>builder()
                .content(this.persistence.getAll(PageRequest.of(page, size))
                        .stream()
                        .map((new BuildingMapperImpl())::toResponse)
                        .toList())
                .build();
    }


    public void create(BuildingResource resource) {
        BuildingHandler handler = new BuildingHandler();
        BuildingDto dto = handler.buildingHandler(resource, empty());
        this.persistence.save(dto);
    }

    public void update(BuildingResource resource, String id) throws TrustImoException {
        BuildingEntity entityInBd = this.findById(id);
        BuildingHandler handler = new BuildingHandler();
        BuildingDto entity = handler.buildingHandler(resource, Optional.of(entityInBd));
        persistence.save(entity);
    }

    private BuildingEntity findById(String id) {
        return this.repository.findById(id).orElseThrow(() -> new NoSuchElementFoundException("Building not found"));
    }


    public void createAddress(AddressResource resource, String buildingId) {

        BuildingDto building = this.persistence.findBuildingById(buildingId);
        BuildingHandler handler = new BuildingHandler();
        AddressDto dto = handler.addressHandler(resource, empty());
        dto.setEntityId(building.getId());
        this.persistence.createAddress(dto);
    }
}
