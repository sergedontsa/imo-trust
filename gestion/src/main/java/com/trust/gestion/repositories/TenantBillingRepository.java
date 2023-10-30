/*
 * Copyright (c) 2023.  - Serge G. Dontsa
 */

package com.trust.gestion.repositories;

import com.trust.gestion.entities.TenantBillingEntity;
import com.trust.gestion.entities.ApartmentEntity;
import com.trust.gestion.entities.TenantEntity;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TenantBillingRepository extends JpaRepository<TenantBillingEntity, String> {

    @Query("select t from TenantBillingEntity t where t.apartment = ?1 and t.tenant = ?2 and t.id = ?3")
    TenantBillingEntity findByApartmentAndTenantAndId(@NotNull ApartmentEntity apartment, @NotNull TenantEntity tenant, @Size(max = 20) String id);
}
