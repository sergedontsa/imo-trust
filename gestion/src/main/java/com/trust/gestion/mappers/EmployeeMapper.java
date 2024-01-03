package com.trust.gestion.mappers;

import com.trust.gestion.domain.EmployeeDto;
import com.trust.gestion.domain.PersonDto;
import com.trust.gestion.entities.EmployeeEntity;
import com.trust.gestion.resources.reponse.EmployeeResponse;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface EmployeeMapper {
    EmployeeEntity toEntity(EmployeeDto employeeDto);

    @Mapping(target = "registrationDate",source = "entity.registrationDate", dateFormat = "yyyy-MM-dd")
    @Mapping(target = "lastUpdated",source = "entity.lastUpdated", dateFormat = "yyyy-MM-dd")
    EmployeeDto toDto(EmployeeEntity entity, PersonDto dto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    EmployeeEntity partialUpdate(EmployeeDto employeeDto, @MappingTarget EmployeeEntity employeeEntity);

    EmployeeResponse toResponse(EmployeeDto dto);
}
