/*
 * Copyright (c) 2023.  - Serge G. Dontsa
 */

package com.trust.gestion.services;

import com.trust.gestion.domain.ApartmentDto;
import com.trust.gestion.domain.IdentificationDto;
import com.trust.gestion.domain.TelephoneDto;
import com.trust.gestion.domain.TenantApartmentDto;
import com.trust.gestion.domain.TenantDto;
import com.trust.gestion.enums.Status;
import com.trust.gestion.exception.NoSuchElementFoundException;
import com.trust.gestion.exception.TrustImoException;
import com.trust.gestion.handlers.TenantHandler;
import com.trust.gestion.mappers.TenantMapper;
import com.trust.gestion.mappers.TenantMapperImpl;
import com.trust.gestion.pages.PageResponse;
import com.trust.gestion.persistence.ApartmentPersistence;
import com.trust.gestion.persistence.TelephonePersistence;
import com.trust.gestion.persistence.TenantApartmentPersistence;
import com.trust.gestion.persistence.TenantPersistence;
import com.trust.gestion.resources.BillPayResource;
import com.trust.gestion.resources.IdentificationResource;
import com.trust.gestion.resources.TelephoneResource;
import com.trust.gestion.resources.TenantResource;
import com.trust.gestion.resources.reponse.TenantResponse;
import com.trust.gestion.utilities.ApartmentUtils;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

import static java.util.Optional.empty;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class TenantService {
    private final TenantPersistence persistence;
    private final TelephonePersistence telephonePersistence;
    private final ApartmentPersistence apartmentPersistence;
    private final TenantApartmentPersistence tenantApartmentPersistence;


    public void create(List<TenantResource> resources, String apartmentId) throws TrustImoException {
        if (CollectionUtils.isEmpty(resources)) {
            log.error("Tenant resources cannot be empty");
            throw new NoSuchElementFoundException("Tenant resources cannot be empty");
        }
        ApartmentDto apartmentDto = this.apartmentPersistence.findApartment(apartmentId);
        ApartmentUtils.validateApartmentStatusOnCreateTenant(apartmentDto);
        TenantHandler handler = new TenantHandler();
        List<TenantDto> dtos = resources.stream()
                .map(resource -> handler.tenantHandler(resource, empty()))
                .toList();
        apartmentDto.setStatus(Status.RESERVED);
        apartmentDto.setOccupant(apartmentDto.getOccupant() + dtos.size());
        List<TenantApartmentDto> tenantApartmentEntities = this.tenantApartmentPersistence.buildTenantApartment(dtos, apartmentDto);

        this.apartmentPersistence.save(apartmentDto);
        dtos.forEach(this.persistence::create);
        tenantApartmentPersistence.saveAll(tenantApartmentEntities);

    }
    public void update(TenantResource resource, String id) {
        //will work on this later
    }
    public void addTenantToApartment(List<TenantResource> resources, String apartmentId) {
        ApartmentDto apartment = this.apartmentPersistence.findApartment(apartmentId);
        TenantHandler handler = new TenantHandler();
        List<TenantDto> dtos = resources.stream()
                .map(resource -> handler.tenantHandler(resource, empty()))
                .toList();
        List<TenantApartmentDto> tenantApartmentEntities = this.tenantApartmentPersistence.buildTenantApartment(dtos, apartment);
        apartment.setOccupant(apartment.getOccupant() + dtos.size());
        apartment.setStatus(Status.RESERVED);
        this.apartmentPersistence.save(apartment);
        dtos.forEach(this.persistence::create);
        tenantApartmentPersistence.saveAll(tenantApartmentEntities);
    }


    public PageResponse<TenantResponse> getById(String id) {
        TenantDto dto = persistence.findById(id);
        TenantMapper mapper = new TenantMapperImpl();
        return (new PageResponse<TenantResponse>()).toBuilder()
                 .content(Collections.singletonList(mapper.toResponse(dto)))
                 .build();
    }

    public PageResponse<TenantResponse> getAll(Integer page, Integer size) {
        TenantMapper mapper = new TenantMapperImpl();

        return (new PageResponse<TenantResponse>()).toBuilder()
                .content(this.persistence.getAll(PageRequest.of(page, size))
                        .stream()
                        .map(mapper::toResponse)
                        .toList())
                .totalPages(0)
                .totalElements(0)
                .size(0)
                .number(0)
                .build();
    }

    public void payBill(BillPayResource resource) {
        //wil work on this later

    }

    public void addTelephone(List<TelephoneResource> resources, String tenantId) {
        TenantDto dto = this.persistence.findById(tenantId);
        TenantHandler handler = new TenantHandler();
        resources.forEach(resource -> resource.setEntityId(dto.getId()));
        List<TelephoneDto> dtos = resources.stream()
                .map(resource -> handler.telephoneHandler(resource, empty()))
                .toList();
        this.persistence.addTelephone(dtos);

    }
    public void addIdentification(List<IdentificationResource> resources, String tenantId){
        TenantDto dto = this.persistence.findById(tenantId);
        TenantHandler handler = new TenantHandler();
        resources.forEach(resource -> resource.setEntityId(dto.getId()));
        List<IdentificationDto> dtos = resources.stream()
                .map(resource -> handler.identificationHandler(resource, empty()))
                .toList();
        this.persistence.addIdentification(dtos);
    }
}
