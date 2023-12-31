package com.trust.gestion.controllers;

import com.trust.gestion.pages.PageResponse;
import com.trust.gestion.resources.AddressResource;
import com.trust.gestion.resources.IdentificationResource;
import com.trust.gestion.resources.OwnerResource;
import com.trust.gestion.resources.TelephoneResource;
import com.trust.gestion.resources.reponse.OwnerResponse;
import com.trust.gestion.services.OwnerService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/api/v1/owners")
@AllArgsConstructor
@Slf4j
public class OwnerController {
    private final OwnerService service;

    @GetMapping( value = "/{id}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<PageResponse<OwnerResponse>> getById(@PathVariable String id) {
        return ResponseEntity.ok().body(this.service.getById(id));
    }


    @GetMapping( value = "/page", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<PageResponse<OwnerResponse>> getAll(@RequestParam(required = false, defaultValue = "0") Integer page,
                                                         @RequestParam(required = false, defaultValue = "10") Integer size) {
        return ResponseEntity.ok().body(service.getAll(page, size));
    }


    @PostMapping( value = "", produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> create(@Valid @RequestBody OwnerResource resource) {
        service.create(resource);
        return ResponseEntity.ok().build();
    }

    @PostMapping(value = "/address/{id}", produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> createAddress(@PathVariable String id, @RequestBody @Valid List<AddressResource> resources) {
        service.createAddress(id, resources);
        return ResponseEntity.ok().build();
    }
    @PostMapping(value = "/telephone/{ownerId}", produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> addTelephone(@RequestBody List<TelephoneResource> resources, @PathVariable String ownerId){
        service.addTelephone(resources, ownerId);
        return ResponseEntity.ok().build();
    }
    @PostMapping(value = "/identification/{ownerId}", produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> addIdentification(@RequestBody List<IdentificationResource> resources, @PathVariable String ownerId){
        service.addIdentification(resources, ownerId);
        return ResponseEntity.ok().build();
    }



}
