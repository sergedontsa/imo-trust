package com.trust.gestion.persistence;

import com.trust.gestion.domain.ApartmentDto;
import com.trust.gestion.domain.BuildingDto;
import com.trust.gestion.entities.ApartmentEntity;
import com.trust.gestion.entities.BuildingEntity;
import com.trust.gestion.enums.ActionTitle;
import com.trust.gestion.mappers.ApartmentMapper;
import com.trust.gestion.mappers.ApartmentMapperImpl;
import com.trust.gestion.mappers.BuildingMapper;
import com.trust.gestion.mappers.BuildingMapperImpl;
import com.trust.gestion.repositories.ApartmentRepository;
import com.trust.gestion.repositories.BuildingRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class ApartmentPersistence {
    private final ApartmentRepository repository;
    private final ActionPersistence actionPersistence;
    private final BuildingRepository buildingRepository;

    public void save(List<ApartmentDto> dtos, BuildingDto building) {
        ApartmentMapper mapper = new ApartmentMapperImpl();
        BuildingMapper buildingMapper = new BuildingMapperImpl();
        List<ApartmentEntity> entities = dtos.stream()
                .map(mapper::toEntity)
                .toList();
        entities.forEach(entity -> entity.setBuilding(buildingMapper.toEntity(building)));
        this.repository.saveAll(entities);
        this.actionPersistence.createAction(ActionTitle.APARTMENT_CREATE);
    }
    public void save(ApartmentDto dto){
        ApartmentMapper mapper = new ApartmentMapperImpl();
        ApartmentEntity entity = mapper.toEntity(dto);
        BuildingEntity building = this.buildingRepository.findByApartmentsId(entity.getId());
        entity.setBuilding(building);
        this.repository.save(entity);
        this.actionPersistence.createAction(ActionTitle.APARTMENT_CREATE);
    }

    public ApartmentDto findApartment(String apartmentId) {
        ApartmentMapper mapper = new ApartmentMapperImpl();
        return this.repository.findById(apartmentId)
                .map(mapper::toDto)
                .orElseThrow();
    }
}
