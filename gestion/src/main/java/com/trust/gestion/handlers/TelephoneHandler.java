package com.trust.gestion.handlers;

import com.trust.gestion.entities.TelephoneEntity;
import com.trust.gestion.resources.TelephoneResource;

import java.time.Instant;
import java.util.Optional;

public class TelephoneHandler {
    public TelephoneEntity telephoneHandler(TelephoneResource resource, Optional<TelephoneEntity> entity) {
        return entity.isPresent() ? updateTelephone(resource, entity.get()) : createTelephone(resource);

    }

    private TelephoneEntity createTelephone(TelephoneResource resource) {
        return TelephoneEntity.builder()
                .entityId(resource.getEntityId())
                .areaCode(resource.getAreaCode())
                .number(resource.getNumber())
                .carrier(resource.getCarrier())
                .country(resource.getCountry())
                .registrationDate(Instant.now())
                .lastUpdated(Instant.now())
                .build();
    }

    private TelephoneEntity updateTelephone(TelephoneResource resource, TelephoneEntity entity) {
        return null;
    }
}
