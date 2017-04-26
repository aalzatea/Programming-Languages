package com.hugeinc.test.models;

import java.math.BigDecimal;

/**
 * Created by aalzate on 10/21/16.
 */
public class Location {

    private String runId;

    private Integer position;

    private BigDecimal latitude;

    private BigDecimal longitude;

    public String getRunId() {
        return runId;
    }

    public void setRunId(String runId) {
        this.runId = runId;
    }

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

    public BigDecimal getLatitude() {
        return latitude;
    }

    public void setLatitude(BigDecimal latitude) {
        this.latitude = latitude;
    }

    public BigDecimal getLongitude() {
        return longitude;
    }

    public void setLongitude(BigDecimal longitude) {
        this.longitude = longitude;
    }

    @Override
    public String toString() {
        return "Location{" +
                "runId='" + runId + '\'' +
                ", position=" + position +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                '}';
    }
}
