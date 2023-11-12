package com.trust.gestion.domain;

import com.trust.gestion.enums.Gender;
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
 * DTO for {@link com.trust.gestion.entities.PersonEntity}
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class PersonDto implements Serializable {
    @Size(max = 20)
    private String id;
    @NotNull
    @Size(max = 20)
    private String firstName;
    @Size(max = 20)
    private String middleName;
    @NotNull
    @Size(max = 20)
    private String lastName;
    @NotNull
    private Gender gender;
    @NotNull
    private Instant registrationDate;
    @NotNull
    private Instant lastUpdated;
}
