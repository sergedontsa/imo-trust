package com.trust.gestion.utilities;

import org.apache.commons.lang3.RandomUtils;

import java.util.Calendar;


public class Utilities {
    private Utilities() {
    }
    public static String getSupplierId() {
        return getPartialId("SUP");
    }
    public static String getWorkerId() {
        return getPartialId("WOR");
    }
    public static String getManagerId() {
        return getPartialId("MAN");
    }
    public static String getOrderId() {
        return getPartialId("org");
    }
    public static String getProjectId() {
        return getPartialId("PRO");
    }
    public static String getProjectSupervisorId() {
        return getPartialId("PRSUP");
    }
    public static String getProjectOwnerId() {
        return getPartialId("PROW");
    }
    public static String getApartmentID() {
        return getPartialId("APA");
    }
    public static String getBillPaidConfirmationId() {
        return getPartialId("BPC");
    }
    public static String getBuildingID() {
        return getPartialId("BUI");
    }
    public static String getProprietorId() {
        return getPartialId("PRO");
    }
    public static String getEmployeeId() {
        return getPartialId("EMP");
    }
    public static String getTenantId() {
        return getPartialId("TEN");
    }
    public static String getApartmentExpenseID() {
        return getPartialId("APE");
    }
    public static String getTenantExpenseId() {
        return getPartialId("TENEX");
    }
    public static String getUserId() {
        return getPartialId("USR");
    }
    public static String getComplainDoneConfirmationId() {
        return getPartialId("CDC");
    }
    public static String getRecordId() {
        return getPartialId("REC");
    }
    public static String getUid() {
        return getPartialId("UID");
    }
    private static String getPartialId(String prefix) {
        return RandomUtils.nextInt(1000, 9999) + prefix.toUpperCase() + String.valueOf(
                Calendar.getInstance().getTimeInMillis()
        ).substring(5, 12) + Calendar.getInstance().get(Calendar.DAY_OF_WEEK);
    }
}
