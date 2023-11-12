package com.trust.gestion.persistence;

import com.trust.gestion.domain.BuildingOwnerDto;
import com.trust.gestion.domain.OwnerDto;
import com.trust.gestion.entities.BuildingEntity;
import com.trust.gestion.entities.BuildingOwnerEntity;
import com.trust.gestion.mappers.BuildingOwnerMapper;
import com.trust.gestion.mappers.BuildingOwnerMapperImpl;
import com.trust.gestion.mappers.OwnerMapper;
import com.trust.gestion.mappers.OwnerMapperImpl;
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

    public List<OwnerDto> findByBuilding(BuildingEntity building){
        OwnerMapper mapper = new OwnerMapperImpl();

        return this.repository.findByBuilding(building)
                .stream()
                .map(BuildingOwnerEntity::getOwner)
                .map(mapper::toDto)
                .toList();
    }
    public void save(BuildingOwnerDto dto){
        BuildingOwnerMapper mapper = new BuildingOwnerMapperImpl();
        this.repository.save(mapper.toEntity(dto));
    }
}
