/*
 * Copyright (c) 2023.  - Serge G. Dontsa
 */

package com.trust.gestion.services;

import com.trust.gestion.enums.Status;
import com.trust.gestion.exception.validators.TenantOnCreationValidation;
import com.trust.gestion.services.domain.TenantDto;
import com.trust.gestion.services.entities.ApartmentEntity;
import com.trust.gestion.services.entities.TenantApartmentEntity;
import com.trust.gestion.services.entities.TenantBillPaidEntity;
import com.trust.gestion.services.entities.TenantBillingEntity;
import com.trust.gestion.services.entities.TenantEntity;
import com.trust.gestion.services.handlers.Handler;
import com.trust.gestion.services.pages.PageResponse;
import com.trust.gestion.services.persistence.TenantPersistence;
import com.trust.gestion.services.repositories.ApartmentRepository;
import com.trust.gestion.services.repositories.TenantApartmentRepository;
import com.trust.gestion.services.repositories.TenantBillPaidRepository;
import com.trust.gestion.services.repositories.TenantBillingRepository;
import com.trust.gestion.services.repositories.TenantRepository;
import com.trust.gestion.services.resources.BillPayResource;
import com.trust.gestion.services.resources.TenantResource;
import com.trust.gestion.utilities.Utilities;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static java.util.Optional.empty;
import static java.util.Optional.of;

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
        TenantOnCreationValidation validation = new TenantOnCreationValidation();
        validation.validate(resource, empty());
        ApartmentEntity apartmentEntity = this.apartmentRepository.findById(resource.getApartmentId())
                .orElseThrow(() -> new RuntimeException("Apartment with id " + resource.getApartmentId() + " not found"));

        if (Status.apartmentNotAvailable().contains(apartmentEntity.getStatus())){
            throw new RuntimeException("Apartment with id " + resource.getApartmentId() + " is not available");
        } else if (apartmentEntity.getOccupant() > 2 || apartmentEntity.getOccupant().compareTo(2) == 0) {
            throw new RuntimeException("Apartment with id " + resource.getApartmentId() + " is full");
        } else {

            Handler handler = new Handler();
            TenantDto dto = handler.tenantHandler(resource, empty());
            TenantDto saved = this.persistence.create(dto);
            TenantEntity entityFromBd = this.repository.findById(saved.getId())
                    .orElseThrow(() -> new RuntimeException("Tenant with id " + saved.getId() + " not found"));

            ApartmentEntity updated = apartmentEntity.toBuilder()
                    .status(Status.RESERVED)
                    .occupant(apartmentEntity.getOccupant() + 1)
                    .build();
            this.apartmentRepository.save(updated);
            this.tenantApartmentRepository.save(TenantApartmentEntity.builder()
                    .tenant(entityFromBd)
                    .apartment(apartmentEntity)
                    .lastUpdated(Instant.now())
                    .registrationDate(Instant.now())
                    .build());
        }
    }
    public void update(TenantResource resource, String id) {
        Handler handler = new Handler();
        TenantEntity entity = this.findById(id).orElseThrow(()->
                new RuntimeException("Tenant with id " + id + " not found"));
        TenantDto dto = handler.tenantHandler(resource, of(entity));
        this.persistence.create(dto);
    }
    private Optional<TenantEntity> findById(String id) {
        return this.repository.findById(id);
    }

    public PageResponse<TenantDto> getById(String id) {
        return null;
    }

    public void payBill(BillPayResource resource) {
        TenantEntity tenantEntity = this.repository.findById(resource.getTenantId())
                .orElseThrow(() -> new RuntimeException("Tenant with id " + resource.getTenantId() + " not found"));
        ApartmentEntity apartmentEntity = this.apartmentRepository.findById(resource.getApartmentId())
                .orElseThrow(() -> new RuntimeException("Apartment with id " + resource.getApartmentId() + " not found"));
        TenantBillingEntity billingEntity = this.billingRepository.findByApartmentAndTenantAndId(apartmentEntity, tenantEntity, resource.getBilId());

        List<TenantApartmentEntity> tenantApartmentEntities = this.tenantApartmentRepository.findAllByTenantAndApartment(tenantEntity, apartmentEntity);

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
            throw new RuntimeException("Amount paid does not match bill amount");
        }


    }
}
