package com.trust.gestion.resources.reponse;

import com.trust.gestion.enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeResponse {
    private String employeeId;
    private String firstName;
    private String middleName;
    private String lastName;
    private Gender gender;
    private List<AddressResponse> address = new ArrayList<>();
    private List<TelephoneResponse> telephones = new ArrayList<>();
    private List<IdentificationResponse> identifications = new ArrayList<>();
}
