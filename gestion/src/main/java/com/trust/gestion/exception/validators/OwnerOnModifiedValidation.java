package com.trust.gestion.exception.validators;


import com.trust.gestion.exception.OwnerValidException;
import com.trust.gestion.entities.OwnerEntity;
import com.trust.gestion.resources.OwnerResource;

import java.util.Optional;

public class OwnerOnModifiedValidation implements Validation<OwnerResource, OwnerEntity> {
    @Override
    public void validate(OwnerResource resource, Optional<OwnerEntity> existingOwner) throws OwnerValidException {
        OwnerCommonValidation ownerCommonValidation = new OwnerCommonValidation();
        ownerCommonValidation.validate(resource, existingOwner);

    }
}
