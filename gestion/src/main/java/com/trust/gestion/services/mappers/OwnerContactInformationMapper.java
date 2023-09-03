/*
 * Copyright (c) 2023.  - Serge G. Dontsa
 */

package com.trust.gestion.services.mappers;


import com.trust.gestion.services.domain.OwnerContactInformationDto;
import com.trust.gestion.services.entities.OwnerContactInformationEntity;
import com.trust.gestion.services.resources.OwnerContactInformationRessource;
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
public interface OwnerContactInformationMapper {

    @Mapping(target = "registrationDate", ignore = true)
    @Mapping(target = "lastUpdated", ignore = true)
    OwnerContactInformationDto fromResourceToDto(OwnerContactInformationRessource resource);
    OwnerContactInformationEntity toEntity(OwnerContactInformationDto ownerContactInformationDto);

    OwnerContactInformationDto toDto(OwnerContactInformationEntity ownerContactInformationEntity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    OwnerContactInformationEntity partialUpdate(OwnerContactInformationDto ownerContactInformationDto, @MappingTarget OwnerContactInformationEntity ownerContactInformationEntity);
}
