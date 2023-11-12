package com.trust.gestion.controllers;

import com.trust.gestion.pages.PageResponse;
import com.trust.gestion.resources.BuildingResource;
import com.trust.gestion.resources.reponse.BuildingResponse;
import com.trust.gestion.services.BuildingService;
import lombok.RequiredArgsConstructor;
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
@RequestMapping("/api/v1/buildings")
@RequiredArgsConstructor
public class BuildingController {
    private final BuildingService service;
    /**
     * @return dto from bd
     */

    @GetMapping(value = "/{id}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<PageResponse<BuildingResponse>> getById(@PathVariable String id) {
        return ResponseEntity.ok().body(service.getById(id));
    }

    /**
     * @return void
     */

    @GetMapping( value = "/page", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<PageResponse<BuildingResponse>> getAll(@RequestParam(required = false, defaultValue = "0") Integer page,
                                                            @RequestParam(required = false, defaultValue = "10") Integer size) {
        return ResponseEntity.ok().body(this.service.getAll(page, size));
    }

    /**
     * @return void
     */

    @PostMapping( value = "", produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> create(@RequestBody BuildingResource resource) {
        this.service.create(resource);
        return ResponseEntity.ok().build();
    }

    /**
     * @return void
     */

    @PatchMapping(value = "/{id}", produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> update(@PathVariable String id, @RequestBody BuildingResource resource) {
        this.service.update(resource, id);
        return ResponseEntity.ok().build();
    }

    /**
     * @return void
     */

    @DeleteMapping(value = "/owners/{id}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> delete(@PathVariable("id") String id) {
        return null;
    }
}
