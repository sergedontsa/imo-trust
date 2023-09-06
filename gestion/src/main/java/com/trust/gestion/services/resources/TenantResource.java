package com.trust.gestion.services.resources;

import com.trust.gestion.enums.Gender;
import com.trust.gestion.enums.Status;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class TenantResource {
    @Size(max = 20)
    String id;
    @NotNull
    @Size(max = 20)
    String firstName;
    @NotNull
    @Size(max = 20)
    String middleName;
    @NotNull
    @Size(max = 20)
    String lastName;
    @NotNull
    @Size(max = 20)
    Gender gender;
    @NotNull
    @Size(max = 20)
    Status status;
    @NotNull
    LocalDate dateOfBirth;
    @NotNull
    @Size(max = 20)
    String countryOfOrigin;
    @NotNull
    @Size(max = 20)
    String cityOfOrigin;
    @NotNull
    @Size(max = 250)
    String description;
    @NotNull
    @Size(max = 20)
    String contactType;
    @NotNull
    Instant registrationDate;
    @NotNull
    Instant lastUpdated;
}
