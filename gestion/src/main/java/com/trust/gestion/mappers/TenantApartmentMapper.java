package com.trust.gestion.mappers;


import com.trust.gestion.domain.TenantApartmentDto;
import com.trust.gestion.entities.TenantApartmentEntity;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingInheritanceStrategy;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING,
        mappingInheritanceStrategy = MappingInheritanceStrategy.AUTO_INHERIT_ALL_FROM_CONFIG)
public interface TenantApartmentMapper {
    TenantApartmentEntity toEntity(TenantApartmentDto tenantApartmentDto);

    TenantApartmentDto toDto(TenantApartmentEntity tenantApartmentEntity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    TenantApartmentEntity partialUpdate(TenantApartmentDto tenantApartmentDto, @MappingTarget TenantApartmentEntity tenantApartmentEntity);
}
