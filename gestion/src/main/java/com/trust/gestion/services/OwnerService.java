package com.trust.gestion.services;



import com.trust.gestion.exception.OwnerNotFoundException;
import com.trust.gestion.services.domain.OwnerDto;
import com.trust.gestion.services.entities.OwnerEntity;
import com.trust.gestion.services.handlers.OwnerHandler;
import com.trust.gestion.services.mappers.OwnerMapper;
import com.trust.gestion.services.pages.OwnerPageResponse;
import com.trust.gestion.services.persistence.OwnerPersistence;
import com.trust.gestion.services.repositories.OwnerAddressRepository;
import com.trust.gestion.services.repositories.OwnerRepository;
import com.trust.gestion.services.resources.OwnerResource;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.Optional.empty;

@Service
@RequiredArgsConstructor
@Transactional
public class OwnerService {
    private final OwnerRepository repository;
    private final OwnerAddressRepository addressRepository;
    private final OwnerMapper mapper;
    private final OwnerPersistence persistence;

    public OwnerPageResponse getById(String id) {
        OwnerEntity entity = findById(id);
        return this.mapper.toPageResponse(entity);
    }
    public OwnerPageResponse getAll(Integer page, Integer size){
        Page<OwnerEntity> entities = this.repository.findAll(PageRequest.of(page, size));
        return OwnerPageResponse.builder()
                .content(entities.getContent().stream().map(this.mapper::toDto).toList())
                .totalPages(entities.getTotalPages())
                .totalElements((int) entities.getTotalElements())
                .size(entities.getSize())
                .number(entities.getNumber())
                .build();
    }
    public OwnerPageResponse create(OwnerResource resource) {
        OwnerHandler handler = new OwnerHandler();
        OwnerDto dto = handler.handle(resource, empty());

        OwnerDto saved = this.persistence.saved(dto);
        //get address
        //get contact
        //get information
        //get identification

        return OwnerPageResponse.builder()
                .content(List.of(saved))
                .totalPages(1)
                .number(1)
                .size(1)
                .totalElements(1)
                .build();
    }

    public OwnerPageResponse update(String id, OwnerResource resource) {
        OwnerEntity entity = findById(id);
        OwnerHandler handler = new OwnerHandler();
        OwnerDto dto = handler.handle(resource, java.util.Optional.of(entity));
        OwnerEntity updated = this.repository.save(this.mapper.toEntity(dto));

        return OwnerPageResponse.builder()
                .content(List.of(this.mapper.toDto(updated)))
                .totalPages(1)
                .number(1)
                .size(1)
                .totalElements(1)
                .build();
    }

    public void delete(String id) {
        // TODO will work on this
    }
    private OwnerEntity findById(String id) {
        return this.repository.findById(id).orElseThrow(() -> new OwnerNotFoundException("Owner not found"));
    }

}
