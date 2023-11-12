package com.trust.gestion.domain;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.Instant;

/**
 * DTO for {@link com.trust.gestion.entities.BuildingOwnerEntity}
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class BuildingOwnerDto implements Serializable {
    private Integer id;
    @NotNull
    private Instant registrationDate;
    @NotNull
    private Instant lastUpdated;
    private OwnerDto owner;
    private BuildingDto building;
}
