/*
 * Copyright (c) 2023.  - Serge G. Dontsa
 */

package com.trust.gestion.services.mappers;


import com.trust.gestion.services.domain.BuildingDto;
import com.trust.gestion.services.entities.BuildingEntity;
import com.trust.gestion.services.resources.BuildingResource;

import org.mapstruct.AfterMapping;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingInheritanceStrategy;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE,
        componentModel = MappingConstants.ComponentModel.SPRING,
        mappingInheritanceStrategy = MappingInheritanceStrategy.AUTO_INHERIT_ALL_FROM_CONFIG)
public interface BuildingMapper {

    @Mapping(target = "lastUpdated", ignore = true)
    @Mapping(target = "registrationDate", ignore = true)
    @Mapping(target = "apartments", ignore = true)
    BuildingDto fromResourceToDto(BuildingResource resource);

    BuildingEntity toEntity(BuildingDto buildingDto);

    BuildingDto toDto(BuildingEntity buildingEntity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    BuildingEntity partialUpdate(BuildingDto buildingDto, @MappingTarget BuildingEntity buildingEntity);


//    @AfterMapping
//    default void linkApartments(@MappingTarget BuildingEntity buildingEntity) {
//        buildingEntity.getApartments().forEach(apartment -> apartment.setBuildingEntity(buildingEntity));
//    }buildingEntity
}
