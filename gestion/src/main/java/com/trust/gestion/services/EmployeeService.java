package com.trust.gestion.services;

import com.trust.gestion.domain.AddressDto;
import com.trust.gestion.domain.EmployeeDto;
import com.trust.gestion.domain.IdentificationDto;
import com.trust.gestion.domain.PersonDto;
import com.trust.gestion.domain.TelephoneDto;
import com.trust.gestion.handlers.EmployeeHandler;
import com.trust.gestion.mappers.EmployeeMapper;
import com.trust.gestion.mappers.EmployeeMapperImpl;
import com.trust.gestion.pages.PageResponse;
import com.trust.gestion.persistence.EmployeePersistence;
import com.trust.gestion.persistence.PersonPersistence;
import com.trust.gestion.resources.AddressResource;
import com.trust.gestion.resources.EmployeeResource;
import com.trust.gestion.resources.IdentificationResource;
import com.trust.gestion.resources.TelephoneResource;
import com.trust.gestion.resources.reponse.EmployeeResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

import static java.util.Optional.empty;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmployeeService {
    private final EmployeePersistence persistence;
    private final PersonPersistence personPersistence;
    public PageResponse<EmployeeResponse> getById(String employeeId) {
        PageResponse<EmployeeResponse> pageResponse = new PageResponse<>();
        EmployeeDto dto = this.persistence.getOne(employeeId);
        return pageResponse.toBuilder()
                .content(List.of((new EmployeeMapperImpl()).toResponse(dto)))
                .build();

    }

    public PageResponse<EmployeeResponse> getAll(Integer page, Integer size) {
        EmployeeMapper mapper = new EmployeeMapperImpl();
        PageResponse<EmployeeDto> response = this.persistence.getAll(page, size);

        List<EmployeeResponse> content = response.getContent().stream()
                .map(mapper::toResponse)
                .toList();

        return (new PageResponse<EmployeeResponse>())
                .toBuilder()
                .content(content)
                .totalPages(response.getTotalPages())
                .totalElements(response.getTotalElements())
                .size(response.getSize())
                .number(response.getNumber())
                .build();
    }

    public void create(EmployeeResource resource) {
        EmployeeHandler handler = new EmployeeHandler();
        EmployeeDto dto = handler.employeeHandler(resource, empty());
        PersonDto personDto = this.extractPersonFromResource(resource, dto.getEmployeeId());
        this.persistence.create(dto);
        this.personPersistence.create(personDto);
    }
    private PersonDto extractPersonFromResource(EmployeeResource resource, String employeeId){
        return PersonDto.builder()
                .id(employeeId)
                .firstName(resource.getFirstName())
                .middleName(resource.getMiddleName())
                .lastName(resource.getLastName())
                .gender(resource.getGender())
                .registrationDate(Instant.now())
                .lastUpdated(Instant.now())
                .build();

    }

    public void createAddress(String employeeId, List<AddressResource> resources) {
        EmployeeHandler handler = new EmployeeHandler();
        List<AddressDto> dtos = resources.stream()
                .map(resource -> handler.handleAddress(resource, employeeId, empty()))
                .toList();
        this.persistence.createAddress(dtos);
    }

    public void addTelephone(List<TelephoneResource> resources, String employeeId) {
        EmployeeHandler handler = new EmployeeHandler();
        List<TelephoneDto> dtos = resources.stream()
                .map(resource -> handler.handleTelephone(resource, employeeId, empty()))
                .toList();
        this.persistence.createTelephone(dtos);
    }

    public void addIdentification(List<IdentificationResource> resources, String employeeId) {
        EmployeeHandler handler = new EmployeeHandler();
        List<IdentificationDto> dtos = resources.stream()
                .map(resource -> handler.handleIdentification(resource, employeeId, empty()))
                .toList();
        this.persistence.createIdentification(dtos);
    }
}
