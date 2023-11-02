package com.trust.gestion.repositories;

import com.trust.gestion.entities.BuildingEntity;
import com.trust.gestion.entities.BuildingOwnerEntity;
import com.trust.gestion.entities.OwnerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BuildingOwnerRepository extends JpaRepository<BuildingOwnerEntity, Integer> {

    @Query("SELECT b FROM BuildingOwnerEntity b WHERE b.building = :building")
    List<BuildingOwnerEntity> findByBuilding(@Param("building") BuildingEntity building);

    @Query("SELECT b FROM BuildingOwnerEntity b WHERE b.owner = :owner")
    List<BuildingOwnerEntity> findByOwner(@Param("owner") OwnerEntity owner);

    @Query("SELECT b FROM BuildingOwnerEntity b WHERE b.building.id = :buildingId")
    List<BuildingOwnerEntity> findByBuilding_Id(@Param("buildingId") String buildingId);
}
