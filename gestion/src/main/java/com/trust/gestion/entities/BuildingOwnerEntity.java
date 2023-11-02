package com.trust.gestion.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "building_owner")
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder(toBuilder = true)
public class BuildingOwnerEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @NotNull
    @Column(name = "registration_date", nullable = false)
    private Instant registrationDate;

    @NotNull
    @Column(name = "last_updated", nullable = false)
    private Instant lastUpdated;

    @OneToOne(optional = false, orphanRemoval = true)
    @JoinColumn(name = "owner_id", nullable = false)
    private OwnerEntity owner;

    @OneToOne(optional = false, orphanRemoval = true)
    @JoinColumn(name = "building_id", nullable = false)
    private BuildingEntity building;

}
