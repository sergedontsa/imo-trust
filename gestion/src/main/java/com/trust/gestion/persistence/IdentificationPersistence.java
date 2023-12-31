package com.trust.gestion.persistence;

import com.trust.gestion.domain.IdentificationDto;
import com.trust.gestion.mappers.IdentificationMapper;
import com.trust.gestion.mappers.IdentificationMapperImpl;
import com.trust.gestion.repositories.IdentificationRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@AllArgsConstructor
@Component
@Slf4j
public class IdentificationPersistence {
    private final IdentificationRepository repository;

    public void save(IdentificationDto dto){
        IdentificationMapper mapper = new IdentificationMapperImpl();
        this.repository.save(mapper.toEntity(dto));
    }
    public List<IdentificationDto> getByEntityId(String entityId){
        IdentificationMapper mapper = new IdentificationMapperImpl();
        return this.repository.findAllByEntityId(entityId)
                .stream()
                .map(mapper::toDto)
                .toList();
    }

    public void saveAll(List<IdentificationDto> dtos) {
        IdentificationMapper mapper = new IdentificationMapperImpl();
        this.repository.saveAll(dtos.stream()
                .map(mapper::toEntity)
                .toList());
    }
}
