package com.trust.gestion.persistence;

import com.trust.gestion.domain.TenantDto;
import com.trust.gestion.entities.TenantEntity;
import com.trust.gestion.exception.NoSuchElementFoundException;
import com.trust.gestion.mappers.TenantMapper;
import com.trust.gestion.mappers.TenantMapperImpl;
import com.trust.gestion.repositories.TenantRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@AllArgsConstructor
@Component
@Slf4j
public class TenantPersistence {
    private final TenantRepository repository;
    private final TenantApartmentPersistence tenantApartmentPersistence;

    public void create(TenantDto dto) {
        TenantMapper mapper = new TenantMapperImpl();
        this.repository.save(mapper.toEntity(dto));
    }
    public List<TenantDto> getAll(){
        TenantMapper mapper = new TenantMapperImpl();
        return this.repository.findAll()
                .stream()
                .map(mapper::toDto)
                .toList();
    }

    public TenantDto findById(String id) {
        TenantMapper mapper = new TenantMapperImpl();
        TenantEntity entity = this.repository.findById(id)
                .orElseThrow(() -> new NoSuchElementFoundException("Tenant not found"));

        return mapper.toDto(entity)
                .toBuilder()
                .apartments(this.tenantApartmentPersistence.getTenantApartment(id))
                .build();
    }
}
