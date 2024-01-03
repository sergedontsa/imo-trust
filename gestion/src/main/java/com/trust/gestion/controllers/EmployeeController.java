package com.trust.gestion.controllers;

import com.trust.gestion.pages.PageResponse;
import com.trust.gestion.resources.AddressResource;
import com.trust.gestion.resources.EmployeeResource;
import com.trust.gestion.resources.IdentificationResource;
import com.trust.gestion.resources.TelephoneResource;
import com.trust.gestion.resources.reponse.EmployeeResponse;
import com.trust.gestion.services.EmployeeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
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
@RequestMapping("/api/v1/employee")
@Slf4j
@RequiredArgsConstructor
public class EmployeeController {
    private final EmployeeService service;

    @GetMapping( value = "/{id}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<PageResponse<EmployeeResponse>> getById(@PathVariable String id) {
        return ResponseEntity.ok().body(this.service.getById(id));
    }


    @GetMapping( value = "/page", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<PageResponse<EmployeeResponse>> getAll(@RequestParam(required = false, defaultValue = "0") Integer page,
                                                              @RequestParam(required = false, defaultValue = "10") Integer size) {
        return ResponseEntity.ok().body(service.getAll(page, size));
    }


    @PostMapping( value = "", produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> create(@Valid @RequestBody EmployeeResource resource) {
        service.create(resource);
        return ResponseEntity.ok().build();
    }

    @PostMapping(value = "/address/{employeeId}", produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> createAddress(@PathVariable String employeeId, @RequestBody @Valid List<AddressResource> resources) {
        service.createAddress(employeeId, resources);
        return ResponseEntity.ok().build();
    }
    @PostMapping(value = "/telephone/{employeeId}", produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> addTelephone(@RequestBody List<TelephoneResource> resources, @PathVariable String employeeId){
        service.addTelephone(resources, employeeId);
        return ResponseEntity.ok().build();
    }
    @PostMapping(value = "/identification/{employeeId}", produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> addIdentification(@RequestBody List<IdentificationResource> resources, @PathVariable String employeeId){
        service.addIdentification(resources, employeeId);
        return ResponseEntity.ok().build();
    }


}
