package com.trust.gestion.services.persistence;

import com.trust.gestion.enums.ActionTitle;
import com.trust.gestion.services.entities.ActionEntity;
import com.trust.gestion.services.repositories.ActionRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.Instant;

@AllArgsConstructor
@Component
public class ActionPersistence {
    private final ActionRepository repository;

    public void createAction( ActionTitle title) {
        this.repository.save(ActionEntity.builder()
                .title(title.getValue())
                .parties("SYSTEM")
                .partiesId("SYSTEM")
                .description("Description")
                .registrationDate(Instant.now())
                .lastUpdated(Instant.now())
                .build());
    }
}
