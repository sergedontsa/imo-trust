/*
 * Copyright (c) 2023.  - Serge G. Dontsa
 */

package com.trust.gestion.services.mappers;


import com.trust.gestion.services.domain.OwnerInformationDto;
import com.trust.gestion.services.entities.OwnerInformationEntity;
import com.trust.gestion.services.resources.OwnerInformationResource;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingInheritanceStrategy;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING,
        mappingInheritanceStrategy = MappingInheritanceStrategy.AUTO_INHERIT_ALL_FROM_CONFIG)
public interface OwnerInformationMapper {
    @Mapping(target = "registrationDate", ignore = true)
    @Mapping(target = "lastUpdated", ignore = true)
    OwnerInformationDto fromResourceToDto(OwnerInformationResource resource);
    OwnerInformationEntity toEntity(OwnerInformationDto ownerInformationDto);

    OwnerInformationDto toDto(OwnerInformationEntity ownerInformationEntity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    OwnerInformationEntity partialUpdate(OwnerInformationDto ownerInformationDto, @MappingTarget OwnerInformationEntity ownerInformationEntity);
}
