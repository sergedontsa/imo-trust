/*
 * Copyright (c) 2023.  - Serge G. Dontsa
 */

package com.trust.gestion.mappers;


import com.trust.gestion.domain.BuildingDto;
import com.trust.gestion.entities.BuildingEntity;
import com.trust.gestion.resources.BuildingResource;
import com.trust.gestion.resources.reponse.BuildingResponse;
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

    @Mapping(target = "apartments", ignore = true)
    BuildingEntity toEntity(BuildingDto buildingDto);

    BuildingDto toDto(BuildingEntity buildingEntity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "apartments", ignore = true)
    BuildingEntity partialUpdate(BuildingDto buildingDto, @MappingTarget BuildingEntity buildingEntity);

    BuildingResponse toResponse(BuildingEntity entity);
}
