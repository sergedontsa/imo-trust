package com.trust.gestion.persistence;

import com.trust.gestion.enums.ActionTitle;
import com.trust.gestion.entities.ApartmentEntity;
import com.trust.gestion.repositories.ApartmentRepository;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ApartmentPersistence {
    private final ApartmentRepository repository;
    private final ActionPersistence actionPersistence;

    public void save(ApartmentEntity entity) {
               this.saveInBd(entity);
    }
    private void saveInBd(ApartmentEntity entity){
        ApartmentEntity saved = this.repository.save(entity);
        if (ObjectUtils.allNotNull(saved)){
            actionPersistence.createAction(ActionTitle.APARTMENT_CREATE);
        }

    }
}
