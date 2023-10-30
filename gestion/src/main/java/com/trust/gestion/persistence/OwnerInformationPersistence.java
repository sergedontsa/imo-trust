package com.trust.gestion.persistence;

import com.trust.gestion.domain.OwnerInformationDto;
import com.trust.gestion.mappers.OwnerInformationMapper;
import com.trust.gestion.repositories.OwnerInformationRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
@Slf4j
public class OwnerInformationPersistence {
    private OwnerInformationRepository repository;
    private final OwnerInformationMapper mapper;

    public OwnerInformationDto saved(OwnerInformationDto dto){
        return this.mapper.toDto(this.repository.save(this.mapper.toEntity(dto)));
    }

}
