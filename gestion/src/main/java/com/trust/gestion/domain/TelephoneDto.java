package com.trust.gestion.domain;

import com.trust.gestion.entities.TelephoneEntity;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.Instant;

/**
 * DTO for {@link TelephoneEntity}
 */
@Getter
@Setter
@Builder(toBuilder = true)
@AllArgsConstructor
public class TelephoneDto implements Serializable {
    private Integer id;
    @NotNull
    @Size(max = 255)
    private String entityId;
    @NotNull
    @Size(max = 255)
    private String areaCode;
    @NotNull
    @Size(max = 255)
    private String number;
    @NotNull
    @Size(max = 255)
    private String carrier;
    @NotNull
    @Size(max = 255)
    private String country;
    @NotNull
    private Instant registrationDate;
    @NotNull
    private Instant lastUpdated;
}
