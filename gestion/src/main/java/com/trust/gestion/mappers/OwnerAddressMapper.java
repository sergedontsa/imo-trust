/*
 * Copyright (c) 2023.  - Serge G. Dontsa
 */

package com.trust.gestion.mappers;


import com.trust.gestion.domain.OwnerAddressDto;
import com.trust.gestion.entities.OwnerAddressEntity;
import com.trust.gestion.resources.OwnerAddressResource;
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
public interface OwnerAddressMapper {
    OwnerAddressEntity toEntity(OwnerAddressDto ownerAddressDto);

    @Mapping(target = "registrationDate", ignore = true)
    @Mapping(target = "lastUpdated", ignore = true)
    OwnerAddressDto fromResourceToDto(OwnerAddressResource resource);
    OwnerAddressDto toDto(OwnerAddressEntity ownerAddressEntity);
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    OwnerAddressEntity partialUpdate(OwnerAddressDto ownerAddressDto, @MappingTarget OwnerAddressEntity ownerAddressEntity);

}
