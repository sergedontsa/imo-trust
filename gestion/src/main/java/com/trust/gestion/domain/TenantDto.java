/*
 * Copyright (c) 2023.  - Serge G. Dontsa
 */

package com.trust.gestion.domain;


import com.trust.gestion.entities.TenantEntity;
import com.trust.gestion.enums.Gender;
import com.trust.gestion.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDate;
import java.util.List;

/**
 * DTO for {@link TenantEntity}
 */
@Getter
@Setter
@Builder(toBuilder = true)
@AllArgsConstructor
public class TenantDto implements Serializable {

    private String id;
    private String firstName;
    private String middleName;
    private String lastName;
    private Gender gender;
    private Status status;
    private LocalDate dateOfBirth;
    private String countryOfOrigin;
    private String cityOfOrigin;
    private String description;
    private String contactType;
    private Instant registrationDate;
    private Instant lastUpdated;
    private List<TelephoneDto> telephones;
    private List<ApartmentDto> apartments;
}
