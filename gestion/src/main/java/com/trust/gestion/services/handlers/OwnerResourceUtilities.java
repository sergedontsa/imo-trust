package com.trust.gestion.services.handlers;


import com.trust.gestion.services.domain.OwnerAddressDto;
import com.trust.gestion.services.domain.OwnerContactInformationDto;
import com.trust.gestion.services.domain.OwnerIdentificationDto;
import com.trust.gestion.services.domain.OwnerInformationDto;
import com.trust.gestion.services.mappers.OwnerAddressMapper;
import com.trust.gestion.services.mappers.OwnerAddressMapperImpl;
import com.trust.gestion.services.mappers.OwnerContactInformationMapper;
import com.trust.gestion.services.mappers.OwnerContactInformationMapperImpl;
import com.trust.gestion.services.mappers.OwnerIdentificationMapper;
import com.trust.gestion.services.mappers.OwnerIdentificationMapperImpl;
import com.trust.gestion.services.mappers.OwnerInformationMapper;
import com.trust.gestion.services.mappers.OwnerInformationMapperImpl;
import com.trust.gestion.services.resources.OwnerAddressResource;
import com.trust.gestion.services.resources.OwnerContactInformationRessource;
import com.trust.gestion.services.resources.OwnerIdentificationRessource;
import com.trust.gestion.services.resources.OwnerInformationResource;

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
