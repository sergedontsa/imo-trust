package com.trust.gestion.exception.validators;

import com.trust.gestion.enums.Gender;
import com.trust.gestion.services.resources.OwnerResource;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

public class OwnerDefaultValidatorConstraint implements ConstraintValidator<OwnerDefaultValidator, OwnerResource> {


    @Override
    public void initialize(OwnerDefaultValidator constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(OwnerResource value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }
        context.disableDefaultConstraintViolation();

        return this.validateGender(value, context)
                && this.validateFistName(value, context)
                && this.validateLastName(value, context);
    }
    private Boolean validateGender(OwnerResource resource, ConstraintValidatorContext context){
        List<Gender> genders = List.of(Gender.MALE, Gender.FEMALE);

        Boolean isValid =  genders.contains(resource.getGender());
        if (isValid.equals(Boolean.FALSE)){
            this.addConstraintViolationMessage(context,
                    "Gender cannot be empty",
                    "gender");

        }
        return isValid;
    }
    private Boolean validateFistName(OwnerResource resource, ConstraintValidatorContext context){
        Boolean isValid = resource.getFirstName().length() > 2 && StringUtils.isNotBlank(resource.getFirstName());
        if (isValid.equals(Boolean.FALSE)){
            this.addConstraintViolationMessage(context,
                    "First name cannot be empty",
                    "firstName");

        }
        return isValid;
    }
    private Boolean validateLastName(OwnerResource resource, ConstraintValidatorContext context){
        Boolean isValid = resource.getLastName().length() > 2 && StringUtils.isNotBlank(resource.getLastName());
        if (isValid.equals(Boolean.FALSE)){
            this.addConstraintViolationMessage(context,
                    "Last name cannot be empty",
                    "lastName");

        }
        return isValid;
    }
    private void addConstraintViolationMessage(ConstraintValidatorContext context, String message, String field){
        context.buildConstraintViolationWithTemplate(message).addConstraintViolation();
    }
}
