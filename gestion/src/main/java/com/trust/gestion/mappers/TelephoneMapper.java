package com.trust.gestion.mappers;

import com.trust.gestion.domain.TelephoneDto;
import com.trust.gestion.entities.TelephoneEntity;
import com.trust.gestion.resources.reponse.TelephoneResponse;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingInheritanceStrategy;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING,
        mappingInheritanceStrategy = MappingInheritanceStrategy.AUTO_INHERIT_ALL_FROM_CONFIG)
public interface TelephoneMapper {
    TelephoneEntity toEntity(TelephoneDto telephoneDto);

    TelephoneDto toDto(TelephoneEntity telephoneEntity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    TelephoneEntity partialUpdate(TelephoneDto telephoneDto, @MappingTarget TelephoneEntity telephoneEntity);

    TelephoneResponse toResponse(TelephoneEntity entity);
}
