/*
 * Copyright (c) 2023.  - Serge G. Dontsa
 */

package com.trust.gestion.services.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.validation.constraints.NotNull;

import java.time.Instant;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
@Table(name = "contractor")
public class ContractorEntity {
    @Id
    @Size(max = 50)
    @Column(name = "id", nullable = false, length = 50)
    private String id;

    @Size(max = 20)
    @NotNull
    @Column(name = "first_name", nullable = false, length = 20)
    private String firstName;

    @Size(max = 20)
    @Column(name = "middle_name", length = 20)
    private String middleName;

    @Size(max = 20)
    @NotNull
    @Column(name = "last_name", nullable = false, length = 20)
    private String lastName;

    @Size(max = 255)
    @NotNull
    @Column(name = "specialization", nullable = false)
    private String specialization;

    @NotNull
    @Column(name = "registration_date", nullable = false)
    private Instant registrationDate;

    @NotNull
    @Column(name = "last_updated", nullable = false)
    private Instant lastUpdated;

}
