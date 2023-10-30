package com.trust.gestion.handlers;

import com.trust.gestion.entities.ApartmentEntity;
import com.trust.gestion.entities.BuildingEntity;
import com.trust.gestion.enums.Status;
import com.trust.gestion.domain.ApartmentDto;
import com.trust.gestion.resources.ApartmentResource;
import com.trust.gestion.utilities.Utilities;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Component
@AllArgsConstructor
@Slf4j
public class ApartmentHandler {
    public ApartmentEntity apartmentHandle(ApartmentResource resource, Optional<ApartmentEntity> optionalEntity) {
        return optionalEntity.isPresent() ? this.updateApartment(resource, optionalEntity.get()) : this.createApartment(resource);
    }
    private ApartmentEntity createApartment(ApartmentResource resource) {
        return ApartmentEntity.builder()
                .id(Utilities.getApartmentID())
                .apartmentNumber(resource.getApartmentNumber())
                .numBedrooms(resource.getNumBedrooms())
                .squareFootage(resource.getSquareFootage())
                .rentAmount(resource.getRentAmount())
                .occupant(0)
                .description(resource.getDescription())
                .status(Status.PENDING)
                .registrationDate(Instant.now())
                .lastUpdated(Instant.now())
                .build();

    }
    private ApartmentEntity updateApartment(ApartmentResource resource, ApartmentEntity entity) {
        return null;
    }
    private List<ApartmentDto> updateApartments(List<ApartmentResource> resources, BuildingEntity entity) {

        return Collections.emptyList();
    }
}
