package com.trust.gestion.persistence;

import com.trust.gestion.domain.BuildingDto;
import com.trust.gestion.domain.OwnerDto;
import com.trust.gestion.domain.PersonDto;
import com.trust.gestion.entities.BuildingEntity;
import com.trust.gestion.enums.ActionTitle;
import com.trust.gestion.exception.NoSuchElementFoundException;
import com.trust.gestion.mappers.ApartmentMapper;
import com.trust.gestion.mappers.ApartmentMapperImpl;
import com.trust.gestion.mappers.BuildingMapper;
import com.trust.gestion.mappers.BuildingMapperImpl;
import com.trust.gestion.repositories.BuildingRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@Slf4j
@RequiredArgsConstructor
public class BuildingPersistence {
    private final BuildingRepository repository;
    private final ActionPersistence actionPersistence;
    private final BuildingOwnerPersistence buildingOwnerPersistence;
    private final PersonPersistence personPersistence;
    private final AddressPersistence addressPersistence;

    public void save(BuildingDto dto) {
        BuildingMapper mapper = new BuildingMapperImpl();
        this.repository.save(mapper.toEntity(dto));
        actionPersistence.createAction(ActionTitle.BUILDING_CREATE);
    }

    public BuildingDto getOne(String buildingId) {
        BuildingMapper mapper = new BuildingMapperImpl();
        ApartmentMapper apartmentMapper = new ApartmentMapperImpl();
        BuildingEntity entity = this.findById(buildingId);
        List<OwnerDto> owners = this.getBuildingOwner(entity);
        return mapper.toDto(entity).toBuilder().owners(owners).apartments(entity.getApartments().stream().map(apartmentMapper::toDto).toList()).build();

    }

    public Page<BuildingDto> getAll(PageRequest pageRequest) {
        BuildingMapper mapper = new BuildingMapperImpl();
        return this.repository.findAll(pageRequest).map(mapper::toDto);
    }

    private BuildingEntity findById(String buildingId) {
        return this.repository.findById(buildingId).orElseThrow(() -> new NoSuchElementFoundException("Building not found"));
    }

    public List<OwnerDto> getBuildingOwner(BuildingEntity entity) {
        //todo refactor this
        List<OwnerDto> owners = this.buildingOwnerPersistence.findByBuilding(entity);

        return owners.stream()
                .collect(Collectors.toMap(ownerEntity -> ownerEntity, ownerEntity -> this.getPersonEntity(ownerEntity.getId())))
                .keySet()
                .stream()
                .map(o -> o.toBuilder()
                        .address(this.addressPersistence.findByEntityId(o.getId()))
                        .build())
                .toList();
    }

    private PersonDto getPersonEntity(String id) {
        return this.personPersistence.findById(id);
    }


    public BuildingDto findBuildingById(String buildingId) {
        BuildingMapper mapper = new BuildingMapperImpl();
        return this.repository.findById(buildingId)
                .map(mapper::toDto)
                .orElseThrow(() -> new NoSuchElementFoundException("Building with id " + buildingId + " not found"));
    }
}
