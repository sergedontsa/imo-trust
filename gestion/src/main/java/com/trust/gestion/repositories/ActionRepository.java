package com.trust.gestion.repositories;

import com.trust.gestion.entities.ActionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ActionRepository extends JpaRepository<ActionEntity, Integer> {
}
