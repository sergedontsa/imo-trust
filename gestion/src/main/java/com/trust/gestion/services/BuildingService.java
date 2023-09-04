/*
 * Copyright (c) 2023.  - Serge G. Dontsa
 */

package com.trust.gestion.services;

import com.trust.gestion.services.domain.BuildingDto;
import com.trust.gestion.services.entities.BuildingEntity;
import com.trust.gestion.services.handlers.BuildingHandler;
import com.trust.gestion.services.mappers.BuildingMapperImpl;
import com.trust.gestion.services.pages.PageResponse;
import com.trust.gestion.services.persistence.BuildingPersistence;
import com.trust.gestion.services.resources.BuildingResource;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import com.trust.gestion.services.mappers.BuildingMapper;
import com.trust.gestion.services.repositories.BuildingRepository;

import java.util.List;

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
        return pageResponse.toBuilder().content(List.of(mapper.toDto(this.findById(id)))).build();
    }
    public void create(BuildingResource resource) {
        BuildingHandler handler = new BuildingHandler();
        BuildingDto dto = handler.handle(resource,empty());
        this.persistence.save(dto);
    }

    private BuildingEntity findById(String id) {
        return this.repository.findById(id).orElseThrow(()-> new RuntimeException("Building not found"));
    }
}
