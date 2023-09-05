package com.trust.gestion.services.domain;

import com.trust.gestion.services.entities.OwnerBuildingLinkEntity;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.Instant;

/**
 * DTO for {@link OwnerBuildingLinkEntity}
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class OwnerBuildingLinkDto implements Serializable {
    Integer id;
    @NotNull
    BuildingDto building;
    @NotNull
    OwnerDto owner;
    @NotNull
    Instant registrationDate;
    @NotNull
    Instant lastUpdated;
}
