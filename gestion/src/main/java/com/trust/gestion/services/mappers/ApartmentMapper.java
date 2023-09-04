/*
 * Copyright (c) 2023.  - Serge G. Dontsa
 */

package com.trust.gestion.services.mappers;


import com.trust.gestion.services.domain.ApartmentDto;
import com.trust.gestion.services.entities.ApartmentEntity;
import com.trust.gestion.services.resources.ApartmentResource;
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
public interface ApartmentMapper {
    ApartmentEntity toEntity(ApartmentDto apartmentDto);

    ApartmentDto toDto(ApartmentEntity apartmentEntity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    ApartmentEntity partialUpdate(ApartmentDto apartmentDto, @MappingTarget ApartmentEntity apartmentEntity);

    @Mapping(target = "registrationDate", ignore = true)
    @Mapping(target = "lastUpdated", ignore = true)
    ApartmentDto fromResourceToDto(ApartmentResource resource);
}
