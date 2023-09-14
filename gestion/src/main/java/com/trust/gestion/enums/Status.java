package com.trust.gestion.enums;

import java.util.EnumSet;
import java.util.Set;

public enum Status implements EnumBase{
    AVAILABLE("AVAILABLE"),
    OCCUPIED("OCCUPIED"),
    RESERVED("RESERVED"),
    SUSPENDED("SUSPENDED"),
    OPEN("OPEN"),
    CLOSED("CLOSED"),
    PAID("PAID"),
    UNPAID("UNPAID"),
    REJECTED("REJECTED"),
    PENDING("PENDING"),
    PENDING_INSPECTION("PENDING_INSPECTION"),
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
    public static Set<Status> apartmentNotAvailable(){
        return EnumSet.of(SUSPENDED, PENDING_INSPECTION);
    }
    public static Set<Status> validApartmentStatus(){
        return EnumSet.of(OCCUPIED, RESERVED, SUSPENDED, PENDING_INSPECTION, AVAILABLE );
    }
    public static Set<Status> reservedApartment(){
        return EnumSet.of(OCCUPIED, RESERVED);
    }

    public static Set<Status> validTenantStatus() {
        return EnumSet.of(ACTIVE, INACTIVE, HOLD, SUSPENDED);
    }
}
