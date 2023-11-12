package com.trust.gestion.handlers;

import com.trust.gestion.domain.AddressDto;
import com.trust.gestion.domain.OwnerDto;
import com.trust.gestion.domain.TelephoneDto;
import com.trust.gestion.entities.AddressEntity;
import com.trust.gestion.entities.OwnerEntity;
import com.trust.gestion.entities.TelephoneEntity;
import com.trust.gestion.resources.AddressResource;
import com.trust.gestion.resources.OwnerResource;
import com.trust.gestion.resources.TelephoneResource;
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
    public OwnerDto ownerHandler(OwnerResource resource, Optional<OwnerEntity> optionalEntity) {
        return optionalEntity.isPresent() ? updateOwner(resource, optionalEntity.get()) : createOwner();
    }
    public List<AddressDto> addressHandler(String ownerId, List<AddressResource> resources, Optional<AddressEntity> optionalEntity) {
        return optionalEntity.isPresent() ? updateAddress(resources, optionalEntity.get()) : createAddress(ownerId, resources);
    }
    private OwnerDto updateOwner(OwnerResource resource, OwnerEntity entity) {
        return null;
    }
    private OwnerDto createOwner() {
        return OwnerDto.builder()
                .id(Utilities.getEmployeeId())
                .registrationDate(Instant.now())
                .lastUpdated(Instant.now())
                .build();
    }
    private List<AddressDto> updateAddress(List<AddressResource> resources, AddressEntity entity) {
       return null;
    }
    private List<AddressDto> createAddress(String ownerId, List<AddressResource> resources) {
        return resources.stream()
                .map(resource -> AddressDto.builder()
                        .civicNumber(resource.getCivicNumber())
                        .streetName(resource.getStreetName())
                        .aptNumber(resource.getAptNumber())
                        .city(resource.getCity())
                        .province(resource.getProvince())
                        .postalCode(resource.getPostalCode())
                        .country(resource.getCountry())
                        .type(resource.getType())
                        .entityId(ownerId)
                        .registrationDate(Instant.now())
                        .lastUpdated(Instant.now())
                        .build())
                .toList();

    }

    public TelephoneDto telephoneHandler(TelephoneResource resource, Optional<TelephoneEntity> optionalTelephone) {
        return optionalTelephone.isPresent() ? this.updateTelephone(resource, optionalTelephone.get()) : this.createTelephone(resource);
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
