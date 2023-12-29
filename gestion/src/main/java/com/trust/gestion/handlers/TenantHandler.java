package com.trust.gestion.handlers;

import com.trust.gestion.domain.TelephoneDto;
import com.trust.gestion.domain.TenantDto;
import com.trust.gestion.entities.TelephoneEntity;
import com.trust.gestion.entities.TenantEntity;
import com.trust.gestion.enums.Status;
import com.trust.gestion.resources.TelephoneResource;
import com.trust.gestion.resources.TenantResource;
import com.trust.gestion.utilities.Utilities;

import java.time.Instant;
import java.util.Optional;

public class TenantHandler {
    public TenantDto tenantHandler(TenantResource resource, Optional<TenantEntity> optionalEntity) {
        return optionalEntity.isPresent() ? this.updateTenant(resource, optionalEntity.get()) : this.createTenant(resource);
    }
    private TenantDto createTenant(TenantResource resource) {
        return TenantDto.builder()
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

    private TenantDto updateTenant(TenantResource resource, TenantEntity entity) {
        return null;

    }

    public TelephoneDto telephoneHandler(TelephoneResource resource, Optional<TelephoneEntity> optionalTelephoneEntity) {
        return optionalTelephoneEntity.isPresent() ? this.updateTelephone(resource, optionalTelephoneEntity.get()) : this.createTelephone(resource);
    }

    private TelephoneDto createTelephone(TelephoneResource resource) {
        return TelephoneDto.builder()
                .entityId(resource.getEntityId())
                .areaCode(resource.getAreaCode())
                .number(resource.getNumber())
                .carrier(resource.getCarrier())
                .country(resource.getCountry())
                .registrationDate(Instant.now())
                .lastUpdated(Instant.now())
                .build();
    }

    private TelephoneDto updateTelephone(TelephoneResource resource, TelephoneEntity telephoneEntity) {
        return null;
    }
}
