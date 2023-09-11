package com.trust.gestion.services.repositories;

import com.trust.gestion.services.entities.TenantApartmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TenantApartmentRepository extends JpaRepository<TenantApartmentEntity, Integer> {
}
