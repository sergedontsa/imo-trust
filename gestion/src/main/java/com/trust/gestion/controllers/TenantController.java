package com.trust.gestion.controllers;

import com.trust.gestion.pages.PageResponse;
import com.trust.gestion.resources.BillPayResource;
import com.trust.gestion.resources.IdentificationResource;
import com.trust.gestion.resources.StatusChangeRequestResource;
import com.trust.gestion.resources.TelephoneResource;
import com.trust.gestion.resources.TenantResource;
import com.trust.gestion.resources.reponse.TenantResponse;
import com.trust.gestion.services.StatusChangeService;
import com.trust.gestion.services.TenantService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
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

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/api/v1/tenants")
@AllArgsConstructor
public class TenantController{
    private final TenantService service;
    private final StatusChangeService statusChangeService;

    @GetMapping( value = "/{id}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<PageResponse<TenantResponse>> getById(@PathVariable String id) {
        return ResponseEntity.ok().body(service.getById(id));
    }
    @GetMapping( value = "/page", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<PageResponse<TenantResponse>> getAll(@RequestParam(required = false, defaultValue = "0") Integer page,
                                                               @RequestParam(required = false, defaultValue = "10") Integer size) {
        return ResponseEntity.ok().body(this.service.getAll(page, size));
    }

    @PostMapping( value = "/{apartmentId}", produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> create(@RequestBody List<TenantResource> resources, @PathVariable String apartmentId) {
        service.create(resources, apartmentId);
        return ResponseEntity.ok().build();
    }
    @PostMapping( value = "/add/{apartmentId}", produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> addTenant(@RequestBody List<TenantResource> resources, @PathVariable String apartmentId) {
        service.addTenantToApartment(resources, apartmentId);
        return ResponseEntity.ok().build();
    }

    @PatchMapping(value = "/{id}", produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> update(@NotNull @PathVariable String id, @Valid  @RequestBody TenantResource resource) {
        service.update(resource, id);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping(value = "/{id}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> delete(@PathVariable String id) {
        return null;
    }

    @PostMapping(value = "/bill/pay", produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> payBill(@RequestBody BillPayResource resource){
        service.payBill(resource);
        return ResponseEntity.ok().build();
    }
    @PatchMapping(value = "/status", produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> updateStatus(@Valid @RequestBody StatusChangeRequestResource resource) {
        this.statusChangeService.updateStatus(resource);
        return ResponseEntity.ok().build();
    }
    @PostMapping(value = "/telephone/{tenantId}", produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> addTelephone(@RequestBody List<TelephoneResource> resources, @PathVariable String tenantId){
        service.addTelephone(resources, tenantId);
        return ResponseEntity.ok().build();
    }
    @PostMapping(value = "/identification/{tenantId}", produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> addIdentification(@RequestBody List<IdentificationResource> resources, @PathVariable String tenantId){
        service.addIdentification(resources, tenantId);
        return ResponseEntity.ok().build();
    }
}
