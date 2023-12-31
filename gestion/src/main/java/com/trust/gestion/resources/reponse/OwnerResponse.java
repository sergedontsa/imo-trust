package com.trust.gestion.resources.reponse;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OwnerResponse {
    private String id;
    private String firstName;
    private String lastName;
    private String middleName;
    private String gender;
    private List<AddressResponse> address = new ArrayList<>();
    private List<TelephoneResponse> telephones = new ArrayList<>();
    private List<IdentificationResponse> identifications = new ArrayList<>();

}
