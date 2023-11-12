package com.trust.gestion.persistence;

import com.trust.gestion.domain.ApartmentDto;
import com.trust.gestion.domain.TenantApartmentDto;
import com.trust.gestion.domain.TenantDto;
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


    public List<TenantApartmentDto> getTenantApartment(List<TenantDto> tenants, ApartmentDto apartment) {
        return tenants.stream()
                .map(tenant -> TenantApartmentDto.builder()
                        .apartment(apartment)
                        .tenant(tenant)
                        .registrationDate(Instant.now())
                        .lastUpdated(Instant.now())
                        .build())
                .toList();
    }

    public void saveAll(List<TenantApartmentDto> tenantApartmentEntities) {
    }

}
