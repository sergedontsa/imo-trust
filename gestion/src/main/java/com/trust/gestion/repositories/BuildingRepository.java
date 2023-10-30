package com.trust.gestion.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.trust.gestion.entities.BuildingEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface BuildingRepository extends JpaRepository<BuildingEntity, String>{

}
