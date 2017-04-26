package com.hugeinc.test.models;

/**
 * Created by aalzate on 10/21/16.
 */
public class Attendee {

    private String runId;

    private String userId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getRunId() {
        return runId;
    }

    public void setRunId(String runId) {
        this.runId = runId;
    }

    @Override
    public String toString() {
        return "Attendee{" +
                "runId='" + runId + '\'' +
                ", userId='" + userId + '\'' +
                '}';
    }
}
