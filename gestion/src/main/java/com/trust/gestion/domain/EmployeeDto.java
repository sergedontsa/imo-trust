package com.trust.gestion.domain;

import com.trust.gestion.enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

/**
 * DTO for {@link com.trust.gestion.entities.EmployeeEntity}
 */
@Getter
@Setter
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDto implements Serializable {
    private String employeeId;
    private String firstName;
    private String middleName;
    private String lastName;
    private Gender gender;
    private Instant registrationDate;
    private Instant lastUpdated;
    private List<AddressDto> address = new ArrayList<>();
    private List<TelephoneDto> telephones = new ArrayList<>();
    private List<IdentificationDto> identifications = new ArrayList<>();
}
