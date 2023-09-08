package com.trust.gestion.exception.validators;


import com.trust.gestion.exception.OwnerValidException;
import com.trust.gestion.services.entities.OwnerEntity;
import com.trust.gestion.services.resources.OwnerResource;

import java.util.Optional;

public class OwnerOnModifiedValidation implements OwnerValidation{
    @Override
    public void validate(OwnerResource resource, Optional<OwnerEntity> existingOwner) throws OwnerValidException {
        OwnerCommonValidation commonValidation = new OwnerCommonValidation();
        commonValidation.validate(resource, existingOwner);

    }
}
