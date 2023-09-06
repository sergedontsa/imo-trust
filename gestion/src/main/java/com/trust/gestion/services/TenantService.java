/*
 * Copyright (c) 2023.  - Serge G. Dontsa
 */

package com.trust.gestion.services;

import com.trust.gestion.services.domain.TenantDto;
import com.trust.gestion.services.entities.TenantEntity;
import com.trust.gestion.services.handlers.TenantHandler;
import com.trust.gestion.services.pages.PageResponse;
import com.trust.gestion.services.persistence.TenantPersistence;
import com.trust.gestion.services.repositories.ApartmentRepository;
import com.trust.gestion.services.repositories.TenantRepository;
import com.trust.gestion.services.resources.TenantResource;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static java.util.Optional.empty;

@Service
@RequiredArgsConstructor
@Transactional
public class TenantService {
    private final TenantRepository repository;
    private final ApartmentRepository apartmentRepository;
    private final TenantPersistence persistence;

    public void create(TenantResource resource) {
        TenantHandler handler = new TenantHandler();
        TenantDto dto = handler.handle(resource, empty());
        this.persistence.create(dto);
    }
    public void update(TenantResource resource, String id) {
        TenantHandler handler = new TenantHandler();
        TenantDto dto = handler.handle(resource, Optional.of(this.findById(id)));
        this.persistence.create(dto);
    }
    private TenantEntity findById(String id) {
        return this.repository.findById(id).orElseThrow(()-> new RuntimeException("Tenant not found"));
    }

    public PageResponse<TenantDto> getById(String id) {
        return null;
    }
}
