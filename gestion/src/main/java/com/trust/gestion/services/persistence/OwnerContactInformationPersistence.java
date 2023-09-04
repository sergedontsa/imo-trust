package com.trust.gestion.services.persistence;

import com.trust.gestion.services.domain.OwnerContactInformationDto;
import com.trust.gestion.services.mappers.OwnerContactInformationMapper;
import com.trust.gestion.services.repositories.OwnerContactInformationRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@AllArgsConstructor
public class OwnerContactInformationPersistence {
    private final OwnerContactInformationRepository repository;
    private final OwnerContactInformationMapper mapper;

    public OwnerContactInformationDto saved(OwnerContactInformationDto dto) {
        return this.mapper.toDto(this.repository.save(this.mapper.toEntity(dto)));
    }
}
