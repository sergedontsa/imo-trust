package com.trust.gestion.services.mappers;

import com.trust.gestion.services.domain.ActionDto;
import com.trust.gestion.services.entities.ActionEntity;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingInheritanceStrategy;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING,
        mappingInheritanceStrategy = MappingInheritanceStrategy.AUTO_INHERIT_ALL_FROM_CONFIG)
public interface ActionMapper {
    ActionEntity toEntity(ActionDto actionDto);

    ActionDto toDto(ActionEntity actionEntity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    ActionEntity partialUpdate(ActionDto actionDto, @MappingTarget ActionEntity actionEntity);
}
