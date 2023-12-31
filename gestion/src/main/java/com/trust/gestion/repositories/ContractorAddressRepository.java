/*
 * Copyright (c) 2023.  - Serge G. Dontsa
 */

package com.trust.gestion.repositories;



import com.trust.gestion.entities.ContractorAddress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContractorAddressRepository extends JpaRepository<ContractorAddress, Integer> {
}
