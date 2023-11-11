package com.trust.gestion.entities;

import com.trust.gestion.enums.AddressType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
@Table(name = "address")
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class AddressEntity {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "entity_id", nullable = false, length = 20)
    @NotNull
    private String entityId;

    @Size(max = 10)
    @NotNull
    @Column(name = "civic_number", nullable = false, length = 10)
    private String civicNumber;

    @Size(max = 50)
    @NotNull
    @Column(name = "street_name", nullable = false, length = 50)
    private String streetName;

    @Size(max = 10)
    @Column(name = "apt_number", length = 10)
    private String aptNumber;

    @Size(max = 50)
    @Column(name = "city", length = 50)
    private String city;

    @Size(max = 50)
    @Column(name = "province", length = 50)
    private String province;

    @Size(max = 10)
    @Column(name = "postal_code", length = 10)
    private String postalCode;

    @Size(max = 50)
    @Column(name = "country", length = 50)
    private String country;

    @Size(max = 20)
    @NotNull
    @Column(name = "type", nullable = false, length = 20)
    @Enumerated(EnumType.STRING)
    private AddressType type;

    @NotNull
    @Column(name = "registration_date", nullable = false)
    private Instant registrationDate;

    @NotNull
    @Column(name = "last_updated", nullable = false)
    private Instant lastUpdated;

}
