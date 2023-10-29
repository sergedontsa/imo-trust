package com.trust.gestion.services;


import com.trust.gestion.exception.NoSuchElementFoundException;
import com.trust.gestion.services.domain.OwnerDto;
import com.trust.gestion.services.entities.BuildingEntity;
import com.trust.gestion.services.entities.OwnerBuildingLinkEntity;
import com.trust.gestion.services.entities.OwnerEntity;
import com.trust.gestion.services.mappers.OwnerMapperImpl;
import com.trust.gestion.services.pages.OwnerLinkResponse;
import com.trust.gestion.services.pages.PageResponse;
import com.trust.gestion.services.persistence.OwnerPersistence;
import com.trust.gestion.services.repositories.BuildingRepository;
import com.trust.gestion.services.repositories.OwnerBuildingLinkRepository;
import com.trust.gestion.services.repositories.OwnerRepository;
import com.trust.gestion.services.resources.OwnerLinkResource;
import com.trust.gestion.services.resources.OwnerResource;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class OwnerService {
    private final OwnerRepository repository;
    private final BuildingRepository buildingRepository;
    private final OwnerPersistence persistence;
    private final OwnerBuildingLinkRepository linkRepository;

    public PageResponse<OwnerDto> getById(String id) {

        PageResponse<OwnerDto> pageResponse = new PageResponse<>();
        return pageResponse.toBuilder().content(List.of(this.mapEntityToDto(this.findById(id)))).build();

    }
    public PageResponse<OwnerDto> getAll(Integer page, Integer size){
        Page<OwnerEntity> entities = this.repository.findAll(PageRequest.of(page, size));
        return (new PageResponse<OwnerDto>()).toBuilder()
                .content(entities.getContent().stream().map(this::mapEntityToDto).toList())
                .totalPages(entities.getTotalPages())
                .totalElements(entities.getTotalElements())
                .size(entities.getSize())
                .number(entities.getNumber())
                .build();

    }
    public void create(OwnerResource resource) {

    }

    public PageResponse<OwnerDto> update(String id, OwnerResource resource) {

        return null;
    }

    public void delete(String id) {
        // TODO will work on this
    }


    @Transactional
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
    private OwnerDto mapEntityToDto(OwnerEntity entity){
        return (new OwnerMapperImpl()).toDto(entity);
    }
}
