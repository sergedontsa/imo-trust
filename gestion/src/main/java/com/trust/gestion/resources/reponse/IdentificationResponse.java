package com.trust.gestion.resources.reponse;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class IdentificationResponse {
    private Integer id;
    private String entityId;
    private String typeId;
    private String issueCountry;
    private LocalDate validFrom;
    private LocalDate validTo;
    private String description;
    private Instant registrationDate;
    private Instant lastUpdated;
}
