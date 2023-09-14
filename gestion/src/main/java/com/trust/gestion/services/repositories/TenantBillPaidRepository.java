package com.trust.gestion.services.repositories;

import com.trust.gestion.services.entities.TenantBillPaidEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TenantBillPaidRepository extends JpaRepository<TenantBillPaidEntity, String> {
}
