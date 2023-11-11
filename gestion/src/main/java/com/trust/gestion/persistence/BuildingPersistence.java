package com.trust.gestion.persistence;

import com.trust.gestion.entities.BuildingEntity;
import com.trust.gestion.entities.OwnerEntity;
import com.trust.gestion.entities.PersonEntity;
import com.trust.gestion.enums.ActionTitle;
import com.trust.gestion.exception.NoSuchElementFoundException;
import com.trust.gestion.mappers.ApartmentMapperImpl;
import com.trust.gestion.mappers.BuildingMapper;
import com.trust.gestion.mappers.BuildingMapperImpl;
import com.trust.gestion.mappers.OwnerMapper;
import com.trust.gestion.mappers.OwnerMapperImpl;
import com.trust.gestion.repositories.BuildingRepository;
import com.trust.gestion.resources.reponse.ApartmentResponse;
import com.trust.gestion.resources.reponse.BuildingResponse;
import com.trust.gestion.resources.reponse.OwnerResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
    public void save(BuildingEntity entity) {
           this.savedInBd(entity);
    }
    private void savedInBd(BuildingEntity entity){
        this.repository.save(entity);
        actionPersistence.createAction(ActionTitle.BUILDING_CREATE);
    }
    public BuildingResponse getOne(String buildingId){
        BuildingMapper mapper = new BuildingMapperImpl();
        BuildingEntity entity = this.findById(buildingId);
        List<OwnerResponse> owners = this.getBuildingOwner(entity);
        return mapper.toResponse(entity)
                .toBuilder()
                .owners(owners)
                .apartments(this.getApartmentResponse(entity))
                .build();

    }
    private BuildingEntity findById(String buildingId){
     return this.repository.findById(buildingId)
                .orElseThrow(() -> new NoSuchElementFoundException("Building not found"));
    }
    public List<OwnerResponse> getBuildingOwner(BuildingEntity entity) {
        OwnerMapper ownerMapper = new OwnerMapperImpl();
        List<OwnerEntity> owners = this.buildingOwnerPersistence.findByBuilding(entity);

        return owners.stream()
                .collect(Collectors.toMap(ownerEntity -> ownerEntity,
                        ownerEntity -> this.getPersonEntity(ownerEntity.getId())))
                .entrySet()
                .stream()
                .map(e -> ownerMapper.toResponse(e.getKey(), e.getValue()))
                .map(e -> e.toBuilder()
                        .address(this.addressPersistence.findByEntity(e.getId()))
                        .build())
                .toList();
    }

    public List<ApartmentResponse> getApartmentResponse(BuildingEntity entity) {
        return entity.getApartments()
                .stream()
                .map(apartment -> (new ApartmentMapperImpl()).toApartmentResponse(apartment))
                .toList();
    }
    private PersonEntity getPersonEntity(String id) {
        return this.personPersistence.findById(id);
    }


}
