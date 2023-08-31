package com.trust.gestion.enums;


import jakarta.persistence.EnumType;

import java.util.Arrays;

public enum SubjectType implements EnumBase {
    TENANT("TENANT"),
    EMPLOYEE("EMPLOYEE"),
    OWNER("OWNER"),
    CONTRACTOR("CONTRACTOR"),
    OTHER("OTHER");

    public final String value;
    SubjectType(String value) {
        this.value = value;
    }

    @Override
    public String getValue() {
        return this.value;
    }

    public static SubjectType fromValue(String value) {
        for (SubjectType claimType : SubjectType.values()) {
            if (claimType.value.equals(value)) {
                return claimType;
            }
        }
        throw new IllegalArgumentException("Unknown enum type " + value + ", Allowed values are " + Arrays.toString(EnumType.values()));
    }
}
