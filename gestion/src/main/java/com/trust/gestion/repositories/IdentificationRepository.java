package com.trust.gestion.repositories;

import com.trust.gestion.entities.IdentificationEntity;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface IdentificationRepository extends JpaRepository<IdentificationEntity, Integer> {

    @Query("select i from IdentificationEntity i where i.entityId = :entityId")
    List<IdentificationEntity> findAllByEntityId(@Size(max = 20) @Param("entityId") @NotNull String entityId);
}
