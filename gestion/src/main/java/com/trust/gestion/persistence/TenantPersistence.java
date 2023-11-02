package com.trust.gestion.persistence;

import com.trust.gestion.domain.TenantDto;
import com.trust.gestion.entities.TenantEntity;
import com.trust.gestion.mappers.TenantMapper;
import com.trust.gestion.mappers.TenantMapperImpl;
import com.trust.gestion.repositories.TenantRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
@Slf4j
public class TenantPersistence {
    private final TenantRepository repository;

    public TenantDto create(TenantDto dto) {
        TenantMapper mapper = new TenantMapperImpl();
        TenantEntity entity = mapper.toEntity(dto);
        return mapper.toDto(this.repository.save(entity));
    }
}
