package com.trust.gestion.repositories;

import com.trust.gestion.entities.AddressEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddressRepository extends JpaRepository<AddressEntity, String> {

    @Query("select a from AddressEntity a where a.entityId = :entityId")
    List<AddressEntity> findByEntityId(@Param("entityId") String entityId);
}
