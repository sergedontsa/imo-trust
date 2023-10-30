package com.trust.gestion.resources;

import com.trust.gestion.enums.Entity;
import com.trust.gestion.enums.Status;
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
public class StatusChangeRequestResource {
    private Status status;
    private String id;
    private Entity entity;
}
