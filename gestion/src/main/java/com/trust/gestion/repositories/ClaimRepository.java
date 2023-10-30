/*
 * Copyright (c) 2023.  - Serge G. Dontsa
 */

package com.trust.gestion.repositories;


import com.trust.gestion.entities.Claim;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClaimRepository extends JpaRepository<Claim, String> {
}
