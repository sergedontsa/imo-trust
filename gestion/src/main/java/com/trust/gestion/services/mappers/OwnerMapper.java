/*
 * Copyright (c) 2023.  - Serge G. Dontsa
 */

package com.trust.gestion.services.mappers;


import com.trust.gestion.services.domain.OwnerDto;
import com.trust.gestion.services.entities.OwnerEntity;
import com.trust.gestion.services.pages.OwnerPageResponse;
import com.trust.gestion.services.resources.OwnerResource;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingInheritanceStrategy;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING,
        mappingInheritanceStrategy = MappingInheritanceStrategy.AUTO_INHERIT_ALL_FROM_CONFIG)
public interface OwnerMapper {
    OwnerDto fromResourceToDto(OwnerResource resource);
    OwnerEntity toEntity(OwnerDto ownerDto);
    OwnerDto toDto(OwnerEntity ownerEntity);
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    OwnerEntity update(OwnerDto ownerDto, @MappingTarget OwnerEntity ownerEntity);

    OwnerDto resourceToDto(OwnerResource resource);

    OwnerPageResponse toPageResponse(OwnerEntity entity);



}
