package com.trust.gestion.controllers;

import com.trust.gestion.services.OwnerService;
import com.trust.gestion.domain.OwnerDto;
import com.trust.gestion.pages.OwnerLinkResponse;
import com.trust.gestion.pages.PageResponse;
import com.trust.gestion.resources.OwnerLinkResource;
import com.trust.gestion.resources.OwnerResource;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/api/v1/owners")
@AllArgsConstructor
@Slf4j
public class OwnerController implements Contract<OwnerDto, OwnerResource> {
    private final OwnerService service;
    @Override
    @GetMapping( value = "/{id}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<PageResponse<OwnerDto>> getById(@PathVariable String id) {
        return ResponseEntity.ok().body(this.service.getById(id));
    }

    @Override
    @GetMapping( value = "/page", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<PageResponse<OwnerDto>> getAll(@RequestParam(required = false, defaultValue = "0") Integer page,
                                                         @RequestParam(required = false, defaultValue = "10") Integer size) {
        return ResponseEntity.ok().body(service.getAll(page, size));
    }

    @Override
    @PostMapping( value = "", produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> create(@Valid @RequestBody OwnerResource resource) {
        service.create(resource);
        return ResponseEntity.ok().build();
    }

    @Override
    @PatchMapping(value = "/{id}", produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> update(@PathVariable String id, @RequestBody OwnerResource resource) {
        service.update(id, resource);
        return ResponseEntity.ok().build();
    }

    @Override
    @DeleteMapping(value = "/owners/{id}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Void>  delete(@PathVariable String id) {
        return null;
    }

    @PostMapping(value = "/link", produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<OwnerLinkResponse> linkOwnerToBuilding(@RequestBody OwnerLinkResource resource) {
        return ResponseEntity.ok().body(service.linkOwnerToBuilding(resource));
    }


}
