package com.trust.gestion.query;

public enum SearchOperation {
    CONTAINS,
    DOES_NOT_CONTAIN,
    EQUAL,
    NOT_EQUAL,
    BEGINS_WITH,
    DOES_NOT_BEGIN_WITH,
    ENDS_WITH,
    DOES_NOT_END_WITH,
    NUL,
    NOT_NULL,
    GREATER_THAN,
    GREATER_THAN_EQUAL,
    LESS_THAN,
    LESS_THAN_EQUAL,
    ANY,
    ALL;

    public static SearchOperation getOption(String input){
        return switch (input) {
            case "cn" -> SearchOperation.CONTAINS;
            case "nc" -> SearchOperation.DOES_NOT_CONTAIN;
            case "eq" -> SearchOperation.EQUAL;
            case "ne" -> SearchOperation.NOT_EQUAL;
            case "bw" -> SearchOperation.BEGINS_WITH;
            case "bn" -> SearchOperation.DOES_NOT_BEGIN_WITH;
            case "ew" -> SearchOperation.ENDS_WITH;
            case "en" -> SearchOperation.DOES_NOT_END_WITH;
            case "nu" -> SearchOperation.NUL;
            case "nn" -> SearchOperation.NOT_NULL;
            case "gt" -> SearchOperation.GREATER_THAN;
            case "ge" -> SearchOperation.GREATER_THAN_EQUAL;
            case "lt" -> SearchOperation.LESS_THAN;
            case "le" -> SearchOperation.LESS_THAN_EQUAL;
            default -> null;
        };
    }

}
