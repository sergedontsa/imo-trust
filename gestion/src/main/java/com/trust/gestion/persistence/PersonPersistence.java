package com.trust.gestion.persistence;

import com.trust.gestion.entities.PersonEntity;
import com.trust.gestion.exception.NoSuchElementFoundException;
import com.trust.gestion.repositories.PersonRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class PersonPersistence {
    private final PersonRepository repository;

    public void save(PersonEntity entity){
        this.repository.save(entity);
    }

    public PersonEntity findById(String id){
        return this.repository.findById(id)
                .orElseThrow(() -> new NoSuchElementFoundException("Person not found"));
    }
}
