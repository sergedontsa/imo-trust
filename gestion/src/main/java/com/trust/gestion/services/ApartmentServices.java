package com.trust.gestion.services;

import com.trust.gestion.exception.NoSuchElementFoundException;
import com.trust.gestion.domain.ApartmentDto;
import com.trust.gestion.entities.ApartmentEntity;
import com.trust.gestion.entities.BuildingEntity;
import com.trust.gestion.handlers.ApartmentHandler;
import com.trust.gestion.mappers.ApartmentMapper;
import com.trust.gestion.services.mappers.ApartmentMapperImpl;
import com.trust.gestion.pages.PageResponse;
import com.trust.gestion.persistence.ApartmentPersistence;
import com.trust.gestion.repositories.ApartmentRepository;
import com.trust.gestion.repositories.BuildingRepository;
import com.trust.gestion.resources.ApartmentResource;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.Optional.empty;

@Service
@RequiredArgsConstructor
@Transactional
public class ApartmentServices  {
    private final ApartmentRepository repository;
    private final ApartmentPersistence persistence;
    private final BuildingRepository buildingRepository;

    public PageResponse<ApartmentDto> getAll(Integer page, Integer size){
        ApartmentMapper mapper = new ApartmentMapperImpl();
        Page<ApartmentEntity> entities = this.repository.findAll(PageRequest.of(page, size));
        return (new PageResponse<ApartmentDto>()).toBuilder()
                .content(entities.getContent().stream().map(mapper::toDto).toList())
                .totalPages(entities.getTotalPages())
                .totalElements(entities.getTotalElements())
                .size(entities.getSize())
                .number(entities.getNumber())
                .build();
    }

    public PageResponse<ApartmentDto> getById(String id) {
        ApartmentMapper mapper = new ApartmentMapperImpl();
        return (new PageResponse<ApartmentDto>())
                .toBuilder()
                .content(List.of(mapper.toDto(this.findById(id))))
                .build();

    }
    public void create(ApartmentResource resource) {
        BuildingEntity buildingEntity = this.findBuildingById(resource.getBuildingId());

        this.validateApartmentNumber(buildingEntity.getApartments(), resource.getApartmentNumber());
        this.validateNumberOfUnit(buildingEntity);
        this.validateDateBuildingOwner(buildingEntity);

        ApartmentEntity entity = (new ApartmentHandler()).apartmentHandle(resource, empty());
        entity.setBuilding(buildingEntity);
        this.persistence.save(entity);

    }

    private void validateDateBuildingOwner(BuildingEntity entity) {

    }

    private BuildingEntity findBuildingById(String id) {
        return this.buildingRepository.findById(id).orElseThrow(() -> new NoSuchElementFoundException("No such element found"));
    }
    private void validateNumberOfUnit(BuildingEntity building) {
        if (building.getNumberOfUnits() == building.getApartments().size()) {
            throw new RuntimeException("Building is full");
        }
    }
    private void validateApartmentNumber(List<ApartmentEntity> apartments, String apartmentNumber){
        apartments.forEach(apartment -> {
            if (apartment.getApartmentNumber().equals(apartmentNumber)) {
                throw new RuntimeException("Apartment number already exist");
            }
        });
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
