package com.trust.gestion.services.persistence;

import com.trust.gestion.services.domain.TenantDto;
import com.trust.gestion.services.entities.TenantEntity;
import com.trust.gestion.services.mappers.TenantMapper;
import com.trust.gestion.services.mappers.TenantMapperImpl;
import com.trust.gestion.services.repositories.TenantRepository;
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
