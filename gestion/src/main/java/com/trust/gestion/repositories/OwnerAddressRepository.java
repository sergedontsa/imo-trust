/*
 * Copyright (c) 2023.  - Serge G. Dontsa
 */

package com.trust.gestion.repositories;


import com.trust.gestion.entities.OwnerAddressEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OwnerAddressRepository extends JpaRepository<OwnerAddressEntity, Integer> {
}
