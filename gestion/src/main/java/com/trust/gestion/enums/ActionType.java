package com.trust.gestion.enums;

public enum ActionType implements EnumBase{
    OWNER_CREATE("OWNER_CREATE"),
    OWNER_UPDATE("OWNER_UPDATE"),
    OWNER_DELETE("OWNER_DELETE"),
    BUILDING_CREATE("BUILDING_CREATE"),
    BUILDING_UPDATE("BUILDING_UPDATE"),
    BUILDING_DELETE("BUILDING_DELETE"),
    TENANT_CREATE("TENANT_CREATE"),
    TENANT_UPDATE("TENANT_UPDATE"),
    TENANT_DELETE("TENANT_DELETE");

    private final String value;

    ActionType(String value) {
        this.value = value;
    }


    @Override
    public String getValue() {
        return this.value;
    }
    public ActionType getAction(String value) {
        for(ActionType v : values())
            if(v.getValue().equalsIgnoreCase(value)) return v;
        throw new IllegalArgumentException();
    }
}
