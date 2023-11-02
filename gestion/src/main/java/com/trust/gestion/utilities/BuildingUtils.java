package com.trust.gestion.utilities;

import com.trust.gestion.entities.BuildingEntity;
import com.trust.gestion.resources.ApartmentResource;

import java.util.List;

public class BuildingUtils {
    private BuildingUtils() {
    }
    public static void validateBuildingOwner(BuildingEntity entity){
        if (entity.getAssigned() == Boolean.FALSE) {
            throw new RuntimeException("Building owner is not assigned");
        }
    }
    public static void validateListOfApartment(BuildingEntity building, List<ApartmentResource> resources) {
        resources.forEach(resource -> validateApartmentNumber(building, resource.getApartmentNumber()));
    }
    public static void validateApartmentNumber(BuildingEntity building, String apartmentNumber){
        if (building.getApartments().stream().anyMatch(apartment -> apartment.getApartmentNumber().equals(apartmentNumber))) {
            throw new RuntimeException("Apartment number already exist");
        }
    }
    public static void validateNumberOfUnit(BuildingEntity building) {
        if (building.getNumberOfUnits() == building.getApartments().size()) {
            throw new RuntimeException("Building is full");
        }
    }
}
