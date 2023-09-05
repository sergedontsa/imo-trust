package com.trust.gestion.enums;

public enum OwnerLink implements EnumBase {
    LINK_BUILDING("LINK_BUILDING"),
    LINK_PROJECT("LINK_PROJECT");

    private final String value;

    OwnerLink(String value) {
        this.value = value;
    }

    @Override
    public String getValue() {
        return this.value;
    }
    private OwnerLink fromValue(String input){
        for(OwnerLink v : values())
            if(v.getValue().equalsIgnoreCase(value)) return v;
        throw new IllegalArgumentException();
    }
}
