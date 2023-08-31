/*
 * Copyright (c) 2023.  - Serge G. Dontsa
 */

package com.trust.gestion.services.mappers;


import com.trust.gestion.services.domain.TenantBillingDto;
import com.trust.gestion.services.entities.TenantBilling;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface TenantBillingMapper {
    TenantBilling toEntity(TenantBillingDto tenantBillingDto);

    TenantBillingDto toDto(TenantBilling tenantBilling);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    TenantBilling partialUpdate(TenantBillingDto tenantBillingDto, @MappingTarget TenantBilling tenantBilling);
}
