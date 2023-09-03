/*
 * Copyright (c) 2023.  - Serge G. Dontsa
 */

package com.trust.gestion.services.mappers;


import com.trust.gestion.services.domain.OwnerIdentificationDto;
import com.trust.gestion.services.entities.OwnerIdentificationEntity;
import com.trust.gestion.services.resources.OwnerIdentificationRessource;
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
public interface OwnerIdentificationMapper {
    @Mapping(target = "registrationDate", ignore = true)
    @Mapping(target = "lastUpdated", ignore = true)
    OwnerIdentificationDto fromResourceToDto(OwnerIdentificationRessource resource);
    OwnerIdentificationEntity toEntity(OwnerIdentificationDto ownerIdentificationDto);

    OwnerIdentificationDto toDto(OwnerIdentificationEntity ownerIdentificationEntity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    OwnerIdentificationEntity partialUpdate(OwnerIdentificationDto ownerIdentificationDto, @MappingTarget OwnerIdentificationEntity ownerIdentificationEntity);
}
