package com.trust.gestion.exception.validators;

import com.trust.gestion.enums.Gender;
import com.trust.gestion.exception.OwnerValidException;
import com.trust.gestion.services.entities.OwnerEntity;
import com.trust.gestion.services.resources.OwnerResource;
import org.apache.commons.lang3.StringUtils;

import java.util.EnumSet;
import java.util.Optional;


public class OwnerCommonValidation implements OwnerValidation{
    @Override
    public void validate(OwnerResource resource, Optional<OwnerEntity> existingOwner){
        this.validateFirstName(resource);
        this.validateLastName(resource);
        this.validateGender(resource);
    }
    private void validateFirstName(OwnerResource resource){
        if (StringUtils.isEmpty(resource.getFirstName())){
            throw new OwnerValidException("First name  is mandatory");
        }
    }
    private void validateLastName(OwnerResource resource){
        if (StringUtils.isEmpty(resource.getLastName())){
            throw new OwnerValidException("Last name is mandatory");
        }
    }
    private void validateGender(OwnerResource resource){

        if (!EnumSet.of(Gender.MALE, Gender.FEMALE).contains(resource.getGender())){
            throw new OwnerValidException("Gender is not valid");
        }
    }
}
