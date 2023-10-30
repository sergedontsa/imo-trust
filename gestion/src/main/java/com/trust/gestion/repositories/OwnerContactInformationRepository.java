/*
 * Copyright (c) 2023.  - Serge G. Dontsa
 */

package com.trust.gestion.repositories;


import com.trust.gestion.entities.OwnerContactInformationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OwnerContactInformationRepository extends JpaRepository<OwnerContactInformationEntity, Integer> {
}
