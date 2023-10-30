/*
 * Copyright (c) 2023.  - Serge G. Dontsa
 */

package com.trust.gestion.repositories;


import com.trust.gestion.entities.ContractorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContractorRepository extends JpaRepository<ContractorEntity, String> {
}
