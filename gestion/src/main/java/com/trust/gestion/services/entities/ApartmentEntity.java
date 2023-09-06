/*
 * Copyright (c) 2023.  - Serge G. Dontsa
 */

package com.trust.gestion.services.entities;

import com.trust.gestion.enums.Status;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

import static jakarta.persistence.GenerationType.AUTO;

@Getter
@Setter
@Entity
@Table(name = "apartments")
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class ApartmentEntity {
    @Id
    @Column(name = "apartment_id", nullable = false)
    @GeneratedValue(strategy = AUTO)
    private Integer id;

    @Size(max = 20)
    @Column(name = "apartment_number", length = 20)
    private String apartmentNumber;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "building_id", nullable = false)
    private BuildingEntity building;

    @Column(name = "num_bedrooms")
    private Integer numBedrooms;

    @Column(name = "square_footage", precision = 10, scale = 2)
    private BigDecimal squareFootage;

    @Column(name = "rent_amount", precision = 10, scale = 2)
    private BigDecimal rentAmount;

    @Size(max = 250)
    @Column(name = "description", length = 250)
    private String description;

    @Size(max = 20)
    @Column(name = "availability_status", length = 20)
    private Status status;

}
