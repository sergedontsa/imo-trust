package com.trust.gestion.resources.reponse;

import com.trust.gestion.enums.AddressType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class AddressResponse {
    private String id;
    private String civicNumber;
    private String streetName;
    private String aptNumber;
    private String city;
    private String province;
    private String postalCode;
    private String country;
    private AddressType type;
}
