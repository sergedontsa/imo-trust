package com.trust.gestion.services.handlers;

import com.trust.gestion.enums.Status;
import com.trust.gestion.services.domain.ApartmentDto;
import com.trust.gestion.services.domain.BuildingDto;
import com.trust.gestion.services.domain.OwnerAddressDto;
import com.trust.gestion.services.domain.OwnerContactInformationDto;
import com.trust.gestion.services.domain.OwnerDto;
import com.trust.gestion.services.domain.OwnerIdentificationDto;
import com.trust.gestion.services.domain.OwnerInformationDto;
import com.trust.gestion.services.domain.TenantDto;
import com.trust.gestion.services.entities.ApartmentEntity;
import com.trust.gestion.services.entities.BuildingEntity;
import com.trust.gestion.services.entities.OwnerAddressEntity;
import com.trust.gestion.services.entities.OwnerContactInformationEntity;
import com.trust.gestion.services.entities.OwnerEntity;
import com.trust.gestion.services.entities.OwnerIdentificationEntity;
import com.trust.gestion.services.entities.OwnerInformationEntity;
import com.trust.gestion.services.entities.TenantEntity;
import com.trust.gestion.services.mappers.ApartmentMapper;
import com.trust.gestion.services.mappers.ApartmentMapperImpl;
import com.trust.gestion.services.mappers.BuildingMapper;
import com.trust.gestion.services.mappers.BuildingMapperImpl;
import com.trust.gestion.services.mappers.OwnerAddressMapper;
import com.trust.gestion.services.mappers.OwnerAddressMapperImpl;
import com.trust.gestion.services.mappers.OwnerContactInformationMapper;
import com.trust.gestion.services.mappers.OwnerContactInformationMapperImpl;
import com.trust.gestion.services.mappers.OwnerIdentificationMapper;
import com.trust.gestion.services.mappers.OwnerIdentificationMapperImpl;
import com.trust.gestion.services.mappers.OwnerInformationMapper;
import com.trust.gestion.services.mappers.OwnerInformationMapperImpl;
import com.trust.gestion.services.mappers.OwnerMapper;
import com.trust.gestion.services.mappers.OwnerMapperImpl;
import com.trust.gestion.services.mappers.TenantMapper;
import com.trust.gestion.services.mappers.TenantMapperImpl;
import com.trust.gestion.services.resources.ApartmentResource;
import com.trust.gestion.services.resources.BuildingResource;
import com.trust.gestion.services.resources.OwnerAddressResource;
import com.trust.gestion.services.resources.OwnerContactInformationRessource;
import com.trust.gestion.services.resources.OwnerIdentificationRessource;
import com.trust.gestion.services.resources.OwnerInformationResource;
import com.trust.gestion.services.resources.OwnerResource;
import com.trust.gestion.services.resources.TenantResource;
import com.trust.gestion.utilities.Utilities;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class Handler {
    public BuildingDto buildingHandler(BuildingResource resource, Optional<BuildingEntity> optionalEntity) {
        return optionalEntity.isPresent() ? this.updateBuilding(resource, optionalEntity.get()) : this.createBuilding(resource);
    }

    public OwnerDto ownerHandler(OwnerResource resource, Optional<OwnerEntity> optionalEntity) {
        return optionalEntity.isPresent() ? updateOwner(resource, optionalEntity.get()) : createOwner(resource);
    }

    public OwnerInformationDto ownerInformationHandler(OwnerInformationResource resource, Optional<OwnerInformationEntity> optionalEntity){
        return optionalEntity.isPresent() ? this.updateOwnerInformation(resource, optionalEntity.get()) : this.createOwnerInformation(resource);
    }
    public OwnerAddressDto ownerAddressHandler(OwnerAddressResource resource, Optional<OwnerAddressEntity> optionalEntity){
        return optionalEntity.isPresent() ? this.updateOwnerAddress(resource, optionalEntity.get()) : this.createOwnerAddress(resource);
    }

    public OwnerContactInformationDto ownerContactInformationHandler(OwnerContactInformationRessource resource, Optional<OwnerContactInformationEntity> optionalEntity){
        return optionalEntity.isPresent() ? this.updateOwnerContactInformation(resource, optionalEntity.get()) : this.createOwnerContactInformation(resource);
    }
    public OwnerIdentificationDto ownerIdentificationHandler(OwnerIdentificationRessource resource, Optional<OwnerIdentificationEntity> optionalEntity) {
        return optionalEntity.isPresent() ? this.updateOwnerIdentification(resource, optionalEntity.get()) : this.createOwnerIdentification(resource);
    }
    public ApartmentDto apartmentHandle(ApartmentResource resource, Optional<ApartmentEntity> optionalEntity) {
        return optionalEntity.isPresent() ? this.updateApartment(resource, optionalEntity.get()) : this.createApartment(resource);
    }

    public TenantDto tenantHandler(TenantResource resource, Optional<TenantEntity> optionalEntity) {
        return optionalEntity.isPresent() ? this.updateTenant(resource, optionalEntity.get()) : this.createTenant(resource);
    }

    //BUILDING
    private BuildingDto createBuilding(BuildingResource resource) {
        BuildingMapper mapper = new BuildingMapperImpl();
        BuildingDto dto = mapper.fromResourceToDto(resource);

        return dto.toBuilder()
                .apartments(
                        resource.getApartments()
                                .stream()
                                .map(apartmentResource -> (this.apartmentHandle(apartmentResource, empty())))
                                .toList()
                )
                .id(Utilities.getBuildingID())
                .assigned(Boolean.FALSE)
                .registrationDate(Instant.now())
                .lastUpdated(Instant.now())
                .build();
    }

    private BuildingDto updateBuilding(BuildingResource resource, BuildingEntity entity) {
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

    private List<ApartmentDto> updateApartments(List<ApartmentResource> resources, BuildingEntity entity) {

        List<String> ids = resources.stream().map(ApartmentResource::getId).toList()
                .parallelStream()
                .filter(Objects::nonNull)
                .toList();

        if (CollectionUtils.isEmpty(ids)) {
            return resources.stream()
                    .map(resource -> this.apartmentHandle(resource, empty()))
                    .toList();
        } else {
            return ids.stream()
                    .map(id -> {
                        ApartmentResource apartmentResource = this.findApartmentResource(id, resources);
                        if (ObjectUtils.isNotEmpty(this.findApartmentById(id, entity))) {
                            ApartmentEntity apartmentEntity = this.findApartmentById(id, entity);
                            return this.apartmentHandle(apartmentResource, Optional.of(apartmentEntity));
                        } else {
                            throw new IllegalArgumentException("Apartment your are trying to update could not be found found " + id);
                        }

                    }).toList();
        }
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

    //APARTMENT
    private ApartmentDto updateApartment(ApartmentResource resource, ApartmentEntity entity) {
        return null;
    }

    private ApartmentDto createApartment(ApartmentResource resource) {
        ApartmentMapper mapper = new ApartmentMapperImpl();
        ApartmentDto dto = mapper.fromResourceToDto(resource);
        return dto.toBuilder()
                .status(Status.PENDING_INSPECTION)
                .id(Utilities.getApartmentID())
                .registrationDate(Instant.now())
                .lastUpdated(Instant.now())
                .build();

    }

    //OWNER
    private OwnerDto createOwner(OwnerResource resource) {
        OwnerMapper mapper = new OwnerMapperImpl();
        OwnerDto dto = mapper.fromResourceToDto(resource);
        return dto.toBuilder().id(Utilities.getManagerId())
                .registrationDate(Instant.now())
                .lastUpdated(Instant.now())
                .address(this.setOwnerAddressDateOnCreate(dto.getAddress()))
                .information(this.setInformationDateOnCreate(dto.getInformation()))
                .identifications(this.setIdentificationDateOnCreate(dto.getIdentifications()))
                .contacts(this.setContactInformationDateOnCreate(dto.getContacts()))
                .build();

    }

    private List<OwnerAddressDto> setOwnerAddressDateOnCreate(List<OwnerAddressDto> dtos) {
        return dtos.stream()
                .map(dto -> dto.toBuilder()
                        .registrationDate(Instant.now())
                        .lastUpdated(Instant.now())
                        .build())
                .toList();
    }

    private List<OwnerInformationDto> setInformationDateOnCreate(List<OwnerInformationDto> dtos) {
        return dtos.stream()
                .map(dto -> dto.toBuilder()
                        .registrationDate(Instant.now())
                        .lastUpdated(Instant.now())
                        .build())
                .toList();
    }

    private List<OwnerIdentificationDto> setIdentificationDateOnCreate(List<OwnerIdentificationDto> dtos) {
        return dtos.stream()
                .map(dto -> dto.toBuilder()
                        .registrationDate(Instant.now())
                        .lastUpdated(Instant.now())
                        .build())
                .toList();
    }

    private List<OwnerContactInformationDto> setContactInformationDateOnCreate(List<OwnerContactInformationDto> dtos) {
        return dtos.stream()
                .map(dto -> dto.toBuilder()
                        .registrationDate(Instant.now())
                        .lastUpdated(Instant.now())
                        .build())
                .toList();
    }

    private OwnerDto updateOwner(OwnerResource resource, OwnerEntity entity) {
        OwnerMapper mapper = new OwnerMapperImpl();
        OwnerDto dto = mapper.fromResourceToDto(resource);

        List<OwnerAddressDto> updatedAddress = this.updatedOwnerAddress(resource, entity);
        List<OwnerInformationDto> updateInformation = this.updateOwnerInformation(resource, entity);
        List<OwnerIdentificationDto> updateIdentification = this.updateOwnerIdentification(resource, entity);
        List<OwnerContactInformationDto> updateContact = this.updateOwnerContactInformation(resource, entity);

        dto = dto.toBuilder()
                .registrationDate(entity.getRegistrationDate())
                .lastUpdated(Instant.now())
                .id(entity.getId())
                .build();


        OwnerDto update = mapper.toDto(mapper.update(dto, entity));
        return update.toBuilder()
                .address(updatedAddress)
                .information(updateInformation)
                .identifications(updateIdentification)
                .contacts(updateContact).build();
    }

    private List<OwnerAddressDto> updatedOwnerAddress(OwnerResource resource, OwnerEntity entity) {
        return resource.getAddress().stream().map(OwnerAddressResource::getId).toList().stream().map(id -> {
            OwnerAddressEntity addressEntity = this.findAddressEntityById(id, entity);
            OwnerAddressResource addressResource = this.findAddressResourceById(id, resource);
            return this.ownerAddressHandler(addressResource, Optional.of(addressEntity));
        }).toList();
    }
    private OwnerAddressDto createOwnerAddress(OwnerAddressResource resource) {
        OwnerAddressMapper mapper = new OwnerAddressMapperImpl();
        OwnerAddressDto dto = mapper.fromResourceToDto(resource);
        return dto.toBuilder()
                .registrationDate(Instant.now())
                .lastUpdated(Instant.now())
                .build();
    }

    private OwnerAddressDto updateOwnerAddress(OwnerAddressResource resource, OwnerAddressEntity entity) {
        OwnerAddressMapper mapper = new OwnerAddressMapperImpl();
        OwnerAddressDto dto = mapper.fromResourceToDto(resource);
        dto.toBuilder().registrationDate(entity.getRegistrationDate())
                .lastUpdated(Instant.now())
                .id(entity.getId())
                .build();
        OwnerAddressEntity updatedEntity = mapper.partialUpdate(dto, entity);
        return mapper.toDto(updatedEntity);
    }

    private List<OwnerInformationDto> updateOwnerInformation(OwnerResource resource, OwnerEntity entity) {
        return resource.getInformation().stream().map(OwnerInformationResource::getId).toList().stream().map(id -> {
            OwnerInformationEntity informationEntity = this.findInformationEntityById(id, entity);
            OwnerInformationResource informationResource = this.findInformationResourceById(id, resource);

            return this.ownerInformationHandler(informationResource, Optional.of(informationEntity));

        }).toList();
    }

    private List<OwnerIdentificationDto> updateOwnerIdentification(OwnerResource resource, OwnerEntity entity) {
        return resource.getIdentifications().stream().map(OwnerIdentificationRessource::getId).toList().stream().map(id -> {
            OwnerIdentificationEntity identificationEntity = this.findIdentificationEntityById(id, entity);
            OwnerIdentificationRessource identificationResource = this.findIdentificationResourceById(id, resource);
            return this.ownerIdentificationHandler(identificationResource, Optional.of(identificationEntity));
        }).toList();
    }

    private List<OwnerContactInformationDto> updateOwnerContactInformation(OwnerResource resource, OwnerEntity entity) {
        return resource.getContacts().stream().map(OwnerContactInformationRessource::getId).toList().stream().map(id -> {
            OwnerContactInformationEntity contactEntity = this.findContactEntityById(id, entity);
            OwnerContactInformationRessource contactResource = this.findContactResourceById(id, resource);
            return ownerContactInformationHandler(contactResource, Optional.of(contactEntity));
        }).toList();
    }

    private OwnerContactInformationEntity findContactEntityById(Integer id, OwnerEntity entity) {
        Optional<OwnerContactInformationEntity> optional = entity.getContacts().stream().filter(contact -> contact.getId().equals(id)).findFirst();
        return optional.get();
    }

    private OwnerContactInformationRessource findContactResourceById(Integer id, OwnerResource resource) {
        Optional<OwnerContactInformationRessource> optional = resource.getContacts().stream().filter(contact -> contact.getId().equals(id)).findFirst();
        return optional.get();
    }

    private OwnerInformationEntity findInformationEntityById(Integer id, OwnerEntity entity) {
        Optional<OwnerInformationEntity> optional = entity.getInformation().stream().filter(information -> information.getId().equals(id)).findFirst();
        return optional.get();
    }

    private OwnerInformationResource findInformationResourceById(Integer id, OwnerResource resource) {
        Optional<OwnerInformationResource> optional = resource.getInformation().stream().filter(information -> information.getId().equals(id)).findFirst();
        return optional.get();
    }

    private OwnerIdentificationEntity findIdentificationEntityById(Integer id, OwnerEntity entity) {
        Optional<OwnerIdentificationEntity> optional = entity.getIdentifications().stream().filter(identification -> identification.getId().equals(id)).findFirst();
        return optional.get();
    }

    private OwnerIdentificationRessource findIdentificationResourceById(Integer id, OwnerResource resource) {
        Optional<OwnerIdentificationRessource> optional = resource.getIdentifications().stream().filter(identification -> identification.getId().equals(id)).findFirst();
        return optional.get();
    }

    private OwnerAddressEntity findAddressEntityById(Integer id, OwnerEntity entity) {
        Optional<OwnerAddressEntity> optional = entity.getAddress().stream().filter(address -> address.getId().equals(id)).findFirst();
        return optional.get();
    }

    private OwnerAddressResource findAddressResourceById(Integer id, OwnerResource resource) {
        Optional<OwnerAddressResource> optional = resource.getAddress().stream().filter(address -> address.getId().equals(id)).findFirst();
        return optional.get();
    }
    private OwnerInformationDto createOwnerInformation(OwnerInformationResource resource) {
        OwnerInformationMapper mapper = new OwnerInformationMapperImpl();
        OwnerInformationDto dto = mapper.fromResourceToDto(resource);
        return dto.toBuilder()
                .registrationDate(Instant.now())
                .lastUpdated(Instant.now())
                .build();
    }

    private OwnerInformationDto updateOwnerInformation(OwnerInformationResource resource, OwnerInformationEntity ownerInformationEntity) {
        OwnerInformationMapper mapper = new OwnerInformationMapperImpl();
        OwnerInformationDto dto = mapper.fromResourceToDto(resource);
        dto.toBuilder()
                .registrationDate(ownerInformationEntity.getRegistrationDate())
                .lastUpdated(Instant.now())
                .id(ownerInformationEntity.getId())
                .build();
        OwnerInformationEntity updatedEntity = mapper.partialUpdate(dto, ownerInformationEntity);
        return mapper.toDto(updatedEntity);
    }

    private OwnerContactInformationDto createOwnerContactInformation(OwnerContactInformationRessource resource) {
        OwnerContactInformationMapper mapper = new OwnerContactInformationMapperImpl();
        OwnerContactInformationDto dto = mapper.fromResourceToDto(resource);
        return dto.toBuilder()
                .registrationDate(Instant.now())
                .lastUpdated(Instant.now())
                .build();
    }

    private OwnerContactInformationDto updateOwnerContactInformation(OwnerContactInformationRessource resource, OwnerContactInformationEntity entity) {
        OwnerContactInformationMapper mapper = new OwnerContactInformationMapperImpl();
        OwnerContactInformationDto dto = mapper.fromResourceToDto(resource);
        dto.toBuilder()
                .registrationDate(entity.getRegistrationDate())
                .lastUpdated(Instant.now())
                .id(entity.getId())
                .build();
        OwnerContactInformationEntity updatedEntity = mapper.partialUpdate(dto, entity);
        return mapper.toDto(updatedEntity);

    }

    private OwnerIdentificationDto updateOwnerIdentification(OwnerIdentificationRessource resource, OwnerIdentificationEntity entity) {
        OwnerIdentificationMapper mapper = new OwnerIdentificationMapperImpl();
        OwnerIdentificationDto dto = mapper.fromResourceToDto(resource);
        dto.toBuilder()
                .registrationDate(entity.getRegistrationDate())
                .lastUpdated(Instant.now())
                .build();
        OwnerIdentificationEntity updateEntity = mapper.partialUpdate(dto, entity);
        return mapper.toDto(updateEntity);
    }

    private OwnerIdentificationDto createOwnerIdentification(OwnerIdentificationRessource resource) {


        OwnerIdentificationMapper mapper = new OwnerIdentificationMapperImpl();
        OwnerIdentificationDto dto = mapper.fromResourceToDto(resource);
        return dto.toBuilder()
                .registrationDate(Instant.now())
                .lastUpdated(Instant.now())
                .build();
    }
    //TENANT
    private TenantDto createTenant(TenantResource resource) {
        TenantMapper mapper = new TenantMapperImpl();
        TenantDto dto = mapper.fromResourceToDto(resource);
        return dto.toBuilder()
                .id(Utilities.getTenantId())
                .status(Status.PENDING)
                .lastUpdated(Instant.now())
                .registrationDate(Instant.now())
                .build();

    }

    private TenantDto updateTenant(TenantResource resource, TenantEntity entity) {
        TenantMapper mapper = new TenantMapperImpl();
        TenantDto dto = mapper.fromResourceToDto(resource);
        TenantEntity updatedEntity = mapper.partialUpdate(dto, entity);
        return mapper.toDto(updatedEntity)
                .toBuilder()
                .lastUpdated(Instant.now())
                .build();

    }
}
