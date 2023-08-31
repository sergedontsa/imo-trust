package com.trust.gestion.enums;


import jakarta.persistence.EnumType;

import java.util.Arrays;

;public enum StatusType implements EnumBase {
    OPEN("OPEN"),
    CLOSED("CLOSED"),
    REJECTED("REJECTED"),
    PENDING("PENDING");

    private final String value;

    StatusType(String value) {
        this.value = value;
    }

    @Override
    public String getValue() {
        return this.value;
    }
    public static StatusType fromValue(String value) {
        for (StatusType statusType : StatusType.values()) {
            if (statusType.value.equals(value)) {
                return statusType;
            }
        }
        throw new IllegalArgumentException("Unknown enum type " + value + ", Allowed values are " + Arrays.toString(EnumType.values()));
    }
}
