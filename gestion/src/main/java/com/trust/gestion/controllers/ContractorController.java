package com.trust.gestion.controllers;


import com.trust.gestion.services.domain.ContractorDto;
import com.trust.gestion.services.pages.PageResponse;
import com.trust.gestion.services.resources.ContractorResource;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/contracts")
@AllArgsConstructor
public class ContractorController implements Contract<ContractorDto, ContractorResource> {
    /**
     * @return
     */
    @Override
    @GetMapping( value = "/{id}", produces = "application/json")
    public ResponseEntity<PageResponse<ContractorDto>> getById(@PathVariable String id) {
        return null;
    }

    /**
     * @return
     */
    @Override
    @GetMapping( value = "/page", produces = "application/json")
    public ResponseEntity<PageResponse<ContractorDto>> getAll(@RequestParam(required = false, defaultValue = "0") Integer page,
                                                              @RequestParam(required = false, defaultValue = "10") Integer size) {
        return null;
    }

    /**
     * @return
     */
    @Override
    @PostMapping( value = "", produces = "application/json", consumes = "application/json")
    public ResponseEntity<Void> create(ContractorResource resource) {
        return null;
    }

    /**
     * @return
     */
    @Override
    @PatchMapping(value = "/{id}", produces = "application/json", consumes = "application/json")
    public ResponseEntity<Void> update(@PathVariable String id, ContractorResource resource) {
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
}
