package com.trust.gestion.controllers;


import com.trust.gestion.services.ApartmentServices;
import com.trust.gestion.services.StatusChangeService;
import com.trust.gestion.services.domain.ApartmentDto;
import com.trust.gestion.services.pages.PageResponse;
import com.trust.gestion.services.resources.ApartmentResource;
import com.trust.gestion.services.resources.StatusChangeRequestResource;
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

@RestController
@RequestMapping("/api/v1/apartments")
@AllArgsConstructor
public class ApartmentController implements Contract<ApartmentDto, ApartmentResource> {
    private final StatusChangeService statusChangeService;
    private final ApartmentServices services;
    /**
     * @return
     */
    @Override
    @GetMapping( value = "/{id}", produces = "application/json")
    public ResponseEntity<PageResponse<ApartmentDto>> getById(@PathVariable String id) {
        return ResponseEntity.ok().body(this.services.getById(id));
    }

    /**
     * @return
     */
    @Override
    @GetMapping( value = "/page", produces = "application/json")
    public ResponseEntity<PageResponse<ApartmentDto>> getAll(@RequestParam(required = false, defaultValue = "0") Integer page,
                                                             @RequestParam(required = false, defaultValue = "10") Integer size) {
        return ResponseEntity.ok().body(services.getAll(page, size));
    }

    /**
     * @return
     */
    @Override
    @PostMapping( value = "", produces = "application/json", consumes = "application/json")
    public ResponseEntity<Void> create(ApartmentResource resource) {
        this.services.create(resource);
        return ResponseEntity.ok().build();
    }

    /**
     * @return
     */
    @Override
    @PatchMapping(value = "/{id}", produces = "application/json", consumes = "application/json")
    public ResponseEntity<Void> update(@PathVariable String id, ApartmentResource resource) {
        return null;
    }

    /**
     * @return
     */
    @Override
    @DeleteMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        return null;
    }

    @PatchMapping(value = "/status", produces = "application/json", consumes = "application/json")
    public ResponseEntity<Void> updateStatus(@Valid @RequestBody StatusChangeRequestResource resource) {
        this.statusChangeService.updateStatus(resource);
        return ResponseEntity.ok().build();
    }
}
