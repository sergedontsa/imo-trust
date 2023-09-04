package com.trust.gestion.services.handlers;

import com.trust.gestion.services.domain.ApartmentDto;
import com.trust.gestion.services.entities.ApartmentEntity;
import com.trust.gestion.services.mappers.ApartmentMapper;
import com.trust.gestion.services.mappers.ApartmentMapperImpl;
import com.trust.gestion.services.resources.ApartmentResource;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Optional;

@Component
@AllArgsConstructor
public class ApartmentHandler {
    public ApartmentDto handle(ApartmentResource resource, Optional<ApartmentEntity> optionalEntity){
        return optionalEntity.isPresent() ? this.update(resource, optionalEntity.get()) : this.create(resource);
    }

    private ApartmentDto update(ApartmentResource resource, ApartmentEntity entity) {
        return null;
    }

    private ApartmentDto create(ApartmentResource resource) {
        ApartmentMapper mapper = new ApartmentMapperImpl();
        ApartmentDto dto = mapper.fromResourceToDto(resource);
        return dto.toBuilder()
                .registrationDate(Instant.now())
                .lastUpdated(Instant.now())
                .build();

    }
}
