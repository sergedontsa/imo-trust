package com.trust.gestion.enums;

import static java.util.Arrays.stream;

public enum ActionTitle implements EnumBase{
    OWNER_CREATE("OWNER_CREATE"),
    OWNER_UPDATE("OWNER_UPDATE"),
    OWNER_DELETE("OWNER_DELETE"),
    BUILDING_CREATE("BUILDING_CREATE"),
    BUILDING_UPDATE("BUILDING_UPDATE"),
    BUILDING_DELETE("BUILDING_DELETE"),
    TENANT_CREATE("TENANT_CREATE"),
    TENANT_UPDATE("TENANT_UPDATE"),
    TENANT_DELETE("TENANT_DELETE"),
    OWNER_ADDRESS_CREATE("OWNER_ADDRESS_CREATE"),
    OWNER_INFORMATION_CREATE("OWNER_INFORMATION_CREATE"),
    OWNER_CONTACT_CREATE("OWNER_CONTACT_CREATE"),
    OWNER_IDENTIFICATION_CREATE("OWNER_IDENTIFICATION_CREATE"),
    OWNER_ADDRESS_UPDATE("OWNER_ADDRESS_UPDATE"),
    OWNER_INFORMATION_UPDATE("OWNER_INFORMATION_UPDATE"),
    OWNER_CONTACT_UPDATE("OWNER_CONTACT_UPDATE"),
    OWNER_IDENTIFICATION_UPDATE("OWNER_IDENTIFICATION_UPDATE"),
    APARTMENT_CREATE("APARTMENT_CREATE");

    private final String value;

    ActionTitle(String value) {
        this.value = value;
    }


    @Override
    public String getValue() {
        return this.value;
    }
    public ActionTitle of(String value) {
      return stream(values())
              .filter(actionTitle -> actionTitle.getValue().equals(value))
              .findFirst()
              .orElseThrow(IllegalArgumentException::new);
    }
}
