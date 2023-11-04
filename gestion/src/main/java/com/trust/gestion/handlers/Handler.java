package com.trust.gestion.handlers;

import com.trust.gestion.domain.OwnerContactInformationDto;
import com.trust.gestion.domain.OwnerIdentificationDto;
import com.trust.gestion.domain.OwnerInformationDto;
import com.trust.gestion.entities.ApartmentEntity;
import com.trust.gestion.entities.BuildingEntity;
import com.trust.gestion.entities.OwnerAddressEntity;
import com.trust.gestion.entities.OwnerContactInformationEntity;
import com.trust.gestion.entities.OwnerEntity;
import com.trust.gestion.entities.OwnerIdentificationEntity;
import com.trust.gestion.entities.OwnerInformationEntity;
import com.trust.gestion.resources.ApartmentResource;
import com.trust.gestion.resources.OwnerAddressResource;
import com.trust.gestion.resources.OwnerContactInformationRessource;
import com.trust.gestion.resources.OwnerIdentificationRessource;
import com.trust.gestion.resources.OwnerInformationResource;
import com.trust.gestion.resources.OwnerResource;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Component
@AllArgsConstructor
@Slf4j
public class Handler {


    public OwnerInformationEntity ownerInformationHandler(OwnerInformationResource resource, Optional<OwnerInformationEntity> optionalEntity){
        return optionalEntity.isPresent() ? this.updateOwnerInformation(resource, optionalEntity.get()) : this.createOwnerInformation(resource);
    }
    public OwnerAddressEntity ownerAddressHandler(OwnerAddressResource resource, Optional<OwnerAddressEntity> optionalEntity){
        return optionalEntity.isPresent() ? this.updateOwnerAddress(resource, optionalEntity.get()) : this.createOwnerAddress(resource);
    }

    public OwnerContactInformationEntity ownerContactInformationHandler(OwnerContactInformationRessource resource, Optional<OwnerContactInformationEntity> optionalEntity){
        return optionalEntity.isPresent() ? this.updateOwnerContactInformation(resource, optionalEntity.get()) : this.createOwnerContactInformation(resource);
    }
    public OwnerIdentificationEntity ownerIdentificationHandler(OwnerIdentificationRessource resource, Optional<OwnerIdentificationEntity> optionalEntity) {
        return optionalEntity.isPresent() ? this.updateOwnerIdentification(resource, optionalEntity.get()) : this.createOwnerIdentification(resource);
    }

    private ApartmentResource findApartmentResource(String id, List<ApartmentResource> resources) {
        return resources.stream()
                .filter(resource -> resource.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    private ApartmentEntity findApartmentById(String id, BuildingEntity entity) {
        return entity.getApartments()
                .stream()
                .filter(apartmentEntity -> apartmentEntity.getId().equals(id))
                .findFirst().orElse(null);
    }


    private List<OwnerContactInformationDto> setContactInformationDateOnCreate(List<OwnerContactInformationDto> dtos) {
        return dtos.stream()
                .map(dto -> dto.toBuilder()
                        .registrationDate(Instant.now())
                        .lastUpdated(Instant.now())
                        .build())
                .toList();
    }


    private List<OwnerAddressEntity> updatedOwnerAddress(OwnerResource resource, OwnerEntity entity) {
        return Collections.emptyList();
    }
    private OwnerAddressEntity createOwnerAddress(OwnerAddressResource resource) {
      return null;
    }

    private OwnerAddressEntity updateOwnerAddress(OwnerAddressResource resource, OwnerAddressEntity entity) {
        return null;
    }

    private List<OwnerInformationDto> updateOwnerInformation(OwnerResource resource, OwnerEntity entity) {
        return Collections.emptyList();
    }

    private List<OwnerIdentificationDto> updateOwnerIdentification(OwnerResource resource, OwnerEntity entity) {
        return Collections.emptyList();
    }

    private List<OwnerContactInformationDto> updateOwnerContactInformation(OwnerResource resource, OwnerEntity entity) {
        return Collections.emptyList();
    }

    private OwnerInformationEntity createOwnerInformation(OwnerInformationResource resource) {
        return null;
    }

    private OwnerInformationEntity updateOwnerInformation(OwnerInformationResource resource, OwnerInformationEntity ownerInformationEntity) {
        return null;
    }

    private OwnerContactInformationEntity createOwnerContactInformation(OwnerContactInformationRessource resource) {
        return null;
    }

    private OwnerContactInformationEntity updateOwnerContactInformation(OwnerContactInformationRessource resource, OwnerContactInformationEntity entity) {
        return null;

    }

    private OwnerIdentificationEntity updateOwnerIdentification(OwnerIdentificationRessource resource, OwnerIdentificationEntity entity) {
        return null;
    }

    private OwnerIdentificationEntity createOwnerIdentification(OwnerIdentificationRessource resource) {
        return null;
    }
    //TENANT

}
