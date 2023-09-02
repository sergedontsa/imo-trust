package com.trust.gestion.enums;

import jakarta.persistence.EnumType;

import java.util.Arrays;

public enum TypeId implements EnumBase{
    DRIVER_LICENSE("DRIVER LICENSE"),
    PASSPORT("PASSPORT"),
    NATIONAL_ID("NATIONAL ID");
    private final String value;

    TypeId(String value){
        this.value = value;
    }


    @Override
    public String getValue() {
        return this.value;
    }
    public static TypeId fromValue(String value){
        for(TypeId typeId : TypeId.values()){
            if(typeId.value.equals(value)){
                return typeId;
            }
        }
        throw new IllegalArgumentException("Unknown enum type " + value + ", Allowed values are " + Arrays.toString(EnumType.values()));
    }
}
