package com.FeatureDocClient.FeatureDocCLI.model.model;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;

import java.sql.Timestamp;


public class FeatureCreatedResponse {
    private Integer createdBy;
    private Integer updatedBy;
    private Integer featureStatusID;
    private Integer priorityID;
    private Integer assignedTo;
    private String name;
    private String shortDescription;
    private String URL;

    // Getter for createdBy
    public Integer getCreatedBy() {
        return createdBy;
    }

    // Setter for createdBy
    public void setCreatedBy(Integer createdBy) {
        this.createdBy = createdBy;
    }

    // Getter for updatedBy
    public Integer getUpdatedBy() {
        return updatedBy;
    }

    // Setter for updatedBy
    public void setUpdatedBy(Integer updatedBy) {
        this.updatedBy = updatedBy;
    }

    // Getter for featureStatusID
    public Integer getFeatureStatusID() {
        return featureStatusID;
    }

    // Setter for featureStatusID
    public void setFeatureStatusID(Integer featureStatusID) {
        this.featureStatusID = featureStatusID;
    }

    // Getter for priorityID
    public Integer getPriorityID() {
        return priorityID;
    }

    // Setter for priorityID
    public void setPriorityID(Integer priorityID) {
        this.priorityID = priorityID;
    }

    // Getter for assignedTo
    public Integer getAssignedTo() {
        return assignedTo;
    }

    // Setter for assignedTo
    public void setAssignedTo(Integer assignedTo) {
        this.assignedTo = assignedTo;
    }

    // Getter for name
    public String getName() {
        return name;
    }

    // Setter for name
    public void setName(String name) {
        this.name = name;
    }

    // Getter for shortDescription
    public String getShortDescription() {
        return shortDescription;
    }

    // Setter for shortDescription
    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    // Getter for URL
    public String getURL() {
        return URL;
    }

    // Setter for URL
    public void setURL(String URL) {
        this.URL = URL;
    }

    @Override
    public String toString() {
        return "FeatureCreatedResponse{" +
                "createdBy=" + createdBy +
                ", updatedBy=" + updatedBy +
                ", featureStatusID=" + featureStatusID +
                ", priorityID=" + priorityID +
                ", assignedTo=" + assignedTo +
                ", name='" + name + '\'' +
                ", shortDescription='" + shortDescription + '\'' +
                ", URL='" + URL + '\'' +
                '}';
    }


}

