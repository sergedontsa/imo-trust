package com.trust.gestion.handlers;

import com.trust.gestion.entities.OwnerEntity;
import com.trust.gestion.resources.OwnerResource;
import com.trust.gestion.utilities.Utilities;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Optional;

@Component
@AllArgsConstructor
@Slf4j
public class OwnerHandler {
    public OwnerEntity ownerHandler(OwnerResource resource, Optional<OwnerEntity> optionalEntity) {
        return optionalEntity.isPresent() ? updateOwner(resource, optionalEntity.get()) : createOwner();
    }

    private OwnerEntity updateOwner(OwnerResource resource, OwnerEntity entity) {
        return null;
    }

    private OwnerEntity createOwner() {
        return OwnerEntity.builder()
                .id(Utilities.getEmployeeId())
                .registrationDate(Instant.now())
                .lastUpdated(Instant.now())
                .build();
    }
}
