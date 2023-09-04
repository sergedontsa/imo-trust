package com.trust.gestion.services.persistence;

import com.trust.gestion.services.domain.OwnerIdentificationDto;
import com.trust.gestion.services.mappers.OwnerIdentificationMapper;
import com.trust.gestion.services.repositories.OwnerIdentificationRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@AllArgsConstructor
public class OwnerIdentificationPersistence {
    private final OwnerIdentificationRepository repository;
    private final OwnerIdentificationMapper mapper;

    public OwnerIdentificationDto saved(OwnerIdentificationDto dto) {
        return this.mapper.toDto(this.repository.save(this.mapper.toEntity(dto)));
    }

}
