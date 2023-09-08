package com.trust.gestion.exception.validators;

import com.trust.gestion.enums.Gender;
import com.trust.gestion.exception.OwnerValidException;
import com.trust.gestion.services.entities.TenantEntity;
import com.trust.gestion.services.resources.TenantResource;
import org.apache.commons.lang3.StringUtils;

import java.util.EnumSet;
import java.util.Optional;

public class TenantCommonValidation implements Validation<TenantResource, TenantEntity>{
    @Override
    public void validate(TenantResource resource, Optional<TenantEntity> existingEntity) throws OwnerValidException {
        this.validateFirstName(resource);
        this.validateLastName(resource);
        this.validateGender(resource);
    }

    private void validateFirstName(TenantResource resource){
        if (StringUtils.isEmpty(resource.getFirstName())){
            throw new OwnerValidException("First name  is mandatory");
        }
    }
    private void validateLastName(TenantResource resource){
        if (StringUtils.isEmpty(resource.getLastName())){
            throw new OwnerValidException("Last name is mandatory");
        }
    }
    private void validateGender(TenantResource resource){
        if (!EnumSet.of(Gender.MALE, Gender.FEMALE).contains(resource.getGender())){
            throw new OwnerValidException("Gender is not valid");
        }
    }
}
