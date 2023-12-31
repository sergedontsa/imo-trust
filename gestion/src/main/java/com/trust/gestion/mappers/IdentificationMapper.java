package com.trust.gestion.mappers;

import com.trust.gestion.domain.IdentificationDto;
import com.trust.gestion.entities.IdentificationEntity;
import com.trust.gestion.resources.reponse.IdentificationResponse;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface IdentificationMapper {
    IdentificationEntity toEntity(IdentificationDto identificationDto);

    IdentificationDto toDto(IdentificationEntity identificationEntity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    IdentificationEntity partialUpdate(IdentificationDto identificationDto, @MappingTarget IdentificationEntity identificationEntity);

    IdentificationResponse toResponse(IdentificationDto dto);
}
