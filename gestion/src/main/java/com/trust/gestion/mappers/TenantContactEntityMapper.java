/*
 * Copyright (c) 2023.  - Serge G. Dontsa
 */

package com.trust.gestion.mappers;


import com.trust.gestion.domain.TenantContactDto;
import com.trust.gestion.entities.TenantContactEntity;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingInheritanceStrategy;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING,
        mappingInheritanceStrategy = MappingInheritanceStrategy.AUTO_INHERIT_ALL_FROM_CONFIG)
public interface TenantContactEntityMapper {
    TenantContactEntity toEntity(TenantContactDto tenantContactDto);

    TenantContactDto toDto(TenantContactEntity tenantContactEntity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    TenantContactEntity partialUpdate(TenantContactDto tenantContactDto, @MappingTarget TenantContactEntity tenantContactEntity);
}
