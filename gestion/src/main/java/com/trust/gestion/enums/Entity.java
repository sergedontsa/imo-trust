package com.trust.gestion.enums;

public enum Entity implements EnumBase{
    BUILDING("BUILDING"),
    APARTMENT("APARTMENT"),
    TENANT("TENANT"),
    TENANT_APARTMENT("TENANT_APARTMENT"),
    OWNER("OWNER"),
    CONTRACTOR("CONTRACTOR");

    Entity(String value){
        this.value = value;
    }

    private  String value;

    @Override
    public String getValue() {
        return this.value;
    }
}
