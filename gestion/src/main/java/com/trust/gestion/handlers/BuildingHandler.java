package com.trust.gestion.handlers;

import com.trust.gestion.entities.BuildingEntity;
import com.trust.gestion.enums.BuildingStatus;
import com.trust.gestion.domain.BuildingDto;
import com.trust.gestion.mappers.BuildingMapper;
import com.trust.gestion.mappers.BuildingMapperImpl;
import com.trust.gestion.resources.BuildingResource;
import com.trust.gestion.utilities.Utilities;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Optional;

@Component
@AllArgsConstructor
@Slf4j
public class BuildingHandler {
    public BuildingEntity buildingHandler(BuildingResource resource, Optional<BuildingEntity> optionalEntity) {
        return optionalEntity.isPresent() ? this.updateBuilding(resource, optionalEntity.get()) : this.createBuilding(resource);
    }
    private BuildingEntity updateBuilding(BuildingResource resource, BuildingEntity entity) {
        BuildingMapper mapper = new BuildingMapperImpl();
        BuildingDto dto = BuildingDto.builder()
                .designation(resource.getDesignation())
                .description(resource.getDescription())
                .category(resource.getCategory())
                .assigned(resource.getAssigned())
                .constructionYear(resource.getConstructionYear())
                .numberOfFloors(resource.getNumberOfFloors())
                .build();

        return mapper.partialUpdate(dto, entity)
                .toBuilder()
                .lastUpdated(Instant.now())
                .registrationDate(entity.getRegistrationDate())
                .status(entity.getStatus())
                .build();
    }
    //BUILDING
    private BuildingEntity createBuilding(BuildingResource resource) {
        return BuildingEntity.builder()
                .id(Utilities.getBuildingID())
                .description(resource.getDescription())
                .designation(resource.getDesignation())
                .category(resource.getCategory())
                .constructionYear(resource.getConstructionYear())
                .numberOfFloors(resource.getNumberOfFloors())
                .numberOfUnits(resource.getNumberOfUnits())
                .assigned(Boolean.FALSE)
                .status(BuildingStatus.PENDING)
                .registrationDate(Instant.now())
                .lastUpdated(Instant.now())
                .build();

    }
}
