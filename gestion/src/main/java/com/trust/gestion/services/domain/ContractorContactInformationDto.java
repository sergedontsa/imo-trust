/*
 * Copyright (c) 2023.  - Serge G. Dontsa
 */

package com.trust.gestion.services.domain;



import com.trust.gestion.services.entities.ContractorContactInformation;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.antlr.v4.runtime.misc.NotNull;

import java.io.Serializable;
import java.time.Instant;

/**
 * DTO for {@link ContractorContactInformation}
 */
@Getter
@Setter
@Builder(toBuilder = true)
@AllArgsConstructor
public class ContractorContactInformationDto implements Serializable {
    Integer id;
    @NotNull
    ContractorDto contractor;
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
