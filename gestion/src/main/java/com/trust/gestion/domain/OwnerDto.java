/*
 * Copyright (c) 2023.  - Serge G. Dontsa
 */

package com.trust.gestion.domain;


import com.trust.gestion.entities.OwnerEntity;
import com.trust.gestion.enums.Gender;
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
    private String firstName;
    private String lastName;
    private String middleName;
    private Gender gender;
    private List<AddressDto> address = new ArrayList<>();
    private List<TelephoneDto> telephones = new ArrayList<>();
}
