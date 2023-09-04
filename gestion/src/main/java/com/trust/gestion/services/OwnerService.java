package com.trust.gestion.services;


import com.trust.gestion.exception.OwnerNotFoundException;
import com.trust.gestion.services.domain.OwnerDto;
import com.trust.gestion.services.entities.OwnerEntity;
import com.trust.gestion.services.handlers.OwnerHandler;
import com.trust.gestion.services.mappers.OwnerMapper;
import com.trust.gestion.services.mappers.OwnerMapperImpl;
import com.trust.gestion.services.pages.PageResponse;
import com.trust.gestion.services.persistence.OwnerPersistence;
import com.trust.gestion.services.repositories.OwnerRepository;
import com.trust.gestion.services.resources.OwnerResource;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.Optional.empty;
import static java.util.Optional.of;

@Service
@RequiredArgsConstructor
@Transactional
public class OwnerService {
    private final OwnerRepository repository;
    private final OwnerPersistence persistence;

    public PageResponse<OwnerDto> getById(String id) {
        OwnerMapper mapper = new OwnerMapperImpl();
        PageResponse<OwnerDto> pageResponse = new PageResponse<>();
        return pageResponse.toBuilder().content(List.of(mapper.toDto(this.findById(id)))).build();

    }
    public PageResponse<OwnerDto> getAll(Integer page, Integer size){
        OwnerMapper mapper = new OwnerMapperImpl();
        Page<OwnerEntity> entities = this.repository.findAll(PageRequest.of(page, size));
        PageResponse<OwnerDto> contents = new PageResponse<>();
        return contents.toBuilder()
                .content(entities.getContent().stream().map(mapper::toDto).toList())
                .totalPages(entities.getTotalPages())
                .totalElements(entities.getTotalElements())
                .size(entities.getSize())
                .number(entities.getNumber())
                .build();

    }
    public void create(OwnerResource resource) {
        OwnerHandler handler = new OwnerHandler();
        OwnerDto dto = handler.handle(resource, empty());
        this.persistence.saved(dto);
    }

    public PageResponse<OwnerDto> update(String id, OwnerResource resource) {
        OwnerEntity entity = findById(id);
        OwnerHandler handler = new OwnerHandler();
        OwnerDto dto = handler.handle(resource, of(entity));
        this.persistence.saved(dto);

        return null;
    }

    public void delete(String id) {
        // TODO will work on this
    }
    private OwnerEntity findById(String id) {
        return this.repository.findById(id).orElseThrow(() -> new OwnerNotFoundException("Owner not found"));
    }

}
