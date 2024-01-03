package com.trust.gestion.handlers;

import com.trust.gestion.domain.AddressDto;
import com.trust.gestion.domain.EmployeeDto;
import com.trust.gestion.domain.IdentificationDto;
import com.trust.gestion.domain.TelephoneDto;
import com.trust.gestion.entities.AddressEntity;
import com.trust.gestion.entities.EmployeeEntity;
import com.trust.gestion.entities.IdentificationEntity;
import com.trust.gestion.entities.TelephoneEntity;
import com.trust.gestion.resources.AddressResource;
import com.trust.gestion.resources.EmployeeResource;
import com.trust.gestion.resources.IdentificationResource;
import com.trust.gestion.resources.TelephoneResource;
import com.trust.gestion.utilities.Utilities;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Optional;

@Component
@RequiredArgsConstructor
@Slf4j
public class EmployeeHandler {
    public EmployeeDto employeeHandler(EmployeeResource resource, Optional<EmployeeEntity> optionalEntity){
        return optionalEntity.isPresent() ? updateEmployee(resource, optionalEntity.get()) : createEmployee();
    }

    public AddressDto handleAddress(AddressResource resource, String employeeId, Optional<AddressEntity> optionalAddress) {
        return optionalAddress.isPresent() ? updateAddress(resource, employeeId, optionalAddress.get()) : createAddress(resource, employeeId);
    }

    public TelephoneDto handleTelephone(TelephoneResource resource, String employeeId, Optional<TelephoneEntity> optionalTelephone) {
        return optionalTelephone.isPresent() ? updateTelephone(resource, employeeId, optionalTelephone.get()) : createTelephone(resource, employeeId);

    }
    public IdentificationDto handleIdentification(IdentificationResource resource, String employeeId, Optional<IdentificationEntity> optionalIdentification)  {
        return optionalIdentification.isPresent() ? updateIdentification(resource, employeeId, optionalIdentification.get()) : createIdentification(resource, employeeId);
    }
    private EmployeeDto createEmployee() {
        return EmployeeDto.builder()
                .employeeId(Utilities.getEmployeeId())
                .registrationDate(Instant.now())
                .lastUpdated(Instant.now())
                .build();

    }
    private EmployeeDto updateEmployee(EmployeeResource resource, EmployeeEntity employeeEntity) {
        return null;
    }
    private AddressDto createAddress(AddressResource resource, String employeeId) {
        return AddressDto.builder()
                .entityId(employeeId)
                .civicNumber(resource.getCivicNumber())
                .streetName(resource.getStreetName())
                .aptNumber(resource.getAptNumber())
                .city(resource.getCity())
                .province(resource.getProvince())
                .postalCode(resource.getPostalCode())
                .country(resource.getCountry())
                .type(resource.getType())
                .registrationDate(Instant.now())
                .lastUpdated(Instant.now())
                .build();
    }
    private AddressDto updateAddress(AddressResource resource, String employeeId, AddressEntity addressEntity) {
        return null;
    }

    private TelephoneDto createTelephone(TelephoneResource resource, String employeeId) {
        return TelephoneDto.builder()
                .entityId(employeeId)
                .number(resource.getNumber())
                .areaCode(resource.getAreaCode())
                .carrier(resource.getCarrier())
                .country(resource.getCountry())
                .registrationDate(Instant.now())
                .lastUpdated(Instant.now())
                .build();
    }

    private TelephoneDto updateTelephone(TelephoneResource resource, String employeeId, TelephoneEntity telephoneEntity) {
        return null;
    }

    private IdentificationDto createIdentification(IdentificationResource resource, String employeeId) {
        return IdentificationDto.builder()
                .entityId(employeeId)
                .typeId("xxxx")
                .issueCountry(resource.getIssueCountry())
                .validFrom(resource.getValidFrom())
                .validTo(resource.getValidTo())
                .description(resource.getDescription())
                .registrationDate(Instant.now())
                .lastUpdated(Instant.now())
                .build();

    }

    private IdentificationDto updateIdentification(IdentificationResource resource, String employeeId, IdentificationEntity identificationEntity) {
        return null;
    }
}
