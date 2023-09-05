package com.trust.gestion.services.resources;

import com.trust.gestion.enums.OwnerLink;
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
public class OwnerLinkResource {
    private String ownerId;
    private String buildingId;
    private String projectId;
    private OwnerLink linkType;

}
