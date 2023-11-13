package com.trust.gestion.services;

import com.trust.gestion.domain.ApartmentDto;
import com.trust.gestion.domain.BuildingDto;
import com.trust.gestion.domain.TenantApartmentDto;
import com.trust.gestion.entities.ApartmentEntity;
import com.trust.gestion.exception.NoSuchElementFoundException;
import com.trust.gestion.handlers.ApartmentHandler;
import com.trust.gestion.mappers.ApartmentMapper;
import com.trust.gestion.mappers.ApartmentMapperImpl;
import com.trust.gestion.mappers.TenantMapper;
import com.trust.gestion.mappers.TenantMapperImpl;
import com.trust.gestion.pages.PageResponse;
import com.trust.gestion.persistence.ApartmentPersistence;
import com.trust.gestion.persistence.BuildingPersistence;
import com.trust.gestion.persistence.TenantApartmentPersistence;
import com.trust.gestion.repositories.ApartmentRepository;
import com.trust.gestion.repositories.BuildingRepository;
import com.trust.gestion.repositories.TenantApartmentRepository;
import com.trust.gestion.resources.ApartmentResource;
import com.trust.gestion.resources.reponse.ApartmentResponse;
import com.trust.gestion.resources.reponse.TenantResponse;
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
    private final TenantApartmentPersistence tenantApartmentPersistence;
    private final BuildingPersistence buildingPersistence;

    public PageResponse<ApartmentResponse> getAll(Integer page, Integer size){
        ApartmentMapper mapper = new ApartmentMapperImpl();
        Page<ApartmentEntity> entities = this.repository.findAll(PageRequest.of(page, size));
        return (new PageResponse<ApartmentResponse>()).toBuilder()
                .content(entities.getContent().stream().map(mapper::toResponse).toList())
                .totalPages(entities.getTotalPages())
                .totalElements(entities.getTotalElements())
                .size(entities.getSize())
                .number(entities.getNumber())
                .build();
    }

    public PageResponse<ApartmentResponse> getById(String id) {
        ApartmentDto apartment = this.persistence.findApartment(id);
        List<TenantApartmentDto> tenantApartment = this.tenantApartmentPersistence.getTenantApartment(apartment.getId());
        List<TenantResponse> tenants = new ArrayList<>();
        TenantMapper tenantMapper = new TenantMapperImpl();
        if (CollectionUtils.isNotEmpty(tenantApartment)){
            tenants = tenantApartment.stream()
                    .map(TenantApartmentDto::getTenant)
                    .toList()
                    .stream()
                    .map(tenantMapper::toResponse)
                    .toList();

        }
        ApartmentMapper mapper = new ApartmentMapperImpl();
        ApartmentResponse response = mapper.toResponse(apartment);
        response.setTenants(tenants);

        return (new PageResponse<ApartmentResponse>())
                .toBuilder()
                .content(Collections.singletonList(response))
                .build();

    }
    public void create(List<ApartmentResource> resources, String buildingId) {
        BuildingDto building = this.buildingPersistence.findBuildingById(buildingId);

        BuildingUtils.validateBuildingOwner(building);
        BuildingUtils.validateNumberOfUnit(building);
        ApartmentUtils.validateDuplicateApartmentNumber(resources);
        ApartmentUtils.validateListOfApartment(building, resources);

        ApartmentHandler handler = new ApartmentHandler();

        List<ApartmentDto> dtos = resources.stream()
                .map(resource -> handler.apartmentHandle(resource, empty()))
                .toList();
        this.persistence.save(dtos, building);
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
