package com.trust.gestion.services.scheduler;

import com.trust.gestion.enums.Status;
import com.trust.gestion.services.entities.ApartmentEntity;
import com.trust.gestion.services.entities.TenantApartmentEntity;
import com.trust.gestion.services.entities.TenantBillingEntity;
import com.trust.gestion.services.entities.TenantEntity;
import com.trust.gestion.services.repositories.TenantApartmentRepository;
import com.trust.gestion.services.repositories.TenantBillingRepository;
import com.trust.gestion.services.repositories.TenantRepository;
import com.trust.gestion.utilities.Utilities;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.LocalDate;
import java.util.List;

@Component
@AllArgsConstructor
@Slf4j
public class ScheduledTask {

    private final TenantRepository repository;
    private final TenantBillingRepository billingRepository;
    private final TenantApartmentRepository tenantApartmentRepository;

    //@Scheduled(cron = "*/10 * * * * *")
    public void generateTenantBill(){
        List<TenantEntity> entities = this.repository.findAll()
                .stream()
                .filter(t -> t.getStatus() == Status.ACTIVE)
                .toList();
        List<TenantApartmentEntity> tenantApartmentEntities = this.tenantApartmentRepository.findAll();
        log.info("Generating bill for {} tenant(s)", entities.size());
        CollectionUtils.emptyIfNull(tenantApartmentEntities).forEach(tenantApartmentEntity -> {

            TenantEntity tenantEntity = tenantApartmentEntity.getTenant();
            ApartmentEntity apartmentEntity = tenantApartmentEntity.getApartment();
            log.info("Generating bill for tenant {}", tenantEntity.getFirstName());

            TenantBillingEntity billing = (new TenantBillingEntity())
                    .toBuilder()
                    .id(Utilities.getBillPaidConfirmationId())
                    .billTo(LocalDate.now())
                    .apartment(apartmentEntity)
                    .billingAmount(apartmentEntity.getRentAmount())
                    .tenant(tenantEntity)
                    .status(Status.OPEN)
                    .billingType("RENT")
                    .billFrom(LocalDate.now().minusMonths(1))
                    .billTo(LocalDate.now())
                    .billDueDate(LocalDate.now().plusDays(10))
                    .description("Rent for the month of " + LocalDate.now().getMonth())
                    .registrationDate(Instant.now())
                    .lastUpdated(Instant.now())
                    .build();

            this.billingRepository.save(billing);
            log.info("Bill generated for tenant {}", tenantEntity.getFirstName());
        });
        log.info("Bill generated for {} tenant(s)", entities.size());


    }
}
