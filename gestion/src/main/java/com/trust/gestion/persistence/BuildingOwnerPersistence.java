package com.trust.gestion.persistence;

import com.trust.gestion.domain.BuildingOwnerDto;
import com.trust.gestion.domain.OwnerDto;
import com.trust.gestion.entities.BuildingEntity;
import com.trust.gestion.entities.BuildingOwnerEntity;
import com.trust.gestion.mappers.BuildingOwnerMapper;
import com.trust.gestion.mappers.BuildingOwnerMapperImpl;
import com.trust.gestion.repositories.BuildingOwnerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
@RequiredArgsConstructor
public class BuildingOwnerPersistence {
    private final BuildingOwnerRepository repository;
    private final OwnerPersistence ownerPersistence;

    public List<OwnerDto> findByBuilding(BuildingEntity building){
        return this.repository.findByBuilding(building)
                .stream()
                .map(BuildingOwnerEntity::getOwner)
                .toList()
                .stream()
                .map(entity -> this.ownerPersistence.getOne(entity.getId()))
                .toList();

    }
    public void save(BuildingOwnerDto dto){
        BuildingOwnerMapper mapper = new BuildingOwnerMapperImpl();
        this.repository.save(mapper.toEntity(dto));
    }
}
