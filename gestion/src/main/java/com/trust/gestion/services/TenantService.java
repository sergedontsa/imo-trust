/*
 * Copyright (c) 2023.  - Serge G. Dontsa
 */

package com.trust.gestion.services;

import com.trust.gestion.enums.Status;
import com.trust.gestion.domain.TenantDto;
import com.trust.gestion.entities.ApartmentEntity;
import com.trust.gestion.entities.TenantBillPaidEntity;
import com.trust.gestion.entities.TenantBillingEntity;
import com.trust.gestion.entities.TenantEntity;
import com.trust.gestion.services.mappers.TenantMapperImpl;
import com.trust.gestion.pages.PageResponse;
import com.trust.gestion.persistence.TenantPersistence;
import com.trust.gestion.repositories.ApartmentRepository;
import com.trust.gestion.repositories.TenantApartmentRepository;
import com.trust.gestion.repositories.TenantBillPaidRepository;
import com.trust.gestion.repositories.TenantBillingRepository;
import com.trust.gestion.repositories.TenantRepository;
import com.trust.gestion.resources.BillPayResource;
import com.trust.gestion.resources.TenantResource;
import com.trust.gestion.utilities.Utilities;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDate;
import java.util.List;

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

    public void create(TenantResource resource) {

    }
    public void update(TenantResource resource, String id) {
    }
    private TenantEntity findById(String id) {
        return this.repository.findById(id).orElseThrow(()->
                new RuntimeException("Tenant with id " + id + " not found"));
    }

    public PageResponse<TenantDto> getById(String id) {
        PageResponse<TenantDto> pageResponse = new PageResponse<>();
         return pageResponse.toBuilder()
                 .content(List.of(this.mapEntityToDto(this.findById(id))))
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
                .orElseThrow(() -> new RuntimeException("Tenant with id " + resource.getTenantId() + " not found"));
        ApartmentEntity apartmentEntity = this.apartmentRepository.findById(resource.getApartmentId())
                .orElseThrow(() -> new RuntimeException("Apartment with id " + resource.getApartmentId() + " not found"));
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

    private TenantDto mapEntityToDto(TenantEntity entity) {
        return (new TenantMapperImpl()).toDto(entity);
    }


}
