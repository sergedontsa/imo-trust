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
    private String id;
    @NotNull
    @Size(max = 20)
    private String firstName;
    @NotNull
    @Size(max = 20)
    private String middleName;
    @NotNull
    @Size(max = 20)
    private String lastName;
    @NotNull
    @Size(max = 20)
    private Gender gender;
    @NotNull
    @Size(max = 20)
    private Status status;
    @NotNull
    private LocalDate dateOfBirth;
    @NotNull
    @Size(max = 20)
    private String countryOfOrigin;
    @NotNull
    @Size(max = 20)
    private String cityOfOrigin;
    @NotNull
    @Size(max = 250)
    private String description;
    @NotNull
    @Size(max = 20)
    private String contactType;
    @NotNull
    private Instant registrationDate;
    @NotNull
    private Instant lastUpdated;
    @NotNull
    private String apartmentId;
}
