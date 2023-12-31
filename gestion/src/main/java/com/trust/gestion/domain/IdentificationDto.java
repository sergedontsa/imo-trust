package com.trust.gestion.domain;

import com.trust.gestion.entities.IdentificationEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDate;

/**
 * DTO for {@link IdentificationEntity}
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class IdentificationDto implements Serializable {
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
