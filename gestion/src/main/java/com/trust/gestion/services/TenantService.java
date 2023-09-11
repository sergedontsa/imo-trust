/*
 * Copyright (c) 2023.  - Serge G. Dontsa
 */

package com.trust.gestion.services;

import com.trust.gestion.enums.Status;
import com.trust.gestion.exception.validators.TenantOnCreationValidation;
import com.trust.gestion.services.domain.TenantDto;
import com.trust.gestion.services.entities.ApartmentEntity;
import com.trust.gestion.services.entities.TenantApartmentEntity;
import com.trust.gestion.services.entities.TenantEntity;
import com.trust.gestion.services.handlers.Handler;
import com.trust.gestion.services.pages.PageResponse;
import com.trust.gestion.services.persistence.TenantPersistence;
import com.trust.gestion.services.repositories.ApartmentRepository;
import com.trust.gestion.services.repositories.TenantApartmentRepository;
import com.trust.gestion.services.repositories.TenantRepository;
import com.trust.gestion.services.resources.TenantResource;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;

import static java.util.Optional.empty;
import static java.util.Optional.of;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class TenantService {
    private final TenantRepository repository;
    private final ApartmentRepository apartmentRepository;
    private final TenantPersistence persistence;
    private final TenantApartmentRepository tenantApartmentRepository;

    public void create(TenantResource resource) {
        TenantOnCreationValidation validation = new TenantOnCreationValidation();
        validation.validate(resource, empty());
        ApartmentEntity apartmentEntity = this.apartmentRepository.findById(resource.getApartmentId())
                .orElseThrow(() -> new RuntimeException("Apartment with id " + resource.getApartmentId() + " not found"));

        if (Status.apartmentNotAvailable().contains(apartmentEntity.getStatus())){
            throw new RuntimeException("Apartment with id " + resource.getApartmentId() + " is not available");
        }else {

            Handler handler = new Handler();
            TenantDto dto = handler.tenantHandler(resource, empty());
            TenantDto saved = this.persistence.create(dto);
            TenantEntity entityFromBd = this.repository.findById(saved.getId())
                    .orElseThrow(() -> new RuntimeException("Tenant with id " + saved.getId() + " not found"));

            ApartmentEntity updated = apartmentEntity.toBuilder()
                    .status(Status.RESERVED)
                    .build();
            this.apartmentRepository.save(updated);
            this.tenantApartmentRepository.save(TenantApartmentEntity.builder()
                    .tenant(entityFromBd)
                    .apartment(apartmentEntity)
                    .lastUpdated(Instant.now())
                    .registrationDate(Instant.now())
                    .build());


        }
    }
    public void update(TenantResource resource, String id) {
        Handler handler = new Handler();
        TenantEntity entity = this.findById(id).orElseThrow(()->
                new RuntimeException("Tenant with id " + id + " not found"));
        TenantDto dto = handler.tenantHandler(resource, of(entity));
        this.persistence.create(dto);
    }
    private Optional<TenantEntity> findById(String id) {
        return this.repository.findById(id);
    }

    public PageResponse<TenantDto> getById(String id) {
        return null;
    }
}
