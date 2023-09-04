package com.trust.gestion.services.persistence;

import com.trust.gestion.services.domain.OwnerInformationDto;
import com.trust.gestion.services.entities.OwnerInformationEntity;
import com.trust.gestion.services.mappers.OwnerInformationMapper;
import com.trust.gestion.services.repositories.OwnerInformationRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

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
