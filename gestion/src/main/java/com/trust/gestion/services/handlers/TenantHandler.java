package com.trust.gestion.services.handlers;

import com.trust.gestion.enums.Status;
import com.trust.gestion.services.domain.TenantDto;
import com.trust.gestion.services.entities.TenantEntity;
import com.trust.gestion.services.mappers.TenantMapper;
import com.trust.gestion.services.mappers.TenantMapperImpl;
import com.trust.gestion.services.resources.TenantResource;
import com.trust.gestion.utilities.Utilities;

import java.time.Instant;
import java.util.Optional;

public class TenantHandler {
    public TenantDto handle(TenantResource resource, Optional<TenantEntity> optionalEntity){
        return optionalEntity.isPresent() ? update(resource, optionalEntity.get()) : create(resource);
    }
    private TenantDto create(TenantResource resource){
        TenantMapper mapper = new TenantMapperImpl();
        TenantDto dto = mapper.fromResourceToDto(resource);
        return dto.toBuilder()
                .id(Utilities.getTenantId())
                .status(Status.PENDING)
                .lastUpdated(Instant.now())
                .registrationDate(Instant.now())
                .build();

    }
    private TenantDto update(TenantResource resource, TenantEntity entity){
        return null;

    }
}
