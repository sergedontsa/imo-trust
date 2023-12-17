/*
 * Copyright (c) 2023.  - Serge G. Dontsa
 */

package com.trust.gestion.mappers;


import com.trust.gestion.domain.OwnerDto;
import com.trust.gestion.domain.PersonDto;
import com.trust.gestion.entities.OwnerEntity;
import com.trust.gestion.resources.OwnerResource;
import com.trust.gestion.resources.reponse.OwnerResponse;
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
public interface OwnerMapper {
    @Mapping(target = "registrationDate", ignore = true)
    @Mapping(target = "lastUpdated", ignore = true)
    OwnerDto fromResourceToDto(OwnerResource resource);

    OwnerEntity toEntity(OwnerDto ownerDto);

    OwnerDto toDto(OwnerEntity ownerEntity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    OwnerEntity update(OwnerDto ownerDto, @MappingTarget OwnerEntity ownerEntity);

    @Mapping(target = "id", source = "entity.id")
    @Mapping(target = "registrationDate", ignore = true)
    @Mapping(target = "lastUpdated", ignore = true)
    OwnerDto toDto(OwnerEntity entity, PersonDto personDto);

    OwnerResponse toResponse(OwnerDto owner);


}
