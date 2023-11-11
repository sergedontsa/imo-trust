package com.trust.gestion.resources;

import com.trust.gestion.enums.AddressType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class AddressResource {
    private String civicNumber;
    private String streetName;
    private String aptNumber;
    private String city;
    private String province;
    private String postalCode;
    private String country;
    private AddressType type;
}
