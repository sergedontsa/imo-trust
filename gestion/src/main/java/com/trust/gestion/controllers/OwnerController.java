package com.trust.gestion.controllers;

import com.trust.gestion.services.OwnerService;
import com.trust.gestion.services.domain.OwnerDto;
import com.trust.gestion.services.pages.PageResponse;
import com.trust.gestion.services.resources.OwnerResource;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/owners")
@AllArgsConstructor
public class OwnerController implements Contract<OwnerDto, OwnerResource> {
    private final OwnerService service;
    @Override
    @GetMapping( value = "/{id}", produces = "application/json")
    public ResponseEntity<PageResponse<OwnerDto>> getById(@PathVariable String id) {
        return ResponseEntity.ok().body(this.service.getById(id));
    }

    @Override
    @GetMapping( value = "/page", produces = "application/json")
    public ResponseEntity<PageResponse<OwnerDto>> getAll(@RequestParam(required = false, defaultValue = "0") Integer page,
                                                         @RequestParam(required = false, defaultValue = "10") Integer size) {
        return ResponseEntity.ok().body(service.getAll(page, size));
    }

    @Override
    @PostMapping( value = "", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> create(@RequestBody OwnerResource resource) {
        service.create(resource);
        return ResponseEntity.ok().build();
    }

    @Override
    @PatchMapping(value = "/{id}", produces = "application/json", consumes = "application/json")
    public ResponseEntity<Void> update(@PathVariable String id, @RequestBody OwnerResource resource) {
        service.update(id, resource);
        return ResponseEntity.ok().build();
    }

    @Override
    @DeleteMapping(value = "/owners/{id}", produces = "application/json")
    public ResponseEntity<Void>  delete(@PathVariable String id) {
        return null;
    }
}
