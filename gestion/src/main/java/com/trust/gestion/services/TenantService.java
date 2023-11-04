/*
 * Copyright (c) 2023.  - Serge G. Dontsa
 */

package com.trust.gestion.services;

import com.trust.gestion.domain.ApartmentDto;
import com.trust.gestion.domain.TenantDto;
import com.trust.gestion.entities.ApartmentEntity;
import com.trust.gestion.entities.TenantApartmentEntity;
import com.trust.gestion.entities.TenantBillPaidEntity;
import com.trust.gestion.entities.TenantBillingEntity;
import com.trust.gestion.entities.TenantEntity;
import com.trust.gestion.enums.Status;
import com.trust.gestion.exception.NoSuchElementFoundException;
import com.trust.gestion.exception.TrustImoException;
import com.trust.gestion.handlers.TenantHandler;
import com.trust.gestion.mappers.ApartmentMapperImpl;
import com.trust.gestion.mappers.TenantMapperImpl;
import com.trust.gestion.pages.PageResponse;
import com.trust.gestion.persistence.TenantPersistence;
import com.trust.gestion.repositories.ApartmentRepository;
import com.trust.gestion.repositories.TenantApartmentRepository;
import com.trust.gestion.repositories.TenantBillPaidRepository;
import com.trust.gestion.repositories.TenantBillingRepository;
import com.trust.gestion.repositories.TenantRepository;
import com.trust.gestion.resources.BillPayResource;
import com.trust.gestion.resources.TenantResource;
import com.trust.gestion.utilities.ApartmentUtils;
import com.trust.gestion.utilities.Utilities;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import static java.util.Optional.empty;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class TenantService {
    private final TenantRepository repository;
    private final ApartmentRepository apartmentRepository;
    private final TenantPersistence persistence;
    private final TenantApartmentRepository tenantApartmentRepository;
    private final TenantBillingRepository billingRepository;
    private final TenantBillPaidRepository billPaidRepository;


    public void create(List<TenantResource> resources, String apartmentId) throws TrustImoException {
        if (CollectionUtils.isEmpty(resources)) {
            log.error("Tenant resources cannot be empty");
            throw new NoSuchElementFoundException("Tenant resources cannot be empty");
        }
        ApartmentEntity apartmentEntity = this.findApartment(apartmentId);
        ApartmentUtils.validateApartmentStatusOnCreateTenant(apartmentEntity);
        List<TenantEntity> entities = this.getEntities(resources);
        apartmentEntity.setStatus(Status.RESERVED);
        apartmentEntity.setOccupant(apartmentEntity.getOccupant() + entities.size());
        List<TenantApartmentEntity> tenantApartmentEntities = this.getTenantApartment(entities, apartmentEntity);

        this.apartmentRepository.save(apartmentEntity);
        entities.forEach(this.persistence::create);
        tenantApartmentRepository.saveAll(tenantApartmentEntities);

    }
    public void update(TenantResource resource, String id) {
    }
    public void addTenantToApartment(List<TenantResource> resources, String apartmentId) {
        ApartmentEntity apartmentEntity = this.findApartment(apartmentId);
        List<TenantEntity> entities = this.getEntities(resources);
        List<TenantApartmentEntity> tenantApartmentEntities = this.getTenantApartment(entities, apartmentEntity);
        apartmentEntity.setOccupant(apartmentEntity.getOccupant() + entities.size());
        apartmentEntity.setStatus(Status.RESERVED);
        this.apartmentRepository.save(apartmentEntity);
        entities.forEach(this.persistence::create);
        tenantApartmentRepository.saveAll(tenantApartmentEntities);
    }


    public PageResponse<TenantDto> getById(String id) {
        TenantEntity entity = this.findById(id);
        List<TenantApartmentEntity> tenantApartmentEntity = this.tenantApartmentRepository.findByTenant(entity);

        TenantDto tenantDto = this.mapEntityToDto(entity);
        tenantDto.setApartments(this.getApartmentDtoFromTenantApartmentEntity(tenantApartmentEntity, id));
        return (new PageResponse<TenantDto>()).toBuilder()
                 .content(Collections.singletonList(tenantDto))
                 .build();
    }

    public PageResponse<TenantDto> getAll(Integer page, Integer size) {
        Page<TenantEntity> entities = this.repository.findAll(PageRequest.of(page, size));
        return (new PageResponse<TenantDto>()).toBuilder()
                .content(entities.getContent().stream().map(this::mapEntityToDto).toList())
                .totalPages(entities.getTotalPages())
                .totalElements(entities.getTotalElements())
                .size(entities.getSize())
                .number(entities.getNumber())
                .build();
    }

    public void payBill(BillPayResource resource) {
        TenantEntity tenantEntity = this.repository.findById(resource.getTenantId())
                .orElseThrow(() -> new NoSuchElementFoundException("Tenant with id " + resource.getTenantId() + " not found"));
        ApartmentEntity apartmentEntity = this.apartmentRepository.findById(resource.getApartmentId())
                .orElseThrow(() -> new NoSuchElementFoundException("Apartment with id " + resource.getApartmentId() + " not found"));
        TenantBillingEntity billingEntity = this.billingRepository.findByApartmentAndTenantAndId(apartmentEntity, tenantEntity, resource.getBilId());


        if (apartmentEntity.getRentAmount().equals(resource.getAmountPaid()) && billingEntity.getBillingAmount().equals(resource.getAmountPaid())){

            TenantBillPaidEntity billPaidEntity = (new TenantBillPaidEntity()).toBuilder()
                    .id(Utilities.getRecordId())
                    .tenant(tenantEntity)
                    .bill(billingEntity)
                    .amountPaid(resource.getAmountPaid())
                    .paymentDate(LocalDate.now())
                    .paymentMethod(resource.getPaymentMethod())
                    .description(resource.getDescription())
                    .registrationDate(Instant.now())
                    .lastUpdated(Instant.now())
                    .build();

            this.billPaidRepository.save(billPaidEntity);
            this.billingRepository.save(billingEntity.toBuilder()
                    .status(Status.PAID)
                    .build());
        }else {
            log.error("Amount paid is not equal to the rent amount");
        }


    }
    private List<ApartmentDto> getApartmentDtoFromTenantApartmentEntity(List<TenantApartmentEntity> tenantApartmentEntities, String tenantId){
        return tenantApartmentEntities.stream()
                .filter(tenantApartmentEntity -> tenantApartmentEntity.getTenant().getId().equals(tenantId))
                .map(TenantApartmentEntity::getApartment)
                .toList()
                .stream()
                .map(entity -> (new ApartmentMapperImpl()).toDto(entity))
                .toList();
    }
    private TenantDto mapEntityToDto(TenantEntity entity) {
        return (new TenantMapperImpl()).toDto(entity);
    }
    private ApartmentEntity findApartment(String apartmentId){
        return this.apartmentRepository.findById(apartmentId)
                .orElseThrow(() -> new NoSuchElementFoundException("Apartment with id " + apartmentId + " not found"));
    }
    private List<TenantEntity> getEntities(List<TenantResource> resources){
        return resources.stream()
                .map(resource -> (new TenantHandler()).tenantHandler(resource, empty()))
                .toList();
    }
    private TenantEntity findById(String id) {
        return this.repository.findById(id).orElseThrow(()->
                new NoSuchElementFoundException("Tenant with id " + id + " not found"));
    }
    private List<TenantApartmentEntity> getTenantApartment(List<TenantEntity> entities, ApartmentEntity apartmentEntity){
        return entities.stream()
                .map(tenant -> TenantApartmentEntity.builder()
                        .apartment(apartmentEntity)
                        .tenant(tenant)
                        .registrationDate(Instant.now())
                        .lastUpdated(Instant.now())
                        .build())
                .toList();
    }


}
