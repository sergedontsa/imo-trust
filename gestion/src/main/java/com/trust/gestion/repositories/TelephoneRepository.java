package com.trust.gestion.repositories;

import com.trust.gestion.entities.TelephoneEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TelephoneRepository extends JpaRepository<TelephoneEntity, Integer> {
    @Query("SELECT t FROM TelephoneEntity t WHERE t.entityId = :entityId")
    List<TelephoneEntity> findByEntityId(@Param("entityId") String entityId);
}
