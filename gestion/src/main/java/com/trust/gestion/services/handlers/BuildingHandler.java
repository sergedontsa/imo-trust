/*
 * Copyright (c) 2023.  - Serge G. Dontsa
 */

package com.trust.gestion.services.handlers;


import com.trust.gestion.services.domain.ApartmentDto;
import com.trust.gestion.services.domain.BuildingDto;
import com.trust.gestion.services.entities.ApartmentEntity;
import com.trust.gestion.services.entities.BuildingEntity;
import com.trust.gestion.services.mappers.BuildingMapper;
import com.trust.gestion.services.mappers.BuildingMapperImpl;
import com.trust.gestion.services.resources.ApartmentResource;
import com.trust.gestion.services.resources.BuildingResource;
import com.trust.gestion.utilities.Utilities;
import lombok.AllArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static java.util.Optional.empty;

@Component
@AllArgsConstructor
public class BuildingHandler {

    public BuildingDto handle(BuildingResource resource, Optional<BuildingEntity> optionalEntity) {
        return optionalEntity.isPresent() ? update(resource, optionalEntity.get()) : create(resource);
    }
    private BuildingDto create(BuildingResource resource) {
        BuildingMapper mapper = new BuildingMapperImpl();
        BuildingDto dto = mapper.fromResourceToDto(resource);

        return dto.toBuilder()
                .apartments(
                        resource.getApartments()
                                .stream()
                                .map(apartmentResource -> (new ApartmentHandler()).handle(apartmentResource, empty()))
                                .toList()
                )
        .id(Utilities.getBuildingID())
        .registrationDate(Instant.now())
        .lastUpdated(Instant.now())
        .build();
    }
    private BuildingDto update(BuildingResource resource, BuildingEntity entity) {
       BuildingMapper mapper = new BuildingMapperImpl();
       BuildingDto dto = mapper.fromResourceToDto(resource);
       List<ApartmentDto> apartmentDtos = this.updateApartments(resource.getApartments(), entity);

       dto = dto.toBuilder()
               .registrationDate(entity.getRegistrationDate())
               .lastUpdated(Instant.now())
               .id(entity.getId())
               .build();

       BuildingEntity entityUpdate = mapper.partialUpdate(dto, entity);
       BuildingDto updated = mapper.toDto(entityUpdate);

       return updated.toBuilder()
               .apartments(apartmentDtos)
               .build();

    }
    private List<ApartmentDto> updateApartments(List<ApartmentResource> resources, BuildingEntity entity){
        ApartmentHandler handler = new ApartmentHandler();

        List<Integer> ids = resources.stream().map(ApartmentResource::getId).toList()
                .parallelStream()
                .filter(Objects::nonNull)
                .toList();

        if (CollectionUtils.isEmpty(ids)){
            return resources.stream()
                    .map(resource -> handler.handle(resource, empty()))
                    .toList();
        }else {
            return ids.stream()
                    .map(id -> {
                        ApartmentResource apartmentResource = this.findApartmentResource(id, resources);
                        if (ObjectUtils.isNotEmpty(this.findApartmentById(id, entity))) {
                            ApartmentEntity apartmentEntity = this.findApartmentById(id, entity);
                            return handler.handle(apartmentResource, Optional.of(apartmentEntity));
                        }else {
                            throw new IllegalArgumentException("Apartment your are trying to update could not be found found " + id);
                        }

                    }).toList();
        }
    }

    private ApartmentResource findApartmentResource(Integer id, List<ApartmentResource> resources) {
        return resources.stream()
                .filter(resource -> resource.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    private ApartmentEntity findApartmentById(Integer id, BuildingEntity entity) {
        return entity.getApartments()
                .stream()
                .filter(apartmentEntity -> apartmentEntity.getId().equals(id))
                .findFirst().orElse(null);
    }

}
