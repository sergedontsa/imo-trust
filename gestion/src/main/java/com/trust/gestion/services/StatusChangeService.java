/*
 * Copyright (c) 2023.  - Serge G. Dontsa
 */

package com.trust.gestion.services;

import com.trust.gestion.enums.Status;
import com.trust.gestion.exception.NoSuchElementFoundException;
import com.trust.gestion.services.entities.ApartmentEntity;
import com.trust.gestion.services.entities.TenantEntity;
import com.trust.gestion.services.repositories.ApartmentRepository;
import com.trust.gestion.services.repositories.TenantApartmentRepository;
import com.trust.gestion.services.repositories.TenantRepository;
import com.trust.gestion.services.resources.StatusChangeRequestResource;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;

import static com.trust.gestion.enums.Status.ACTIVE;

@Service
@RequiredArgsConstructor
@Transactional
public class StatusChangeService {
    private final ApartmentRepository repository;
    private final TenantRepository tenantRepository;
    private final TenantApartmentRepository tenantApartmentRepository;
    public void updateStatus(StatusChangeRequestResource resource) {
        switch (resource.getEntity()) {
            case BUILDING -> this.updateBuildingStatus(resource);
            case APARTMENT -> this.updateApartmentStatus(resource);
            case TENANT -> this.updateTenantStatus(resource);
            case TENANT_APARTMENT -> this.updateTenantApartmentStatus(resource);
            case OWNER -> this.updateOwnerStatus(resource);
            case CONTRACTOR -> this.updateContractorStatus(resource);
            default -> throw  new IllegalArgumentException("Invalid entity type");
        }

    }

    /**
     *
     * @param resource
     */

    private void updateContractorStatus(StatusChangeRequestResource resource) {
    }

    /**
     *
     * @param resource
     */
    private void updateOwnerStatus(StatusChangeRequestResource resource) {
    }

    /**
     *
     * @param resource
     */
    private void updateBuildingStatus(StatusChangeRequestResource resource) {

    }

    /**
     *
     * @param resource
     */
    private void updateTenantApartmentStatus(StatusChangeRequestResource resource) {
    }

    /**
     *
     * @param resource resource
     */
    private void updateTenantStatus(StatusChangeRequestResource resource) {
        if (!Status.validTenantStatus().contains(resource.getStatus())){
            throw new IllegalArgumentException("Invalid status");
        }
        TenantEntity tenant  = tenantRepository.findById(resource.getId()).orElseThrow(
                () -> new NoSuchElementFoundException("Invalid tenant id")
        );
        TenantEntity entity = this.tenantRepository.findById(resource.getId())
                .orElseThrow(() -> new NoSuchElementFoundException("Invalid tenant id"));
        if (resource.getStatus() == ACTIVE) {
            tenantRepository.save(entity.toBuilder().status(resource.getStatus()).lastUpdated(Instant.now()).build());
        }

    }

    /**
     *
     * @param resource
     */
    private void updateApartmentStatus(StatusChangeRequestResource resource) {
        if (!Status.validApartmentStatus().contains(resource.getStatus())){
            throw new IllegalArgumentException("Invalid status");
        }
        ApartmentEntity entityInBd = repository.findById(resource.getId()).orElseThrow(
                () -> new NoSuchElementFoundException("Invalid apartment id")
        );
        if (entityInBd.getStatus() == Status.OCCUPIED){
            throw new IllegalArgumentException("Cannot change status of occupied apartment");
        }
        ApartmentEntity updated = entityInBd.toBuilder().status(resource.getStatus()).lastUpdated(Instant.now()).build();
        repository.save(updated);
    }

}
