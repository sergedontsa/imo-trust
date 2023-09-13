package com.trust.gestion.services;

import com.trust.gestion.exception.NoSuchElementFoundException;
import com.trust.gestion.services.domain.ApartmentDto;
import com.trust.gestion.services.entities.ApartmentEntity;
import com.trust.gestion.services.handlers.Handler;
import com.trust.gestion.services.mappers.ApartmentMapper;
import com.trust.gestion.services.mappers.ApartmentMapperImpl;
import com.trust.gestion.services.pages.PageResponse;
import com.trust.gestion.services.persistence.ApartmentPersistence;
import com.trust.gestion.services.repositories.ApartmentRepository;
import com.trust.gestion.services.resources.ApartmentResource;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.Optional.empty;

@Service
@RequiredArgsConstructor
@Transactional
public class ApartmentServices {
    private final ApartmentRepository repository;
    private final ApartmentPersistence persistence;
    public PageResponse<ApartmentDto> getAll(Integer page, Integer size){
        ApartmentMapper mapper = new ApartmentMapperImpl();
        Page<ApartmentEntity> entities = this.repository.findAll(PageRequest.of(page, size));
        return (new PageResponse<ApartmentDto>()).toBuilder()
                .content(entities.getContent().stream().map(mapper::toDto).toList())
                .totalPages(entities.getTotalPages())
                .totalElements(entities.getTotalElements())
                .size(entities.getSize())
                .number(entities.getNumber())
                .build();
    }

    public PageResponse<ApartmentDto> getById(String id) {
        ApartmentMapper mapper = new ApartmentMapperImpl();
        return (new PageResponse<ApartmentDto>())
                .toBuilder()
                .content(List.of(mapper.toDto(this.findById(id))))
                .build();

    }
    private ApartmentEntity findById(String id) {
        return this.repository.findById(id).orElseThrow(() -> new NoSuchElementFoundException("No such element found"));
    }

    public void create(ApartmentResource resource) {
        Handler handler = new Handler();
        ApartmentDto dto = handler.apartmentHandle(resource, empty());
        this.persistence.save(dto);
    }
}
