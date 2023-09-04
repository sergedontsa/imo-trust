package com.trust.gestion.services.persistence;

import com.trust.gestion.enums.ActionTitle;
import com.trust.gestion.services.entities.ActionEntity;
import com.trust.gestion.services.repositories.ActionRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.Instant;

@AllArgsConstructor
@Component
@Slf4j
public class ActionPersistence {
    private final ActionRepository repository;

    public void createAction( ActionTitle title) {
        log.info(title.getValue());
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
