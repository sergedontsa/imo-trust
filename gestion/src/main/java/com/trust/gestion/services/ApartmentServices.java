package com.trust.gestion.services;

import com.trust.gestion.domain.ApartmentDto;
import com.trust.gestion.domain.TenantDto;
import com.trust.gestion.entities.ApartmentEntity;
import com.trust.gestion.entities.BuildingEntity;
import com.trust.gestion.entities.TenantApartmentEntity;
import com.trust.gestion.exception.NoSuchElementFoundException;
import com.trust.gestion.handlers.ApartmentHandler;
import com.trust.gestion.mappers.ApartmentMapper;
import com.trust.gestion.mappers.ApartmentMapperImpl;
import com.trust.gestion.mappers.TenantMapperImpl;
import com.trust.gestion.pages.PageResponse;
import com.trust.gestion.persistence.ApartmentPersistence;
import com.trust.gestion.repositories.ApartmentRepository;
import com.trust.gestion.repositories.BuildingRepository;
import com.trust.gestion.repositories.TenantApartmentRepository;
import com.trust.gestion.resources.ApartmentResource;
import com.trust.gestion.utilities.ApartmentUtils;
import com.trust.gestion.utilities.BuildingUtils;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static java.util.Optional.empty;

@Service
@RequiredArgsConstructor
@Transactional
public class ApartmentServices  {
    private final ApartmentRepository repository;
    private final ApartmentPersistence persistence;
    private final BuildingRepository buildingRepository;
    private final TenantApartmentRepository tenantApartmentRepository;

    public PageResponse<ApartmentDto> getAll(Integer page, Integer size){
        ApartmentMapper mapper = new ApartmentMapperImpl();
        Page<ApartmentEntity> entities = this.repository.findAll(PageRequest.of(page, size));
        return (new PageResponse<ApartmentDto>()).toBuilder()
                .content(entities.getContent().stream().map(mapper::toDto).toList())
                .totalPages(entities.getTotalPages())
                .totalElements(entities.getTotalElements())
                .size(entities.getSize())
                .number(entities.getNumber())
                .build();
    }

    public PageResponse<ApartmentDto> getById(String id) {
        ApartmentEntity entity = this.findById(id);
        List<TenantApartmentEntity> tenantApartmentEntities = this.tenantApartmentRepository.findByApartment(entity);
        List<TenantDto> tenants = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(tenantApartmentEntities)){
            tenants = tenantApartmentEntities.stream()
                    .map(TenantApartmentEntity::getTenant)
                    .toList()
                    .stream()
                    .map(tenant -> (new TenantMapperImpl()).toDto(tenant))
                    .toList();

        }
        ApartmentDto apartmentDto = (new ApartmentMapperImpl()).toDto(entity);
        apartmentDto.setTenants(tenants);

        return (new PageResponse<ApartmentDto>())
                .toBuilder()
                .content(Collections.singletonList(apartmentDto))
                .build();

    }
    public void create(List<ApartmentResource> resources, String buildingId) {
        BuildingEntity building = this.findBuildingById(buildingId);

        BuildingUtils.validateBuildingOwner(building);
        BuildingUtils.validateNumberOfUnit(building);
        ApartmentUtils.validateDuplicateApartmentNumber(resources);
        ApartmentUtils.validateListOfApartment(building, resources);

        ApartmentHandler handler = new ApartmentHandler();
        List<ApartmentEntity> entity = resources.stream()
                .map(resource -> handler.apartmentHandle(resource, empty()))
                .toList();

        entity.forEach(apartment -> apartment.setBuilding(building));
        entity.forEach(this.persistence::save);
    }
    private BuildingEntity findBuildingById(String id) {
        return this.buildingRepository.findById(id).orElseThrow(() -> new NoSuchElementFoundException("No such element found"));
    }

    public void update(String id, ApartmentResource resource) {
    }

    public void delete(String id) {
        this.repository.delete(this.findById(id));
    }
    private ApartmentEntity findById(String id) {
        return this.repository.findById(id).orElseThrow(() -> new NoSuchElementFoundException("No such element found"));
    }
}
