package com.trust.gestion.persistence;

import com.trust.gestion.entities.BuildingEntity;
import com.trust.gestion.entities.BuildingOwnerEntity;
import com.trust.gestion.entities.OwnerEntity;
import com.trust.gestion.repositories.BuildingOwnerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
@RequiredArgsConstructor
public class BuildingOwnerPersistence {
    private final BuildingOwnerRepository repository;

    public List<OwnerEntity> findByBuilding(BuildingEntity building){
        return this.repository.findByBuilding(building)
                .stream()
                .map(BuildingOwnerEntity::getOwner)
                .toList();
    }
}
