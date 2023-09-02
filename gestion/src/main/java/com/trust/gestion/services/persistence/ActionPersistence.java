package com.trust.gestion.services.persistence;

import com.trust.gestion.services.domain.ActionDto;
import com.trust.gestion.services.entities.ActionEntity;
import com.trust.gestion.services.mappers.ActionMapper;
import com.trust.gestion.services.mappers.ActionMapperImpl;
import com.trust.gestion.services.repositories.ActionRepository;
import lombok.AllArgsConstructor;

import java.time.Instant;

@AllArgsConstructor
public class ActionPersistence {
    private final ActionRepository repository;

    public void createAction(ActionDto dto){
        dto.toBuilder()
                .registrationDate(Instant.now())
                .lastUpdated(Instant.now());

        ActionMapper actionMapper = new ActionMapperImpl();
        ActionEntity entity = actionMapper.toEntity(dto);
        this.repository.save(entity);
    }
}
