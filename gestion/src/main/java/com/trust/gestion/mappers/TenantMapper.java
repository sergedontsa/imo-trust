/*
 * Copyright (c) 2023.  - Serge G. Dontsa
 */

package com.trust.gestion.mappers;


import com.trust.gestion.domain.TenantDto;
import com.trust.gestion.entities.TenantEntity;
import com.trust.gestion.resources.TenantResource;
import com.trust.gestion.resources.reponse.TenantResponse;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingInheritanceStrategy;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING,
        mappingInheritanceStrategy = MappingInheritanceStrategy.AUTO_INHERIT_ALL_FROM_CONFIG)
public interface TenantMapper {
    TenantDto fromResourceToDto(TenantResource resource);
    TenantEntity toEntity(TenantDto tenantDto);

    TenantDto toDto(TenantEntity tenantEntity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    TenantEntity partialUpdate(TenantDto tenantDto, @MappingTarget TenantEntity tenantEntity);

    TenantResponse toResponse(TenantEntity entity);
}
