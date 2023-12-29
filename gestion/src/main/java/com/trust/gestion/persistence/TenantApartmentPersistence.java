package com.trust.gestion.persistence;

import com.trust.gestion.domain.ApartmentDto;
import com.trust.gestion.domain.TenantApartmentDto;
import com.trust.gestion.domain.TenantDto;
import com.trust.gestion.entities.TenantApartmentEntity;
import com.trust.gestion.mappers.ApartmentMapper;
import com.trust.gestion.mappers.ApartmentMapperImpl;
import com.trust.gestion.mappers.TenantApartmentMapper;
import com.trust.gestion.mappers.TenantApartmentMapperImpl;
import com.trust.gestion.repositories.TenantApartmentRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.List;

@AllArgsConstructor
@Component
@Slf4j
public class TenantApartmentPersistence {
    private final TenantApartmentRepository repository;


    public List<TenantApartmentDto> buildTenantApartment(List<TenantDto> tenants, ApartmentDto apartment) {
        return tenants.stream()
                .map(tenant -> TenantApartmentDto.builder()
                        .apartment(apartment)
                        .tenant(tenant)
                        .registrationDate(Instant.now())
                        .lastUpdated(Instant.now())
                        .build())
                .toList();
    }

    public void saveAll(List<TenantApartmentDto> dtos) {
        TenantApartmentMapper mapper = new TenantApartmentMapperImpl();
        this.repository.saveAll(dtos.stream()
                .map(mapper::toEntity)
                .toList());
    }

    public List<TenantApartmentDto> getTenantApartmentByAptId(String apartmentId) {
        TenantApartmentMapper mapper = new TenantApartmentMapperImpl();
        return this.repository.findByApartmentId(apartmentId)
                .stream()
                .map(mapper::toDto)
                .toList();
    }
    public List<ApartmentDto> getTenantApartment(String tenantId){
        ApartmentMapper mapper = new ApartmentMapperImpl();

        return this.repository.findByTenantId(tenantId)
                .stream()
                .map(TenantApartmentEntity::getApartment)
                .toList()
                .stream()
                .map(mapper::toDto)
                .toList();
    }
}
