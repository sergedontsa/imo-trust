package com.trust.gestion.controllers;

import com.trust.gestion.services.pages.PageResponse;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

public interface Contract <T>{
    ResponseEntity<PageResponse<T>> getById(@PathVariable String id);
    ResponseEntity<PageResponse<T>> getAll(@RequestParam(required = false, defaultValue = "0") Integer page,
                                           @RequestParam(required = false, defaultValue = "10") Integer size);
    ResponseEntity<Void> create();
    ResponseEntity<Void> update(@NotNull String id);

    ResponseEntity<Void> delete(@NotNull String id);
}
