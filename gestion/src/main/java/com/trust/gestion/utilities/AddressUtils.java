package com.trust.gestion.utilities;

import com.trust.gestion.entities.AddressEntity;
import com.trust.gestion.exception.TrustImoException;
import com.trust.gestion.resources.AddressResource;

import java.util.List;

import static java.util.stream.Collectors.groupingBy;

public class AddressUtils {
    private AddressUtils() {
    }
    public static void validateDuplicatedAddressType(List<AddressResource> resources){

        resources.stream()
                .collect(groupingBy(AddressResource::getType))
                .forEach((key, value) -> {
            if(value.size() > 1){
                throw new TrustImoException("Duplicated address type");
            }
        });

    }
    public static void validateAddressTypeAlreadyExist(List<AddressResource> resources, List<AddressEntity> entities){
        if(resources.stream().anyMatch(resource -> entities.stream().anyMatch(entity -> resource.getType().equals(entity.getType())))){
            throw new TrustImoException("Address type already exist");
        }
    }
}
