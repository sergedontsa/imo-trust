package com.trust.gestion.resources;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class IdentificationResource {
    private String entityId;
    private String typeId;
    private String issueCountry;
    private LocalDate validFrom;
    private LocalDate validTo;
    private String description;
}
