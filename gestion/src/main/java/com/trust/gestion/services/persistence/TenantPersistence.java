package com.trust.gestion.services.persistence;

import com.trust.gestion.services.domain.TenantDto;
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

    public void create(TenantDto dto) {
        this.repository.save((new TenantMapperImpl()).toEntity(dto));
    }
}
