package com.trust.gestion.domain;

import com.trust.gestion.entities.ActionEntity;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.Instant;

/**
 * DTO for {@link ActionEntity}
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class ActionDto implements Serializable {
    Integer id;
    @NotNull
    @Size(max = 50)
    String title;
    @NotNull
    @Size(max = 50)
    String parties;
    @NotNull
    @Size(max = 20)
    String partiesId;
    @NotNull
    @Size(max = 250)
    String description;
    @NotNull
    Instant registrationDate;
    @NotNull
    Instant lastUpdated;
}
