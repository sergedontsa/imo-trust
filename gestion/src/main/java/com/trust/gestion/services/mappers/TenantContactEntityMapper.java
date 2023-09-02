/*
 * Copyright (c) 2023.  - Serge G. Dontsa
 */

package com.trust.gestion.services.mappers;


import com.trust.gestion.services.domain.TenantContactDto;
import com.trust.gestion.services.entities.TenantContactEntity;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface TenantContactEntityMapper {
    TenantContactEntity toEntity(TenantContactDto tenantContactDto);

    TenantContactDto toDto(TenantContactEntity tenantContactEntity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    TenantContactEntity partialUpdate(TenantContactDto tenantContactDto, @MappingTarget TenantContactEntity tenantContactEntity);
}
