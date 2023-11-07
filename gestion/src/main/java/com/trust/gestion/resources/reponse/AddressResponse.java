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
    Integer id;
    String civicNumber;
    String streetName;
    String aptNumber;
    String city;
    String province;
    String postalCode;
    String country;
    AddressType type;
}
