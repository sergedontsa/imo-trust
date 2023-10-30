/*
 * Copyright (c) 2023.  - Serge G. Dontsa
 */

package com.trust.gestion.entities;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "owner_identification")
public class OwnerIdentificationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "owner_id", nullable = false)
    private OwnerEntity owner;

    @Size(max = 50)
    @NotNull
    @Column(name = "type_id", nullable = false, length = 50)
    private String typeId;

    @Size(max = 50)
    @NotNull
    @Column(name = "issue_country", nullable = false, length = 50)
    private String issueCountry;

    @NotNull
    @Column(name = "valid_from", nullable = false)
    private LocalDate validFrom;

    @NotNull
    @Column(name = "valid_to", nullable = false)
    private LocalDate validTo;

    @Size(max = 250)
    @Column(name = "description", length = 250)
    private String description;

    @NotNull
    @Column(name = "registration_date", nullable = false)
    private Instant registrationDate;

    @NotNull
    @Column(name = "last_updated", nullable = false)
    private Instant lastUpdated;

}
