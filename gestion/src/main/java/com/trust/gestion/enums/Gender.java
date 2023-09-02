package com.trust.gestion.enums;

public enum Gender implements EnumBase{
    MALE("MALE"),
    FEMALE("FEMALE");
    private final String value;
    Gender(String value) {
        this.value = value;
    }

    @Override
    public String getValue() {
        return this.value;
    }
    public Gender fromValue(String value) {
        for(Gender v : values())
            if(v.getValue().equalsIgnoreCase(value)) return v;
        throw new IllegalArgumentException();
    }
}
