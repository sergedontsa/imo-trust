package com.trust.gestion.repositories;

import com.trust.gestion.entities.ApartmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ApartmentRepository extends JpaRepository<ApartmentEntity, String> , JpaSpecificationExecutor<ApartmentEntity> {
}
