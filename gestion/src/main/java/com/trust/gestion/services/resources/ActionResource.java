package com.trust.gestion.services.resources;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class ActionResource {
    private int id;
    @NotNull
    private String title;
    @NotNull
    private String parties;
    @NotNull
    private String partiesId;

    private String description;

    @NotNull
    Instant registrationDate;
    @NotNull
    Instant lastUpdated;

}
