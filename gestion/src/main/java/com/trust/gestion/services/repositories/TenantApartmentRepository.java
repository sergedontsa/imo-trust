package com.trust.gestion.services.repositories;

import com.trust.gestion.services.entities.ApartmentEntity;
import com.trust.gestion.services.entities.TenantApartmentEntity;
import com.trust.gestion.services.entities.TenantEntity;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TenantApartmentRepository extends JpaRepository<TenantApartmentEntity, Integer> {

    @Query("select t from TenantApartmentEntity t where t.tenant = :tenant and t.apartment = :apartment")
    List<TenantApartmentEntity> findAllByTenantAndApartment(@Param("tenant") @NotNull TenantEntity tenant, @Param("apartment") @NotNull ApartmentEntity apartment);
}
