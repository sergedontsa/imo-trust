package com.trust.gestion.persistence;

import com.trust.gestion.domain.OwnerAddressDto;
import com.trust.gestion.mappers.OwnerAddressMapper;
import com.trust.gestion.repositories.OwnerAddressRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
@Slf4j
public class OwnerAddressPersistence {
    private final OwnerAddressRepository repository;
    private final OwnerAddressMapper mapper;

    public OwnerAddressDto saved(OwnerAddressDto dto) {
        return this.mapper.toDto(this.repository.save(this.mapper.toEntity(dto)));
    }


}
