package com.trust.gestion.resources;

import com.trust.gestion.enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeResource {
    private String employeeId;
    private String firstName;
    private String middleName;
    private String lastName;
    private Gender gender;
}
