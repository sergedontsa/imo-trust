package com.trust.gestion.exception.validators;

import com.trust.gestion.exception.OwnerValidException;
import com.trust.gestion.services.entities.TenantEntity;
import com.trust.gestion.services.resources.TenantResource;

import java.util.Optional;

public class TenantOnCreationValidation implements Validation<TenantResource, TenantEntity> {

    @Override
    public void validate(TenantResource resource, Optional<TenantEntity> existingEntity) throws OwnerValidException {
        TenantCommonValidation ownerCommonValidation = new TenantCommonValidation();
        ownerCommonValidation.validate(resource, existingEntity);
        this.validateCountryOfOrigin(resource);
        this.validateDateOfBirth(resource);
        this.validateContactType(resource);
    }
    private void validateDateOfBirth(TenantResource resource){
        if (resource.getDateOfBirth() == null){
            throw new OwnerValidException("Date of birth is mandatory");
        }
    }
    private void validateCountryOfOrigin(TenantResource resource){
        if (resource.getCountryOfOrigin() == null){
            throw new OwnerValidException("Country of origin is mandatory");
        }
    }
    private void validateContactType(TenantResource resource){
        if (resource.getContactType() == null){
            throw new OwnerValidException("Contact type is mandatory");
        }
    }

}
