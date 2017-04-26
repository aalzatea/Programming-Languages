package com.hugeinc.test.models.enums;

/**
 * Created by aalzate on 10/21/16.
 */
public enum RunStatus {
    ENABLED("E"), DISABLED("D");

    private String value;

    RunStatus(String value) {
        this.value = value;
    }

    /**
     * Based on a given string representing the value obtains a RunStatus value.
     *
     * @param value a string representing the RunStatus
     * @return a RunStatus based on a given value.
     */
    public static RunStatus fromValue(String value) {
        for (RunStatus runStatus : RunStatus.values()) {
            if (runStatus.value.equals(value)) {
                return runStatus;
            }
        }
        return null;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return value;
    }
}
