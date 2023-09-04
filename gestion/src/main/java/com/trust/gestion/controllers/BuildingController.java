package com.trust.gestion.controllers;

import com.trust.gestion.services.BuildingService;
import com.trust.gestion.services.domain.BuildingDto;
import com.trust.gestion.services.pages.PageResponse;
import com.trust.gestion.services.resources.BuildingResource;
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
@RequestMapping("/api/v1/buildings")
@AllArgsConstructor
public class BuildingController implements Contract<BuildingDto, BuildingResource> {
    private final BuildingService service;
    /**
     * @return
     */
    @Override
    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<PageResponse<BuildingDto>> getById(@PathVariable String id) {
        return ResponseEntity.ok().body(service.getById(id));
    }

    /**
     * @return
     */
    @Override
    @GetMapping( value = "/page", produces = "application/json")
    public ResponseEntity<PageResponse<BuildingDto>> getAll(@RequestParam(required = false, defaultValue = "0") Integer page,
                                                            @RequestParam(required = false, defaultValue = "10") Integer size) {
        return null;
    }

    /**
     * @return
     */
    @Override
    @PostMapping( value = "", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> create(@RequestBody BuildingResource resource) {
        this.service.create(resource);
        return ResponseEntity.ok().build();
    }

    /**
     * @return
     */
    @Override
    @PatchMapping(value = "/{id}", produces = "application/json", consumes = "application/json")
    public ResponseEntity<Void> update(@PathVariable String id, BuildingResource resource) {
        return null;
    }

    /**
     * @return
     */
    @Override
    @DeleteMapping(value = "/owners/{id}", produces = "application/json")
    public ResponseEntity<Void> delete(@PathVariable("id") String id) {
        return null;
    }
}
