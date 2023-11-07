package com.trust.gestion.resources.reponse;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class OwnerResponse {
    private String id;
    private String firstName;
    private String lastName;
    private String middleName;
    private String gender;

}
