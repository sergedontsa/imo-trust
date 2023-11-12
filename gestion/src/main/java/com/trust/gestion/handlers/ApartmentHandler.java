package com.trust.gestion.handlers;

import com.trust.gestion.domain.ApartmentDto;
import com.trust.gestion.entities.ApartmentEntity;
import com.trust.gestion.enums.Status;
import com.trust.gestion.resources.ApartmentResource;
import com.trust.gestion.utilities.Utilities;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Optional;

@Component
@AllArgsConstructor
@Slf4j
public class ApartmentHandler {
    public ApartmentDto apartmentHandle(ApartmentResource resource, Optional<ApartmentEntity> optionalEntity) {
        return optionalEntity.isPresent() ? this.updateApartment(resource, optionalEntity.get()) : this.createApartment(resource);
    }
    private ApartmentDto createApartment(ApartmentResource resource) {
        return ApartmentDto.builder()
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
    private ApartmentDto updateApartment(ApartmentResource resource, ApartmentEntity entity) {
        return null;
    }

}
