package com.trust.gestion.services.persistence;

import com.trust.gestion.enums.ActionTitle;
import com.trust.gestion.services.domain.ApartmentDto;
import com.trust.gestion.services.domain.BuildingDto;
import com.trust.gestion.services.entities.ApartmentEntity;
import com.trust.gestion.services.entities.BuildingEntity;
import com.trust.gestion.services.mappers.ApartmentMapper;
import com.trust.gestion.services.mappers.ApartmentMapperImpl;
import com.trust.gestion.services.mappers.BuildingMapper;
import com.trust.gestion.services.mappers.BuildingMapperImpl;
import com.trust.gestion.services.repositories.BuildingRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@Slf4j
@RequiredArgsConstructor
public class BuildingPersistence {
    private final BuildingRepository repository;
    private final ActionPersistence actionPersistence;
    private final ApartmentPersistence apartmentPersistence;
    public BuildingDto save(BuildingDto dto) {
           return this.findById(dto.getId()).isPresent() ? this.savedInBd(ActionTitle.BUILDING_UPDATE, dto)
                : this.savedInBd(ActionTitle.BUILDING_CREATE, dto);
    }
    private BuildingDto savedInBd(ActionTitle actionTitle, BuildingDto dto){
        BuildingMapper mapper = new BuildingMapperImpl();
        List<ApartmentDto> apartments = CollectionUtils.emptyIfNull(dto.getApartments()).stream().toList();

        BuildingEntity toSave = mapper.toEntity(dto);
        BuildingEntity saved = this.repository.save(toSave);
        this.saveApartment(apartments, saved);
        actionPersistence.createAction(actionTitle);
        return mapper.toDto(saved);
    }
    private void saveApartment(List<ApartmentDto> dtos, BuildingEntity entity){
        ApartmentMapper mapper = new ApartmentMapperImpl();
        if (CollectionUtils.isNotEmpty(dtos)){
            List<ApartmentEntity> apartmentEntities = dtos.stream()
                    .map(mapper::toEntity)
                    .toList();
            apartmentEntities.forEach(ap -> ap.setBuilding(entity));
            apartmentEntities.forEach(this.apartmentPersistence::save);
            this.actionPersistence.createAction(ActionTitle.APARTMENT_CREATE);
            this.actionPersistence.createAction(ActionTitle.BUILDING_UPDATE);
        }
    }
    private Optional<BuildingEntity> findById(String id) {
        return this.repository.findById(id);
    }

}
