package com.trust.gestion.utilities;

import com.trust.gestion.domain.BuildingDto;
import com.trust.gestion.entities.BuildingEntity;
import com.trust.gestion.enums.BuildingStatus;
import com.trust.gestion.exception.TrustImoException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class BuildingUtils {
    private BuildingUtils() {
    }
    public static void validateBuildingOwner(BuildingDto building){
        if (building.getAssigned() == Boolean.FALSE) {
            log.error("Building owner is not assigned");
            throw new TrustImoException("Building owner is not assigned");
        }
    }

    public static void validateNumberOfUnit(BuildingDto building) {
        if (building.getNumberOfUnits() == building.getApartments().size()) {
            log.error("Building is full");
            throw new TrustImoException("Building is full");
        }
    }
    public static void validateBuildingStatusOnAddTenant(BuildingEntity building) {
        if (building.getStatus() == BuildingStatus.OPEN) {
            log.error("Building is closed");
            throw new TrustImoException("Building is closed");
        }
    }

}
