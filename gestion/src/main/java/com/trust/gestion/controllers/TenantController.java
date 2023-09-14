package com.trust.gestion.controllers;

import com.trust.gestion.services.StatusChangeService;
import com.trust.gestion.services.TenantService;
import com.trust.gestion.services.domain.TenantDto;
import com.trust.gestion.services.pages.PageResponse;
import com.trust.gestion.services.resources.BillPayResource;
import com.trust.gestion.services.resources.StatusChangeRequestResource;
import com.trust.gestion.services.resources.TenantResource;
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

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/api/v1/tenants")
@AllArgsConstructor
public class TenantController implements Contract<TenantDto, TenantResource> {
    private final TenantService service;
    private final StatusChangeService statusChangeService;
    /**
     * @return
     */
    @Override
    @GetMapping( value = "/{id}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<PageResponse<TenantDto>> getById(@PathVariable String id) {
        return ResponseEntity.ok().body(service.getById(id));
    }

    /**
     * @return
     */
    @Override
    @GetMapping( value = "/page", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<PageResponse<TenantDto>> getAll(@RequestParam(required = false, defaultValue = "0") Integer page,
                                                          @RequestParam(required = false, defaultValue = "10") Integer size) {
        return null;
    }

    /**
     * @return
     */
    @Override
    @PostMapping( value = "", produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> create(@RequestBody TenantResource resource) {
        service.create(resource);
        return ResponseEntity.ok().build();
    }

    /**
     * @return
     */
    @Override
    @PatchMapping(value = "/{id}", produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> update(@NotNull @PathVariable String id, @Valid  @RequestBody TenantResource resource) {
        service.update(resource, id);
        return ResponseEntity.ok().build();
    }

    /**
     * @return Void
     */
    @Override
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
}
