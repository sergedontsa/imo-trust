package com.trust.gestion.persistence;

import com.trust.gestion.domain.TelephoneDto;
import com.trust.gestion.entities.TelephoneEntity;
import com.trust.gestion.mappers.TelephoneMapper;
import com.trust.gestion.mappers.TelephoneMapperImpl;
import com.trust.gestion.repositories.TelephoneRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@AllArgsConstructor
@Component
@Slf4j
public class TelephonePersistence {
    private final TelephoneRepository repository;
    public List<TelephoneDto> getAllByEntityId(String entityId){
        TelephoneMapper mapper = new TelephoneMapperImpl();
        return this.repository.findByEntityId(entityId)
                .stream()
                .map(mapper::toDto)
                .toList();
    }

    public void saveAll(List<TelephoneDto> dtos) {
        TelephoneMapper mapper = new TelephoneMapperImpl();
        List<TelephoneEntity> entities = dtos.stream()
                .map(mapper::toEntity)
                .toList();
        this.repository.saveAll(entities);
    }
}
