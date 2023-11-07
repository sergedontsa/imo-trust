package com.trust.gestion.controllers;


import com.trust.gestion.pages.PageResponse;
import com.trust.gestion.resources.ApartmentResource;
import com.trust.gestion.resources.StatusChangeRequestResource;
import com.trust.gestion.resources.reponse.ApartmentResponse;
import com.trust.gestion.services.ApartmentServices;
import com.trust.gestion.services.StatusChangeService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
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

import java.util.List;

@RestController
@RequestMapping("/api/v1/apartments")
@AllArgsConstructor
public class ApartmentController {
    private final StatusChangeService statusChangeService;
    private final ApartmentServices service;
    /**
     * @return
     */

    @GetMapping( value = "/{id}", produces = "application/json")
    public ResponseEntity<PageResponse<ApartmentResponse>> getById(@PathVariable String id) {
        return ResponseEntity.ok().body(this.service.getById(id));
    }

    /**
     * @return
     */

    @GetMapping( value = "/page", produces = "application/json")
    public ResponseEntity<PageResponse<ApartmentResponse>> getAll(@RequestParam(required = false, defaultValue = "0") Integer page,
                                                             @RequestParam(required = false, defaultValue = "10") Integer size) {
        return ResponseEntity.ok().body(service.getAll(page, size));
    }

    /**
     * @return
     */

    @PostMapping( value = "/{buildingId}", produces = "application/json", consumes = "application/json")
    public ResponseEntity<Void> create(@RequestBody @Valid List<ApartmentResource> resources, @PathVariable String buildingId) {
        this.service.create(resources, buildingId);
        return ResponseEntity.ok().build();
    }


    /**
     * @return
     */

    @PatchMapping(value = "/{id}", produces = "application/json", consumes = "application/json")
    public ResponseEntity<Void> update(@PathVariable String id, @RequestBody @Valid ApartmentResource resource) {
        this.service.update(id, resource);
        return ResponseEntity.ok().build();
    }

    /**
     * @return
     */

    @DeleteMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        this.service.delete(id);
        return ResponseEntity.ok().build();
    }

    @PatchMapping(value = "/status", produces = "application/json", consumes = "application/json")
    public ResponseEntity<Void> updateStatus(@Valid @RequestBody StatusChangeRequestResource resource) {
        this.statusChangeService.updateStatus(resource);
        return ResponseEntity.ok().build();
    }
}
