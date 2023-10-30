/*
 * Copyright (c) 2023.  - Serge G. Dontsa
 */

package com.trust.gestion.pages;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class PageResponse <T>{
    private List<T> content;
    private int totalPages;
    private long totalElements;
    private int size;
    private int number;
}
