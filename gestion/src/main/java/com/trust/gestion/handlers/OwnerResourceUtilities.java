package com.trust.gestion.handlers;


import com.trust.gestion.domain.OwnerAddressDto;
import com.trust.gestion.domain.OwnerContactInformationDto;
import com.trust.gestion.domain.OwnerIdentificationDto;
import com.trust.gestion.domain.OwnerInformationDto;
import com.trust.gestion.mappers.OwnerAddressMapper;
import com.trust.gestion.mappers.OwnerAddressMapperImpl;
import com.trust.gestion.mappers.OwnerContactInformationMapper;
import com.trust.gestion.mappers.OwnerContactInformationMapperImpl;
import com.trust.gestion.mappers.OwnerIdentificationMapper;
import com.trust.gestion.mappers.OwnerIdentificationMapperImpl;
import com.trust.gestion.mappers.OwnerInformationMapper;
import com.trust.gestion.mappers.OwnerInformationMapperImpl;
import com.trust.gestion.resources.OwnerAddressResource;
import com.trust.gestion.resources.OwnerContactInformationRessource;
import com.trust.gestion.resources.OwnerIdentificationRessource;
import com.trust.gestion.resources.OwnerInformationResource;
import java.time.Instant;
import java.util.List;

public class OwnerResourceUtilities {
    private OwnerResourceUtilities() {
    }
    public static List<OwnerIdentificationDto> getOwnerIdentificationDtoFromResource(List<OwnerIdentificationRessource> resources){
        OwnerIdentificationMapper mapper = new OwnerIdentificationMapperImpl();
        return resources.stream().map(resource -> {
            resource.setRegistrationDate(Instant.now());
            resource.setLastUpdated(Instant.now());
            return resource;
        }).map(mapper::fromResourceToDto).toList();
    }
    public static List<OwnerContactInformationDto> getOwnerContactInformationFromResource(List<OwnerContactInformationRessource> resources){
        OwnerContactInformationMapper mapper = new OwnerContactInformationMapperImpl();
        return resources.stream().map(resource -> {
            resource.setRegistrationDate(Instant.now());
            resource.setLastUpdated(Instant.now());
            return resource;
        }).map(mapper::fromResourceToDto).toList();
    }
    public static List<OwnerInformationDto> getOwnerInformationDtoFromResource(List<OwnerInformationResource> resources) {
        OwnerInformationMapper mapper = new OwnerInformationMapperImpl();
        return resources.stream().map(resource -> {
            resource.setRegistrationDate(Instant.now());
            resource.setLastUpdated(Instant.now());
            return resource;
        }).map(mapper::fromResourceToDto).toList();

    }

    public static List<OwnerAddressDto> getAddressDtoFromResource(List<OwnerAddressResource> resources) {
        OwnerAddressMapper mapper = new OwnerAddressMapperImpl();
        return resources.stream().map(resource -> {
            resource.setRegistrationDate(Instant.now());
            resource.setLastUpdated(Instant.now());
            return resource;
        }).map(mapper::fromResourceToDto).toList();
    }

}
