/*
 * Copyright (c) 2023.  - Serge G. Dontsa
 */

package com.trust.gestion.services.mappers;


import com.trust.gestion.services.domain.ContractorContactInformationDto;
import com.trust.gestion.services.entities.ContractorContactInformation;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING, uses = {ContractorMapper.class})
public interface ContractorContactInformationMapper {
    ContractorContactInformation toEntity(ContractorContactInformationDto contractorContactInformationDto);

    ContractorContactInformationDto toDto(ContractorContactInformation contractorContactInformation);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    ContractorContactInformation partialUpdate(ContractorContactInformationDto contractorContactInformationDto, @MappingTarget ContractorContactInformation contractorContactInformation);
}
