package com.trust.gestion.services.persistence;

import com.trust.gestion.enums.ActionTitle;
import com.trust.gestion.services.domain.ApartmentDto;
import com.trust.gestion.services.entities.ApartmentEntity;
import com.trust.gestion.services.mappers.ApartmentMapper;
import com.trust.gestion.services.mappers.ApartmentMapperImpl;
import com.trust.gestion.services.repositories.ApartmentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ApartmentPersistence {
    private final ApartmentRepository repository;
    private final ActionPersistence actionPersistence;

    public ApartmentDto save(ApartmentEntity entity) {
        ApartmentMapper mapper = new ApartmentMapperImpl();
        return mapper.toDto(this.saveInBd(ActionTitle.APARTMENT_CREATE, entity));
    }
    private ApartmentEntity saveInBd(ActionTitle actionTitle,ApartmentEntity entity){
        ApartmentEntity saved = this.repository.save(entity);
        actionPersistence.createAction(actionTitle);
        return saved;
    }
}
