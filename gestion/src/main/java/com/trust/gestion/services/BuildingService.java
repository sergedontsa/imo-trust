/*
 * Copyright (c) 2023.  - Serge G. Dontsa
 */

package com.trust.gestion.services;

import com.trust.gestion.domain.BuildingDto;
import com.trust.gestion.domain.OwnerDto;
import com.trust.gestion.entities.BuildingEntity;
import com.trust.gestion.entities.BuildingOwnerEntity;
import com.trust.gestion.handlers.BuildingHandler;
import com.trust.gestion.mappers.BuildingMapper;
import com.trust.gestion.mappers.BuildingMapperImpl;
import com.trust.gestion.mappers.OwnerMapper;
import com.trust.gestion.mappers.OwnerMapperImpl;
import com.trust.gestion.pages.PageResponse;
import com.trust.gestion.persistence.BuildingPersistence;
import com.trust.gestion.repositories.BuildingOwnerRepository;
import com.trust.gestion.repositories.BuildingRepository;
import com.trust.gestion.resources.BuildingResource;
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

    public PageResponse<BuildingDto> getById(String id) {
        BuildingMapper mapper = new BuildingMapperImpl();

        BuildingEntity entity = this.findById(id);
        List<OwnerDto> owners = this.getBuildingOwner(entity);

        BuildingDto dto = mapper.toDto(entity);
        dto.setOwners(owners);

        PageResponse<BuildingDto> pageResponse = new PageResponse<>();
        return pageResponse
                .toBuilder()
                .content(Collections.singletonList(dto))
                .build();
    }
    public PageResponse<BuildingDto> getAll(Integer page, Integer size) {

        Page<BuildingEntity> entities = this.repository.findAll(PageRequest.of(page, size));

        List<BuildingEntity> buildings = entities.getContent();
        List<BuildingDto> content = this.linkOwnerToBuildingDto(buildings);

        PageResponse<BuildingDto> contents = new PageResponse<>();
        return contents.toBuilder()
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

    public void update(BuildingResource resource, String id) {
        BuildingEntity entityInBd = this.findById(id);
        BuildingHandler handler = new BuildingHandler();
        BuildingEntity entity = handler.buildingHandler(resource, Optional.of(entityInBd));
        persistence.save(entity);
    }
    private List<BuildingDto> linkOwnerToBuildingDto(List<BuildingEntity> buildingEntities) {
        BuildingMapper mapper = new BuildingMapperImpl();
        OwnerMapper ownerMapper = new OwnerMapperImpl();
        return buildingEntities.stream()
                .collect(Collectors.toMap(buildingEntity -> buildingEntity,
                        buildingEntity -> this.buildingOwnerRepository.findByBuilding(buildingEntity)
                                .stream()
                                .map(BuildingOwnerEntity::getOwner)
                                .toList()))
                .entrySet()
                .stream()
                .collect(Collectors.toMap(entry -> mapper.toDto(entry.getKey()),
                        entry -> entry.getValue()
                                .stream()
                                .map(ownerMapper::toDto)
                                .toList()))
                .entrySet()
                .stream()
                .map(entry -> {
                    List<OwnerDto> owners = entry.getValue();
                    BuildingDto buildingDto = entry.getKey();
                    buildingDto.setOwners(owners);
                    return buildingDto;
                }).toList();
    }


    private BuildingEntity findById(String id) {
        return this.repository.findById(id).orElseThrow(() -> new RuntimeException("Building not found"));
    }
    private List<OwnerDto> getBuildingOwner(BuildingEntity entity) {
        OwnerMapper ownerMapper = new OwnerMapperImpl();
        return this.buildingOwnerRepository.findByBuilding(entity)
                .stream()
                .map(BuildingOwnerEntity::getOwner)
                .toList()
                .stream()
                .map(ownerMapper::toDto)
                .toList();
    }
}
