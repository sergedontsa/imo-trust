package com.trust.gestion.persistence;

import com.trust.gestion.domain.AddressDto;
import com.trust.gestion.entities.AddressEntity;
import com.trust.gestion.mappers.AddressMapper;
import com.trust.gestion.mappers.AddressMapperImpl;
import com.trust.gestion.repositories.AddressRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
@RequiredArgsConstructor
public class AddressPersistence {
    private final AddressRepository repository;
    public void save(AddressDto dto) {
        AddressMapper mapper = new AddressMapperImpl();
        this.repository.save(mapper.toEntity(dto));
    }
    public void saveAll(List<AddressDto> dtos){
        AddressMapper mapper = new AddressMapperImpl();
        List<AddressEntity> entities = dtos.stream()
                .map(mapper::toEntity)
                .toList();
        this.repository.saveAll(entities);
    }
    public List<AddressDto> findByEntityId(String entityId){
        AddressMapper mapper = new AddressMapperImpl();
        return this.repository.findByEntityId(entityId)
                .stream()
                .map(mapper::toDto)
                .toList();
    }
}
