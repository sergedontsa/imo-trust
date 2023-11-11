package com.trust.gestion.mappers;

import com.trust.gestion.domain.AddressDto;
import com.trust.gestion.entities.AddressEntity;
import com.trust.gestion.resources.AddressResource;
import com.trust.gestion.resources.reponse.AddressResponse;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingInheritanceStrategy;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING,
        mappingInheritanceStrategy = MappingInheritanceStrategy.AUTO_INHERIT_ALL_FROM_CONFIG)
public interface AddressMapper {
    AddressEntity toEntity(AddressDto addressDto);

    AddressEntity toEntity(AddressResource resource);

    AddressDto toDto(AddressEntity addressEntity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    AddressEntity partialUpdate(AddressDto addressDto, @MappingTarget AddressEntity addressEntity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    AddressEntity partialUpdate(AddressResource resource, @MappingTarget AddressEntity addressEntity);

    AddressResponse toResponse(AddressEntity entity);
}
