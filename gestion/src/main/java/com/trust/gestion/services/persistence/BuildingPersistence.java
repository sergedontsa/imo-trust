package com.trust.gestion.services.persistence;

import com.trust.gestion.enums.ActionTitle;
import com.trust.gestion.services.domain.BuildingDto;
import com.trust.gestion.services.entities.BuildingEntity;
import com.trust.gestion.services.mappers.BuildingMapper;
import com.trust.gestion.services.mappers.BuildingMapperImpl;
import com.trust.gestion.services.repositories.BuildingRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@Slf4j
@RequiredArgsConstructor
public class BuildingPersistence {
    private final BuildingRepository repository;
    private final ActionPersistence actionPersistence;
    public BuildingDto save(BuildingEntity entity) {
           return this.findById(entity.getId()).isPresent() ? this.savedInBd(ActionTitle.BUILDING_UPDATE, entity)
                : this.savedInBd(ActionTitle.BUILDING_CREATE, entity);
    }
    private BuildingDto savedInBd(ActionTitle actionTitle, BuildingEntity entity){
        BuildingMapper mapper = new BuildingMapperImpl();
        BuildingEntity saved = this.repository.save(entity);
        actionPersistence.createAction(actionTitle);
        return mapper.toDto(saved);
    }
    private Optional<BuildingEntity> findById(String id) {
        return this.repository.findById(id);
    }

}
