package com.trust.gestion.domain;

import com.trust.gestion.entities.BuildingEntity;
import com.trust.gestion.enums.BuildingStatus;
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
 * DTO for {@link BuildingEntity}
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class BuildingDto implements Serializable {
    @Size(max = 50)
    private String id;
    @Size(max = 250)
    private String designation;
    @Size(max = 250)
    private String description;
    @NotNull
    @Size(max = 250)
    private String category;
    private BuildingStatus status;
    @NotNull
    private Boolean assigned;
    @NotNull
    private Integer constructionYear;
    @NotNull
    private Integer numberOfFloors;
    @NotNull
    private Integer numberOfUnits;
    @NotNull
    private Instant registrationDate;
    @NotNull
    private Instant lastUpdated;
    private List<ApartmentDto> apartments;
    private List<OwnerDto> owners;
}
