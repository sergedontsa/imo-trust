package com.trust.gestion.resources.reponse;

import com.trust.gestion.enums.BuildingStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder(toBuilder = true)
@AllArgsConstructor
public class BuildingResponse {
    private String id;
    private String designation;
    private String description;
    private String category;
    private BuildingStatus status;
    private Boolean assigned;
    private Integer constructionYear;
    private Integer numberOfFloors;
    private Integer numberOfUnits;
    private List<ApartmentResponse> apartments;
    private List<OwnerResponse> owners;
}
