/*
 * Copyright (c) 2023.  - Serge G. Dontsa
 */

package com.trust.gestion.services.persistence;

import com.trust.gestion.services.domain.OwnerDto;
import com.trust.gestion.services.entities.OwnerEntity;
import com.trust.gestion.services.mappers.OwnerMapper;
import com.trust.gestion.services.repositories.OwnerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@AllArgsConstructor
@Component
public class OwnerPersistence {
    private final OwnerRepository repository;
    private final OwnerMapper mapper;

    public OwnerDto saved(OwnerDto dto) {
        OwnerEntity entity = this.mapper.toEntity(dto);
        return this.mapper.toDto(this.repository.save(entity));
    }
    public void deleteById(String id) {
        this.findById(id).ifPresent(repository::delete);
    }
    private Optional<OwnerEntity> findById(String id) {
        return this.repository.findById(id);
    }
}
