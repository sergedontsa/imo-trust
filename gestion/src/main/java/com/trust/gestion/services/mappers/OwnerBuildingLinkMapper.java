package com.trust.gestion.services.mappers;

import com.trust.gestion.services.domain.OwnerBuildingLinkDto;
import com.trust.gestion.services.entities.OwnerBuildingLinkEntity;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingInheritanceStrategy;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING,
        mappingInheritanceStrategy = MappingInheritanceStrategy.AUTO_INHERIT_ALL_FROM_CONFIG)
public interface OwnerBuildingLinkMapper {
    OwnerBuildingLinkEntity toEntity(OwnerBuildingLinkDto ownerBuildingLinkDto);

    OwnerBuildingLinkDto toDto(OwnerBuildingLinkEntity ownerBuildingLinkEntity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    OwnerBuildingLinkEntity partialUpdate(OwnerBuildingLinkDto ownerBuildingLinkDto, @MappingTarget OwnerBuildingLinkEntity ownerBuildingLinkEntity);
}
