package com.trust.gestion.exception.validators;

import com.trust.gestion.exception.OwnerValidException;
import com.trust.gestion.services.entities.OwnerEntity;
import com.trust.gestion.services.resources.OwnerResource;

import java.util.Optional;

public interface OwnerValidation {
    void validate(OwnerResource resource, Optional<OwnerEntity> existingOwner) throws OwnerValidException;

}
