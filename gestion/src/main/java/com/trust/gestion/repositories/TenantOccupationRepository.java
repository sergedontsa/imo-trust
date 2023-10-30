/*
 * Copyright (c) 2023.  - Serge G. Dontsa
 */

package com.trust.gestion.repositories;

import com.trust.gestion.entities.TenantOccupationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TenantOccupationRepository extends JpaRepository<TenantOccupationEntity, String> {
}
