package com.hugeinc.test.models;

import com.hugeinc.test.models.enums.DistanceMeasurementUnit;
import com.hugeinc.test.models.enums.Pace;
import com.hugeinc.test.models.enums.RunStatus;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;

/**
 * Created by aalzate on 10/21/16.
 */
public class Run {

    private String id;

    private String startingAddress;

    private String hostUserId;

    private Date createdDate;

    private Date modifiedDate;

    private Date startDate;

    private Double distance;

    private DistanceMeasurementUnit distanceMeasurementUnit;

    private Pace pace;

    private boolean feature;

    private boolean flagged;

    private boolean publicRun;

    private Integer maxRunners;

    private RunStatus runStatus;

    private String title;

    private String message;

    private String description;

    private Collection<Location> route;

    private Collection<Attendee> attendees;

    private BigDecimal latitude;

    private BigDecimal longitude;

    public String getStartingAddress() {
        return startingAddress;
    }

    public void setStartingAddress(String startingAddress) {
        this.startingAddress = startingAddress;
    }

    public String getHostUserId() {
        return hostUserId;
    }

    public void setHostUserId(String hostUserId) {
        this.hostUserId = hostUserId;
    }

    public Date getCreatedDate() {
        return new Date(createdDate.getTime());
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = new Date(createdDate.getTime());
    }

    public Date getModifiedDate() {
        return new Date(modifiedDate.getTime());
    }

    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = new Date(modifiedDate.getTime());
    }

    public Date getStartDate() {
        return new Date(startDate.getTime());
    }

    public void setStartDate(Date startDate) {
        this.startDate = new Date(startDate.getTime());
    }

    public Double getDistance() {
        return distance;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
    }

    public DistanceMeasurementUnit getDistanceMeasurementUnit() {
        return distanceMeasurementUnit;
    }

    public void setDistanceMeasurementUnit(DistanceMeasurementUnit distanceMeasurementUnit) {
        this.distanceMeasurementUnit = distanceMeasurementUnit;
    }

    public Pace getPace() {
        return pace;
    }

    public void setPace(Pace pace) {
        this.pace = pace;
    }

    public boolean isFeature() {
        return feature;
    }

    public void setFeature(boolean feature) {
        this.feature = feature;
    }

    public boolean isFlagged() {
        return flagged;
    }

    public void setFlagged(boolean flagged) {
        this.flagged = flagged;
    }

    public boolean isPublicRun() {
        return publicRun;
    }

    public void setPublicRun(boolean publicRun) {
        this.publicRun = publicRun;
    }

    public Integer getMaxRunners() {
        return maxRunners;
    }

    public void setMaxRunners(Integer maxRunners) {
        this.maxRunners = maxRunners;
    }

    public RunStatus getRunStatus() {
        return runStatus;
    }

    public void setRunStatus(RunStatus runStatus) {
        this.runStatus = runStatus;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Collection<Location> getRoute() {
        return route;
    }

    public void setRoute(Collection<Location> route) {
        this.route = route;
    }

    public Collection<Attendee> getAttendees() {
        return attendees;
    }

    public void setAttendees(Collection<Attendee> attendees) {
        this.attendees = attendees;
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Run{" +
                "id='" + id + '\'' +
                ", startingAddress='" + startingAddress + '\'' +
                ", hostUserId='" + hostUserId + '\'' +
                ", createdDate=" + createdDate +
                ", modifiedDate=" + modifiedDate +
                ", startDate=" + startDate +
                ", distance=" + distance +
                ", distanceMeasurementUnit=" + distanceMeasurementUnit +
                ", pace=" + pace +
                ", feature=" + feature +
                ", flagged=" + flagged +
                ", publicRun=" + publicRun +
                ", maxRunners=" + maxRunners +
                ", runStatus=" + runStatus +
                ", title='" + title + '\'' +
                ", message='" + message + '\'' +
                ", description='" + description + '\'' +
                ", route=" + route +
                ", attendees=" + attendees +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                '}';
    }
}
