/*
 * Copyright (c) 2023.  - Serge G. Dontsa
 */

package com.trust.gestion.services.mappers;


import com.trust.gestion.services.domain.TenantOccupationEntityDto;
import com.trust.gestion.services.entities.TenantOccupationEntity;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface TenantOccupationEntityMapper {
    TenantOccupationEntity toEntity(TenantOccupationEntityDto tenantOccupationEntityDto);

    TenantOccupationEntityDto toDto(TenantOccupationEntity tenantOccupationEntity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    TenantOccupationEntity partialUpdate(TenantOccupationEntityDto tenantOccupationEntityDto, @MappingTarget TenantOccupationEntity tenantOccupationEntity);
}
