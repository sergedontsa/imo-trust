/*
 * Copyright (c) 2023.  - Serge G. Dontsa
 */

package com.trust.gestion.handlers;

import com.trust.gestion.domain.ContractorDto;
import com.trust.gestion.entities.ContractorEntity;
import com.trust.gestion.repositories.ContractorRepository;
import com.trust.gestion.resources.ContractorResource;
import com.trust.gestion.utilities.Utilities;
import lombok.AllArgsConstructor;

import java.time.Instant;
import java.util.Optional;

@AllArgsConstructor
public class ContractorHandler {
    private final ContractorRepository contractorRepository;

    public ContractorDto handle(ContractorResource resource, Optional<ContractorEntity> optionalEntity) {
        return optionalEntity.isPresent() ? update(resource, optionalEntity.get()) : create(resource);
    }

    private ContractorDto create(ContractorResource resource) {
        return ContractorDto.builder()
                .id(Utilities.getEmployeeId())
                .firstName(resource.getFirstName())
                .middleName(resource.getMiddleName())
                .lastName(resource.getLastName())
                .specialization(resource.getSpecialization())
                .registrationDate(Instant.now())
                .lastUpdated(Instant.now())
                .build();
    }

    private ContractorDto update(ContractorResource resource, ContractorEntity entity) {
        return null;
    }
}
