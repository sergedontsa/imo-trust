package com.trust.gestion.repositories;

import com.trust.gestion.entities.OwnerEntity;
import com.trust.gestion.entities.BuildingEntity;
import com.trust.gestion.entities.OwnerBuildingLinkEntity;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OwnerBuildingLinkRepository extends JpaRepository<OwnerBuildingLinkEntity, Integer> {

    @Query("select o from OwnerBuildingLinkEntity o where o.building = :building and o.owner = :owner")
    OwnerBuildingLinkEntity findOwnerBuildingLinkEntitiesByBuildingAndOwner(@Param("building") @NotNull BuildingEntity building, @Param("owner") @NotNull OwnerEntity owner);


    @Query("select o from OwnerBuildingLinkEntity o where o.building = :building and o.owner = :owner")
    List<OwnerBuildingLinkEntity> findAllByBuildingAndOwner(@Param("building") BuildingEntity building, @Param("owner") OwnerEntity owner);
}