package com.trust.gestion.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "telephones")
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class TelephoneEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Size(max = 255)
    @NotNull
    @Column(name = "entity_id", nullable = false)
    private String entityId;

    @Size(max = 255)
    @NotNull
    @Column(name = "area_code", nullable = false)
    private String areaCode;

    @Size(max = 255)
    @NotNull
    @Column(name = "number", nullable = false)
    private String number;

    @Size(max = 255)
    @NotNull
    @Column(name = "carrier", nullable = false)
    private String carrier;

    @Size(max = 255)
    @NotNull
    @Column(name = "country", nullable = false)
    private String country;

    @NotNull
    @Column(name = "registration_date", nullable = false)
    private Instant registrationDate;

    @NotNull
    @Column(name = "last_updated", nullable = false)
    private Instant lastUpdated;

}
