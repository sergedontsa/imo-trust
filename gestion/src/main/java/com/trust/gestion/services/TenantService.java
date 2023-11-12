/*
 * Copyright (c) 2023.  - Serge G. Dontsa
 */

package com.trust.gestion.services;

import com.trust.gestion.domain.ApartmentDto;
import com.trust.gestion.domain.TenantApartmentDto;
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
import com.trust.gestion.persistence.ApartmentPersistence;
import com.trust.gestion.persistence.TelephonePersistence;
import com.trust.gestion.persistence.TenantApartmentPersistence;
import com.trust.gestion.persistence.TenantPersistence;
import com.trust.gestion.repositories.ApartmentRepository;
import com.trust.gestion.repositories.TenantApartmentRepository;
import com.trust.gestion.repositories.TenantBillPaidRepository;
import com.trust.gestion.repositories.TenantBillingRepository;
import com.trust.gestion.repositories.TenantRepository;
import com.trust.gestion.resources.BillPayResource;
import com.trust.gestion.resources.TelephoneResource;
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
        List<TenantDto> dtos = this.getEntities(resources);
        apartmentDto.setStatus(Status.RESERVED);
        apartmentDto.setOccupant(apartmentDto.getOccupant() + dtos.size());
        List<TenantApartmentDto> tenantApartmentEntities = this.tenantApartmentPersistence.getTenantApartment(dtos, apartmentDto);

        this.apartmentPersistence.save(apartmentDto);
        dtos.forEach(this.persistence::create);
        tenantApartmentPersistence.saveAll(tenantApartmentEntities);

    }
    public void update(TenantResource resource, String id) {
    }
    public void addTenantToApartment(List<TenantResource> resources, String apartmentId) {
        ApartmentDto apartment = this.apartmentPersistence.findApartment(apartmentId);
        List<TenantDto> dtos = this.getEntities(resources);
        List<TenantApartmentDto> tenantApartmentEntities = this.tenantApartmentPersistence.getTenantApartment(dtos, apartment);
        apartment.setOccupant(apartment.getOccupant() + dtos.size());
        apartment.setStatus(Status.RESERVED);
        this.apartmentPersistence.save(apartment);
        dtos.forEach(this.persistence::create);
        tenantApartmentPersistence.saveAll(tenantApartmentEntities);
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
        //TODO add validation to check if the amount paid is equal to the rent amount
        //TODO add validation to check if the amount paid is equal to the billing amount
        //TODO add validation to check if the billing is not paid
        //TODO refactor this method
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
    private List<TenantDto> getEntities(List<TenantResource> resources){
        return resources.stream()
                .map(resource -> (new TenantHandler()).tenantHandler(resource, empty()))
                .toList();
    }
    private TenantEntity findById(String id) {
        return this.repository.findById(id).orElseThrow(()->
                new NoSuchElementFoundException("Tenant with id " + id + " not found"));
    }



    public void addTelephone(List<TelephoneResource> resources, String tenantId) {
        this.persistence.findById(tenantId);
        TenantHandler handler = new TenantHandler();
        resources.forEach(resource -> resource.setEntityId(tenantId));
        this.telephonePersistence.saveAll(resources.stream()
                .map(resource -> handler.telephoneHandler(resource, empty()))
                .toList());
    }
}
