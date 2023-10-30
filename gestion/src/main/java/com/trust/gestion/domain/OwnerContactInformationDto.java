/*
 * Copyright (c) 2023.  - Serge G. Dontsa
 */

package com.trust.gestion.domain;


import com.trust.gestion.entities.OwnerContactInformationEntity;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.Instant;

/**
 * DTO for {@link OwnerContactInformationEntity}
 */
@Getter
@Setter
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class OwnerContactInformationDto implements Serializable {
    Integer id;
    @NotNull
    @Size(max = 255)
    String email;
    @NotNull
    @Size(max = 20)
    String cellPhoneNumber;
    @Size(max = 10)
    String cellPhoneNumberExtension;
    @Size(max = 20)
    String workPhoneNumber;
    @Size(max = 10)
    String workPhoneNumberExtension;
    @Size(max = 20)
    String homePhoneNumber;
    @Size(max = 10)
    String homePhoneNumberExtension;
    @Size(max = 20)
    String otherPhoneNumber;
    @Size(max = 10)
    String otherPhoneNumberExtension;
    @NotNull
    Instant registrationDate;
    @NotNull
    Instant lastUpdated;
}
