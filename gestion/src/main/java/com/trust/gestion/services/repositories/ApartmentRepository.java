package com.trust.gestion.services.repositories;

import com.trust.gestion.services.entities.ApartmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApartmentRepository extends JpaRepository<ApartmentEntity, Integer> {
}
