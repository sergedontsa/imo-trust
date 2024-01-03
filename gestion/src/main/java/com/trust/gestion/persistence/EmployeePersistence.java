package com.trust.gestion.persistence;

import com.trust.gestion.domain.AddressDto;
import com.trust.gestion.domain.EmployeeDto;
import com.trust.gestion.domain.IdentificationDto;
import com.trust.gestion.domain.PersonDto;
import com.trust.gestion.domain.TelephoneDto;
import com.trust.gestion.entities.EmployeeEntity;
import com.trust.gestion.mappers.EmployeeMapper;
import com.trust.gestion.mappers.EmployeeMapperImpl;
import com.trust.gestion.pages.PageResponse;
import com.trust.gestion.repositories.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
@RequiredArgsConstructor
public class EmployeePersistence {
    private final EmployeeRepository repository;
    private final PersonPersistence personPersistence;
    private final AddressPersistence addressPersistence;
    private final TelephonePersistence telephonePersistence;
    private final IdentificationPersistence identificationPersistence;


    public void create(EmployeeDto dto){
        EmployeeMapper mapper = new EmployeeMapperImpl();
        this.repository.save(mapper.toEntity(dto));
    }
    public EmployeeDto getOne(String employeeId){
        EmployeeMapper mapper = new EmployeeMapperImpl();
        EmployeeEntity entity = this.repository.findByEmployeeId(employeeId);
        PersonDto person = this.personPersistence.getById(employeeId);
        return mapper.toDto(entity, person)
                .toBuilder()
                .telephones(this.telephonePersistence.getAllByEntityId(employeeId))
                .address(this.addressPersistence.findByEntityId(employeeId))
                .identifications(this.identificationPersistence.getByEntityId(employeeId))
                .build();
    }
    public PageResponse<EmployeeDto> getAll(Integer page, Integer size){
        EmployeeMapper mapper = new EmployeeMapperImpl();
        Page<EmployeeEntity> pages = this.repository.findAll(PageRequest.of(page, size));
        List<EmployeeDto> content = pages.getContent()
                .stream()
                .map(entity -> mapper.toDto(entity, this.personPersistence.getById(entity.getEmployeeId()))
                        .toBuilder()
                        .address(this.addressPersistence.findByEntityId(entity.getEmployeeId()))
                        .telephones(this.telephonePersistence.getAllByEntityId(entity.getEmployeeId()))
                        .identifications(this.identificationPersistence.getByEntityId(entity.getEmployeeId()))
                        .build()).toList();

        return (new PageResponse<EmployeeDto>())
                .toBuilder()
                .content(content)
                .totalPages(pages.getTotalPages())
                .totalElements(pages.getTotalElements())
                .size(pages.getSize())
                .number(pages.getNumber())
                .build();

    }

    public void createAddress(List<AddressDto> dtos) {
        this.addressPersistence.saveAll(dtos);
    }
    public void createTelephone(List<TelephoneDto> dtos) {
        this.telephonePersistence.saveAll(dtos);
    }
    public void createIdentification(List<IdentificationDto> dtos) {
        this.identificationPersistence.saveAll(dtos);
    }
}
