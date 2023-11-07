/*
 * Copyright (c) 2023.  - Serge G. Dontsa
 */

package com.trust.gestion.domain;


import com.trust.gestion.entities.OwnerEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

/**
 * DTO for {@link OwnerEntity}
 */

@Getter
@Setter
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class OwnerDto implements Serializable {

    private String id;
    private Instant registrationDate;
    private Instant lastUpdated;
    private List<OwnerAddressDto> address = new ArrayList<>();
    private List<OwnerContactInformationDto> contacts = new ArrayList<>();
    private List<OwnerInformationDto> information = new ArrayList<>();
    private List<OwnerIdentificationDto> identifications = new ArrayList<>();
}
