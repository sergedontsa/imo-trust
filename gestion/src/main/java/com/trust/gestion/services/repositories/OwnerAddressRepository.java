/*
 * Copyright (c) 2023.  - Serge G. Dontsa
 */

package com.trust.gestion.services.repositories;


import com.trust.gestion.services.entities.OwnerAddressEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OwnerAddressRepository extends JpaRepository<OwnerAddressEntity, Integer> {
}
