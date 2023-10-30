package com.trust.gestion.controllers;

import com.trust.gestion.pages.PageResponse;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

public interface Contract <T, K>{
    ResponseEntity<PageResponse<T>> getById(@PathVariable String id);
    ResponseEntity<PageResponse<T>> getAll(@RequestParam(required = false, defaultValue = "0") Integer page,
                                           @RequestParam(required = false, defaultValue = "10") Integer size);
    ResponseEntity<Void> create(K k);
    ResponseEntity<Void> update(@NotNull String id, @NotNull K k);

    ResponseEntity<Void> delete(@NotNull String id);
}
