package com.trust.gestion.services.domain;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.Instant;
import java.util.List;

/**
 * DTO for {@link com.trust.gestion.services.entities.BuildingEntity}
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class BuildingDto implements Serializable {
    @Size(max = 50)
    String id;
    @Size(max = 250)
    String designation;
    @Size(max = 250)
    String description;
    @NotNull
    @Size(max = 250)
    String category;
    @NotNull
    Boolean assigned;
    @NotNull
    Integer constructionYear;
    @NotNull
    Integer numberOfFloors;
    @NotNull
    Instant registrationDate;
    @NotNull
    Instant lastUpdated;
    private List<ApartmentDto> apartments;
}
