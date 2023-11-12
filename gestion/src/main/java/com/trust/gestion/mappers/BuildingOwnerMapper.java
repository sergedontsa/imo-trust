package com.trust.gestion.mappers;

import com.trust.gestion.domain.BuildingOwnerDto;
import com.trust.gestion.entities.BuildingOwnerEntity;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingInheritanceStrategy;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE,
        componentModel = MappingConstants.ComponentModel.SPRING,
        mappingInheritanceStrategy = MappingInheritanceStrategy.AUTO_INHERIT_ALL_FROM_CONFIG)
public interface BuildingOwnerMapper {
    BuildingOwnerEntity toEntity(BuildingOwnerDto buildingOwnerDto);

    BuildingOwnerDto toDto(BuildingOwnerEntity buildingOwnerEntity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    BuildingOwnerEntity partialUpdate(BuildingOwnerDto buildingOwnerDto, @MappingTarget BuildingOwnerEntity buildingOwnerEntity);
}
