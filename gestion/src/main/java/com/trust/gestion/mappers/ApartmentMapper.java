/*
 * Copyright (c) 2023.  - Serge G. Dontsa
 */

package com.trust.gestion.mappers;


import com.trust.gestion.domain.ApartmentDto;
import com.trust.gestion.resources.ApartmentResource;
import com.trust.gestion.entities.ApartmentEntity;
import com.trust.gestion.resources.reponse.ApartmentResponse;
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

    @Mapping(target = "building.apartments", ignore = true)
    @Mapping(target = "building.assigned", ignore = true)
    @Mapping(target = "building.status", ignore = true)
    @Mapping(target = "building.owners", ignore = true)
    ApartmentDto toDto(ApartmentEntity apartmentEntity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    ApartmentEntity partialUpdate(ApartmentDto apartmentDto, @MappingTarget ApartmentEntity apartmentEntity);

    ApartmentDto fromResourceToDto(ApartmentResource resource);

    @Mapping(target = "building.apartments", ignore = true)
    @Mapping(target = "building.assigned", ignore = true)
    @Mapping(target = "building.status", ignore = true)
    @Mapping(target = "building.owners", ignore = true)
    ApartmentResponse toResponse(ApartmentEntity entity);

    ApartmentResponse toResponse(ApartmentDto dto);
}
