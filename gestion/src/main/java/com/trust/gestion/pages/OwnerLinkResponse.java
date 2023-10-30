package com.trust.gestion.pages;

import com.trust.gestion.enums.OwnerLink;
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
public class OwnerLinkResponse {
    private String message;
    private String linkTo;
    private OwnerLink linkType;
}
