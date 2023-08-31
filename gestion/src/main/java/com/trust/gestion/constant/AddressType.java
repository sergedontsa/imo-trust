package com.trust.gestion.constant;

public enum AddressType {
    MAILING("MAILING"),
    RESIDENTIAL("RESIDENTIAL"),
    BUSINESS("BUSINESS"),
    BILLING("BILLING"),
    MAILING_BILLING("MAILING_BILLING");

    private String name;

    AddressType(String name) {
        this.name = name;
    }
    String getName() {
        return this.name;
    }
    AddressType getAddressType(String name) {
        for (AddressType addressType : AddressType.values()) {
            if (addressType.getName().equals(name)) {
                return addressType;
            }
        }
        throw new IllegalArgumentException("AddressType not found.");
    }


}
