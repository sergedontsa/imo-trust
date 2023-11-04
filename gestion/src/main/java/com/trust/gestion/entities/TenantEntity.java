/*
 * Copyright (c) 2023.  - Serge G. Dontsa
 */

package com.trust.gestion.entities;

import com.trust.gestion.enums.Gender;
import com.trust.gestion.enums.Status;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "tenant")
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class TenantEntity {
    @Id
    @Size(max = 20)
    @Column(name = "id", nullable = false, length = 20)
    private String id;

    @Size(max = 20)
    @NotNull
    @Column(name = "first_name", nullable = false, length = 20)
    private String firstName;

    @Size(max = 20)
    @Column(name = "middle_name", nullable = false, length = 20)
    private String middleName;

    @Size(max = 20)
    @NotNull
    @Column(name = "last_name", nullable = false, length = 20)
    private String lastName;

    @Size(max = 20)
    @NotNull
    @Column(name = "gender", nullable = false, length = 20)
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Size(max = 20)
    @NotNull
    @Column(name = "status", nullable = false, length = 20)
    @Enumerated(EnumType.STRING)
    private Status status;

    @NotNull
    @Column(name = "date_of_birth", nullable = false)
    private LocalDate dateOfBirth;

    @Size(max = 20)
    @NotNull
    @Column(name = "country_of_origin", nullable = false, length = 20)
    private String countryOfOrigin;

    @Size(max = 20)
    @NotNull
    @Column(name = "city_of_origin", nullable = false, length = 20)
    private String cityOfOrigin;

    @Size(max = 250)
    @Column(name = "description", nullable = false, length = 250)
    private String description;

    @Size(max = 20)
    @NotNull
    @Column(name = "contact_type", nullable = false, length = 20)
    private String contactType;

    @NotNull
    @Column(name = "registration_date", nullable = false)
    private Instant registrationDate;

    @NotNull
    @Column(name = "last_updated", nullable = false)
    private Instant lastUpdated;

}
