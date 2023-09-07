package com.trust.gestion.exception.validators;


import com.trust.gestion.exception.OwnerValidException;
import com.trust.gestion.services.domain.OwnerDto;
import com.trust.gestion.services.resources.OwnerResource;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class OwnerOnCreatValidation implements OwnerValidation {
    @Override
    public void validate(OwnerResource resource, Optional<OwnerDto> existingOwner) throws OwnerValidException {
        this.validateFirstName(resource);
        this.validateLastName(resource);
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
}
