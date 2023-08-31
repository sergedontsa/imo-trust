/*
 * Copyright (c) 2023.  - Serge G. Dontsa
 */

package com.trust.gestion.services.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.antlr.v4.runtime.misc.NotNull;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "contractor_contact_information")
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class ContractorContactInformation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "contractor_id", nullable = false)
    private ContractorEntity contractorEntity;

    @Size(max = 255)
    @NotNull
    @Column(name = "email", nullable = false)
    private String email;

    @Size(max = 20)
    @NotNull
    @Column(name = "cell_phone_number", nullable = false, length = 20)
    private String cellPhoneNumber;

    @Size(max = 10)
    @Column(name = "cell_phone_number_extension", length = 10)
    private String cellPhoneNumberExtension;

    @Size(max = 20)
    @Column(name = "work_phone_number", length = 20)
    private String workPhoneNumber;

    @Size(max = 10)
    @Column(name = "work_phone_number_extension", length = 10)
    private String workPhoneNumberExtension;

    @Size(max = 20)
    @Column(name = "home_phone_number", length = 20)
    private String homePhoneNumber;

    @Size(max = 10)
    @Column(name = "home_phone_number_extension", length = 10)
    private String homePhoneNumberExtension;

    @Size(max = 20)
    @Column(name = "other_phone_number", length = 20)
    private String otherPhoneNumber;

    @Size(max = 10)
    @Column(name = "other_phone_number_extension", length = 10)
    private String otherPhoneNumberExtension;

    @NotNull
    @Column(name = "registration_date", nullable = false)
    private Instant registrationDate;

    @NotNull
    @Column(name = "last_updated", nullable = false)
    private Instant lastUpdated;

}
