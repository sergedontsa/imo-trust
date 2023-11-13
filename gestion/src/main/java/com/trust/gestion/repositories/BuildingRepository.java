package com.trust.gestion.repositories;

import com.trust.gestion.entities.BuildingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BuildingRepository extends JpaRepository<BuildingEntity, String>{
    @Query("SELECT b FROM BuildingEntity b INNER JOIN b.apartments apartments WHERE apartments.id = ?1")
    BuildingEntity findByApartmentsId(String apartmentId);

}
