package com.trust.gestion.exception.validators;

import com.trust.gestion.exception.OwnerValidException;
import com.trust.gestion.services.domain.OwnerDto;
import com.trust.gestion.services.resources.OwnerResource;

import java.util.Optional;

public interface OwnerValidation {
    void validate(OwnerResource resource, Optional<OwnerDto> existingOwner) throws OwnerValidException;

}
