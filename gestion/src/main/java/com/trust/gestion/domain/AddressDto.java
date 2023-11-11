package com.trust.gestion.domain;

import com.trust.gestion.enums.AddressType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.Instant;

/**
 * DTO for {@link com.trust.gestion.entities.AddressEntity}
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class AddressDto implements Serializable {
    @Id
    Integer id;
    private String entityId;
    @NotNull
    @Size(max = 10)
    private String civicNumber;
    @NotNull
    @Size(max = 50)
    private String streetName;
    @Size(max = 10)
    private String aptNumber;
    @Size(max = 50)
    private String city;
    @Size(max = 50)
    private String province;
    @Size(max = 10)
    private String postalCode;
    @Size(max = 50)
    private String country;
    @NotNull
    private AddressType type;
    @NotNull
    private Instant registrationDate;
    @NotNull
    private Instant lastUpdated;
}
