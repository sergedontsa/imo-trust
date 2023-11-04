package com.trust.gestion.handlers;

import com.trust.gestion.entities.TenantEntity;
import com.trust.gestion.enums.Status;
import com.trust.gestion.resources.TenantResource;
import com.trust.gestion.utilities.Utilities;

import java.time.Instant;
import java.util.Optional;

public class TenantHandler {
    public TenantEntity tenantHandler(TenantResource resource, Optional<TenantEntity> optionalEntity) {
        return optionalEntity.isPresent() ? this.updateTenant(resource, optionalEntity.get()) : this.createTenant(resource);
    }
    private TenantEntity createTenant(TenantResource resource) {
        return TenantEntity.builder()
                .id(Utilities.getTenantId())
                .firstName(resource.getFirstName())
                .lastName(resource.getLastName())
                .middleName(resource.getMiddleName())
                .gender(resource.getGender())
                .status(Status.HOLD)
                .description(resource.getDescription())
                .cityOfOrigin(resource.getCityOfOrigin())
                .dateOfBirth(resource.getDateOfBirth())
                .countryOfOrigin(resource.getCountryOfOrigin())
                .contactType(resource.getContactType())
                .registrationDate(Instant.now())
                .lastUpdated(Instant.now())
                .build();

    }

    private TenantEntity updateTenant(TenantResource resource, TenantEntity entity) {
        return null;

    }
}
