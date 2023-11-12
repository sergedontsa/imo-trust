package com.trust.gestion.persistence;

import com.trust.gestion.domain.TenantDto;
import com.trust.gestion.entities.TenantEntity;
import com.trust.gestion.exception.NoSuchElementFoundException;
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

    public void create(TenantDto dto) {
        TenantMapper mapper = new TenantMapperImpl();
        this.repository.save(mapper.toEntity(dto));
    }

    public TenantEntity findById(String id) {
        return this.repository.findById(id)
                .orElseThrow(() -> new NoSuchElementFoundException("Tenant not found"));
    }
}
