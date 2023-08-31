/*
 * Copyright (c) 2023.  - Serge G. Dontsa
 */

package com.trust.gestion.services.entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "building")
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class BuildingEntity {
    @Id
    @Size(max = 50)
    @Column(name = "id", nullable = false, length = 50)
    private String id;

    @Size(max = 250)
    @Column(name = "designation", length = 250)
    private String designation;

    @Size(max = 250)
    @Column(name = "description", length = 250)
    private String description;

    @Size(max = 250)
    @NotNull
    @Column(name = "category", nullable = false, length = 250)
    private String category;

    @NotNull
    @Column(name = "construction_year", nullable = false)
    private Integer constructionYear;

    @NotNull
    @Column(name = "number_of_floors", nullable = false)
    private Integer numberOfFloors;

    @NotNull
    @Column(name = "registration_date", nullable = false)
    private Instant registrationDate;

    @NotNull
    @Column(name = "last_updated", nullable = false)
    private Instant lastUpdated;

    @OneToMany(mappedBy = "buildingEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ApartmentEntity> apartments = new ArrayList<>();

}
