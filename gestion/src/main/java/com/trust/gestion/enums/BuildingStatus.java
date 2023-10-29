package com.trust.gestion.enums;

import lombok.Getter;

import java.util.Arrays;

@Getter
public enum BuildingStatus {
    OPEN("OPEN"),
    CLOSED("CLOSED"),
    REJECTED("REJECTED"),
    EVALUATION("EVALUATION"),
    PENDING("PENDING");

    private final String value;

    BuildingStatus(String value) {
        this.value = value;
    }

    public static BuildingStatus fromValue(String value) {
        return Arrays.stream(BuildingStatus.values())
                .filter(country -> country.getValue().equals(value))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Building not found"));
    }
}
