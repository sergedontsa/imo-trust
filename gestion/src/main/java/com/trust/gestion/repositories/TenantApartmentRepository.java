package com.trust.gestion.repositories;

import com.trust.gestion.entities.ApartmentEntity;
import com.trust.gestion.entities.TenantApartmentEntity;
import com.trust.gestion.entities.TenantEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TenantApartmentRepository extends JpaRepository<TenantApartmentEntity, Integer> {

    @Query("SELECT t FROM TenantApartmentEntity t WHERE t.apartment = ?1")
    List<TenantApartmentEntity> findByApartment(ApartmentEntity apartment);

    @Query("SELECT t FROM TenantApartmentEntity t WHERE t.apartment.id = :apartmentId")
    List<TenantApartmentEntity> findByApartmentId(@Param("apartmentId") String apartmentId);

    @Query("SELECT t FROM TenantApartmentEntity t WHERE t.tenant = :tenant")
    List<TenantApartmentEntity> findByTenant(@Param("tenant") TenantEntity tenant);

    @Query("SELECT t FROM TenantApartmentEntity t WHERE t.tenant.id = :tenantId")
    List<TenantApartmentEntity> findByTenantId(@Param("tenantId") String tenantId);
}
