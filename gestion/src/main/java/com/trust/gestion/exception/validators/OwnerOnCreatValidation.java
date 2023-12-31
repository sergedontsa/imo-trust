package com.trust.gestion.exception.validators;


import com.trust.gestion.exception.OwnerValidException;
import com.trust.gestion.entities.OwnerEntity;
import com.trust.gestion.resources.OwnerResource;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class OwnerOnCreatValidation implements Validation<OwnerResource, OwnerEntity> {

    @Override
    public void validate(OwnerResource resource, Optional<OwnerEntity> existingOwner) throws OwnerValidException {
        OwnerCommonValidation ownerCommonValidation = new OwnerCommonValidation();
        ownerCommonValidation.validate(resource, existingOwner);
        this.validateAddress(resource);
        this.validateInformation(resource);
        this.validateContact(resource);
    }

    private void validateAddress(OwnerResource resource){

    }
    private void validateContact(OwnerResource resource){

    }
    private void validateInformation(OwnerResource resource){

    }

}
