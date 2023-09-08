package com.trust.gestion.exception.validators;


import com.trust.gestion.exception.OwnerValidException;
import com.trust.gestion.services.entities.OwnerEntity;
import com.trust.gestion.services.resources.OwnerResource;
import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
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
        if (CollectionUtils.isEmpty(resource.getAddress())){
            throw new OwnerValidException("Address is mandatory");
        }
    }
    private void validateContact(OwnerResource resource){
        if (CollectionUtils.isEmpty(resource.getContacts())){
            throw new OwnerValidException("Contact is mandatory");
        }
    }
    private void validateInformation(OwnerResource resource){
        if (CollectionUtils.isEmpty(resource.getInformation())){
            throw new OwnerValidException("Information is mandatory");
        }
    }

}
