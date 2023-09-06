package com.trust.gestion.enums;

public enum Status implements EnumBase{
    AVAILABLE("AVAILABLE"),
    OCCUPIED("OCCUPIED"),
    RESERVED("RESERVED"),
    SUSPENDED("SUSPENDED"),
    OPEN("OPEN"),
    CLOSED("CLOSED"),
    REJECTED("REJECTED"),
    PENDING("PENDING"),
    ACTIVE("ACTIVE"),
    HOLD("HOLD"),
    INACTIVE("INACTIVE");

    private final String value;

    Status(String value){
        this.value = value;
    }
    @Override
    public String getValue() {
        return this.value;
    }
    public Status fromValue(String value){
        for(Status v : values()){
            if(v.getValue().equalsIgnoreCase(value)){
                return v;
            }
        }
        throw new IllegalArgumentException();
    }
}
