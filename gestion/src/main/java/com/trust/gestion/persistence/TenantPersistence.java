package com.trust.gestion.persistence;

import com.trust.gestion.entities.TenantEntity;
import com.trust.gestion.repositories.TenantRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
@Slf4j
public class TenantPersistence {
    private final TenantRepository repository;

    public void create(TenantEntity entity) {
        this.repository.save(entity);
    }
}
