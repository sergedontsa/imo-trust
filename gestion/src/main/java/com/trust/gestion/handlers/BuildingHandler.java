package com.trust.gestion.handlers;

import com.trust.gestion.domain.AddressDto;
import com.trust.gestion.domain.BuildingDto;
import com.trust.gestion.entities.AddressEntity;
import com.trust.gestion.entities.BuildingEntity;
import com.trust.gestion.enums.AddressType;
import com.trust.gestion.enums.BuildingStatus;
import com.trust.gestion.resources.AddressResource;
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
    public BuildingDto buildingHandler(BuildingResource resource, Optional<BuildingEntity> optionalEntity) {
        return optionalEntity.isPresent() ? this.updateBuilding(resource, optionalEntity.get()) : this.createBuilding(resource);
    }
    private BuildingDto updateBuilding(BuildingResource resource, BuildingEntity entity) {
        return BuildingDto.builder()
                .designation(resource.getDesignation())
                .description(resource.getDescription())
                .category(resource.getCategory())
                .assigned(resource.getAssigned())
                .constructionYear(resource.getConstructionYear())
                .numberOfFloors(resource.getNumberOfFloors())
                .build();

    }
    //BUILDING
    private BuildingDto createBuilding(BuildingResource resource) {
        return BuildingDto.builder()
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

    public AddressDto addressHandler(AddressResource resource, Optional<AddressEntity> optionalAddress) {
        return optionalAddress.isPresent() ? this.updateAddress(resource, optionalAddress.get()) : this.createAddress(resource);
    }

    private AddressDto createAddress(AddressResource resource) {
        return AddressDto.builder()
                .civicNumber(resource.getCivicNumber())
                .civicNumber(resource.getCivicNumber())
                .streetName(resource.getStreetName())
                .aptNumber(resource.getAptNumber())
                .city(resource.getCity())
                .province(resource.getProvince())
                .postalCode(resource.getPostalCode())
                .country(resource.getCountry())
                .type(AddressType.HOME)
                .registrationDate(Instant.now())
                .lastUpdated(Instant.now())
                .build();
    }

    private AddressDto updateAddress(AddressResource resource, AddressEntity o) {
        return null;
    }
}
