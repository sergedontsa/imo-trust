package com.trust.gestion.utilities;


import com.trust.gestion.domain.ApartmentDto;
import com.trust.gestion.domain.BuildingDto;
import com.trust.gestion.exception.TrustImoException;
import com.trust.gestion.resources.ApartmentResource;
import lombok.extern.slf4j.Slf4j;

import java.util.Collections;
import java.util.List;

@Slf4j
public class ApartmentUtils {
    private ApartmentUtils() {
    }
    public static void validateDuplicateApartmentNumber(List<ApartmentResource> resources) {
        resources.stream().filter(ap -> Collections.frequency(resources, ap) > 1)
                .findFirst()
                .ifPresent(apartmentResource -> {
                    log.error("Duplicate apartment number");
                    throw new TrustImoException("Duplicate apartment number");
                });
    }
    public static void validateListOfApartment(BuildingDto building, List<ApartmentResource> resources) {
        resources.forEach(resource -> {
            try {
                validateApartmentNumber(building, resource.getApartmentNumber());
            } catch (TrustImoException e) {
                log.error(e.getMessage());
                throw new TrustImoException(e.getMessage());
            }
        });
    }
    public static void validateApartmentNumber(BuildingDto dto, String apartmentNumber) throws TrustImoException {
        if (dto.getApartments().stream().anyMatch(apartment -> apartment.getApartmentNumber().equals(apartmentNumber))) {
            log.error("Apartment number already exist");
            throw new TrustImoException("Apartment number already exist");
        }
    }

    public static void validateApartmentStatusOnCreateTenant(ApartmentDto dto) throws TrustImoException {
        if (List.of("OCCUPIED", "CLOSED", "RESERVED").contains(dto.getStatus().name())) {
            log.error("Apartment is either occupied, closed or reserved");
            throw new TrustImoException("Apartment is either occupied, closed or reserved");
        }
    }
}
