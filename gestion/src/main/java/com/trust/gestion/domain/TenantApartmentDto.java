package com.trust.gestion.domain;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.Instant;

/**
 * DTO for {@link com.trust.gestion.entities.TenantApartmentEntity}
 */
@Getter
@Setter
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class TenantApartmentDto implements Serializable {
    private Integer id;
    @NotNull
    private TenantDto tenant;
    @NotNull
    private ApartmentDto apartment;
    @Size(max = 255)
    private String description;
    @NotNull
    private Instant registrationDate;
    @NotNull
    private Instant lastUpdated;
}
