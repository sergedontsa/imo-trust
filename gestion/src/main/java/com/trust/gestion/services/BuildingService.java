/*
 * Copyright (c) 2023.  - Serge G. Dontsa
 */

package com.trust.gestion.services;

import com.trust.gestion.domain.BuildingDto;
import com.trust.gestion.entities.BuildingEntity;
import com.trust.gestion.handlers.BuildingHandler;
import com.trust.gestion.mappers.BuildingMapper;
import com.trust.gestion.services.mappers.BuildingMapperImpl;
import com.trust.gestion.pages.PageResponse;
import com.trust.gestion.persistence.BuildingPersistence;
import com.trust.gestion.repositories.BuildingRepository;
import com.trust.gestion.resources.BuildingResource;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
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

    public PageResponse<BuildingDto> getById(String id) {
        BuildingMapper mapper = new BuildingMapperImpl();
        PageResponse<BuildingDto> pageResponse = new PageResponse<>();
        return pageResponse
                .toBuilder()
                .content(Collections.singletonList(mapper.toDto(this.findById(id))))
                .build();
    }

    public PageResponse<BuildingDto> getAll(Integer page, Integer size){
        BuildingMapper mapper = new BuildingMapperImpl();
        Page<BuildingEntity> entities = this.repository.findAll(PageRequest.of(page, size));
        PageResponse<BuildingDto> contents = new PageResponse<>();
        return contents.toBuilder()
                .content(entities.getContent().stream().map(mapper::toDto).toList())
                .totalPages(entities.getTotalPages())
                .totalElements(entities.getTotalElements())
                .size(entities.getSize())
                .number(entities.getNumber())
                .build();
    }
    public void create(BuildingResource resource) {
        this.persistence.save((new BuildingHandler().buildingHandler(resource, empty())));
    }


    public void update(BuildingResource resource, String id) {
        BuildingEntity entityInBd = this.findById(id);
        BuildingHandler handler = new BuildingHandler();
        BuildingEntity entity = handler.buildingHandler(resource, Optional.of(entityInBd));
        persistence.save(entity);
    }

    private BuildingEntity findById(String id) {
        return this.repository.findById(id).orElseThrow(()-> new RuntimeException("Building not found"));
    }
}
