package com.trust.gestion.persistence;

import com.trust.gestion.entities.AddressEntity;
import com.trust.gestion.mappers.AddressMapper;
import com.trust.gestion.mappers.AddressMapperImpl;
import com.trust.gestion.repositories.AddressRepository;
import com.trust.gestion.resources.reponse.AddressResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
@RequiredArgsConstructor
public class AddressPersistence {
    private final AddressRepository repository;
    public void save(AddressEntity entity) {
        this.repository.save(entity);
    }
    public void saveAll(List<AddressEntity> entities){
        this.repository.saveAll(entities);
    }
    public List<AddressResponse> findByEntity(String entityId){
        AddressMapper mapper = new AddressMapperImpl();
        return this.repository.findByEntityId(entityId)
                .stream()
                .map(mapper::toResponse)
                .toList();
    }
}
