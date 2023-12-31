package com.trust.gestion.resources.reponse;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.trust.gestion.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Builder(toBuilder = true)
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TenantResponse {
    private String id;
    private String firstName;
    private String middleName;
    private String lastName;
    private String gender;
    private Status status;
    private LocalDate dateOfBirth;
    private String countryOfOrigin;
    private String cityOfOrigin;
    private String description;
    private String contactType;
    private List<TelephoneResponse> telephone;
    private List<ApartmentResponse> apartments;
    private List<IdentificationResponse> identifications;
}
