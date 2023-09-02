/*
 * Copyright (c) 2023.  - Serge G. Dontsa
 */

package com.trust.gestion.services.entities;



import com.trust.gestion.enums.Gender;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
import lombok.ToString;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;


@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "owner")
public class OwnerEntity {
    @Id
    @Size(max = 20)
    @Column(name = "id", nullable = false, length = 20)
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

    @Size(max = 10)
    @NotNull
    @Column(name = "gender", nullable = false, length = 10)
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @NotNull
    @Column(name = "registration_date", nullable = false)
    private Instant registrationDate;

    @NotNull
    @Column(name = "last_updated", nullable = false)
    private Instant lastUpdated;

    @OneToMany(mappedBy = "owner", orphanRemoval = true)
    @ToString.Exclude
    private List<OwnerAddressEntity> address = new ArrayList<>();

    @OneToMany(mappedBy = "owner", orphanRemoval = true)
    @ToString.Exclude
    private List<OwnerInformationEntity> information = new ArrayList<>();

    @OneToMany(mappedBy = "owner", orphanRemoval = true)
    @ToString.Exclude
    private List<OwnerContactInformationEntity> contacts = new ArrayList<>();

    @OneToMany(mappedBy = "owner", orphanRemoval = true)
    @ToString.Exclude
    private List<OwnerIdentificationEntity> identifications = new ArrayList<>();

    public void addAddress(OwnerAddressEntity ownerAddressEntity) {
        ownerAddressEntity.setOwner(this);
        this.address.add(ownerAddressEntity);

    }
}
