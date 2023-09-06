package com.trust.gestion.controllers;

import com.trust.gestion.services.TenantService;
import com.trust.gestion.services.domain.TenantDto;
import com.trust.gestion.services.pages.PageResponse;
import com.trust.gestion.services.resources.TenantResource;
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

@RestController
@RequestMapping("/api/v1/tenants")
@AllArgsConstructor
public class TenantController implements Contract<TenantDto, TenantResource> {
    private final TenantService service;
    /**
     * @return
     */
    @Override
    @GetMapping( value = "/{id}", produces = "application/json")
    public ResponseEntity<PageResponse<TenantDto>> getById(@PathVariable String id) {
        return ResponseEntity.ok().body(service.getById(id));
    }

    /**
     * @return
     */
    @Override
    @GetMapping( value = "/page", produces = "application/json")
    public ResponseEntity<PageResponse<TenantDto>> getAll(@RequestParam(required = false, defaultValue = "0") Integer page,
                                                          @RequestParam(required = false, defaultValue = "10") Integer size) {
        return null;
    }

    /**
     * @return
     */
    @Override
    @PostMapping( value = "", produces = "application/json", consumes = "application/json")
    public ResponseEntity<Void> create(@RequestBody TenantResource resource) {
        service.create(resource);
        return ResponseEntity.ok().build();
    }

    /**
     * @return
     */
    @Override
    @PatchMapping(value = "/{id}", produces = "application/json", consumes = "application/json")
    public ResponseEntity<Void> update(@PathVariable String id, TenantResource resource) {
        service.update(resource, id);
        return ResponseEntity.ok().build();
    }

    /**
     * @return Void
     */
    @Override
    @DeleteMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        return null;
    }
}
