package com.trust.gestion.handlers;

import com.trust.gestion.entities.AddressEntity;
import com.trust.gestion.entities.OwnerEntity;
import com.trust.gestion.mappers.AddressMapperImpl;
import com.trust.gestion.resources.AddressResource;
import com.trust.gestion.resources.OwnerResource;
import com.trust.gestion.utilities.Utilities;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Component
@AllArgsConstructor
@Slf4j
public class OwnerHandler {
    public OwnerEntity ownerHandler(OwnerResource resource, Optional<OwnerEntity> optionalEntity) {
        return optionalEntity.isPresent() ? updateOwner(resource, optionalEntity.get()) : createOwner();
    }
    public List<AddressEntity> addressHandler(String ownerId, List<AddressResource> resources, Optional<AddressEntity> optionalEntity) {
        return optionalEntity.isPresent() ? updateAddress(resources, optionalEntity.get()) : createAddress(ownerId, resources);
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
    private List<AddressEntity> updateAddress(List<AddressResource> resources, AddressEntity entity) {
        return resources.stream()
                .map(resource -> (new AddressMapperImpl()).partialUpdate(resource, entity)
                        .toBuilder()
                        .id(entity.getId())
                        .entityId(entity.getEntityId())
                        .registrationDate(entity.getRegistrationDate())
                        .lastUpdated(Instant.now())
                        .build())
                .toList();
    }
    private List<AddressEntity> createAddress(String ownerId, List<AddressResource> resources) {

        return resources.stream()
                .map(resource -> (new AddressMapperImpl()).toEntity(resource)
                        .toBuilder()
                        .entityId(ownerId)
                        .registrationDate(Instant.now())
                        .lastUpdated(Instant.now())
                        .build())
                .toList();

    }
}
