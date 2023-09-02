package com.trust.gestion.enums;

public enum AddressType implements EnumBase{
    MAILING("MAILING"),
    PHYSICAL("PHYSICAL"),
    WORK("WORK"),
    HOME("HOME"),
    OTHER("OTHER");
    private final String value;
    AddressType(String value) {
        this.value = value;
    }

    @Override
    public String getValue() {
        return this.value;
    }
    public AddressType getAddressType(String value) {
        for(AddressType v : values())
            if(v.getValue().equalsIgnoreCase(value)) return v;
        throw new IllegalArgumentException();
    }
}
