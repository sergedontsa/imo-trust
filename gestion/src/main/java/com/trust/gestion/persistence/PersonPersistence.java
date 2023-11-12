package com.trust.gestion.persistence;

import com.trust.gestion.domain.PersonDto;
import com.trust.gestion.entities.PersonEntity;
import com.trust.gestion.exception.NoSuchElementFoundException;
import com.trust.gestion.mappers.PersonMapper;
import com.trust.gestion.mappers.PersonMapperImpl;
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

    public PersonDto findById(String id){
        PersonMapper mapper = new PersonMapperImpl();
        return mapper.toDto(this.repository.findById(id)
                .orElseThrow(() -> new NoSuchElementFoundException("Person not found")));

    }
}
