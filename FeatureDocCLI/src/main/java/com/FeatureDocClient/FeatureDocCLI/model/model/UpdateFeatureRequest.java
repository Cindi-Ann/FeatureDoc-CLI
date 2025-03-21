package com.FeatureDocClient.FeatureDocCLI.model.model;
import org.springframework.lang.Nullable;


public class UpdateFeatureRequest {

    @Nullable
    private Long createdBy;

    private Long featureID;
    @Nullable
    private Long updatedBy;
    @Nullable
    private Integer featureStatusID;
    @Nullable
    private Integer priorityID;
    @Nullable
    private Long assignedTo;
    @Nullable
    private String shortDescription;
    @Nullable
    private String name;
    @Nullable
    private String URL;

    public Long getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Long createdBy) {
        this.createdBy = createdBy;
    }

    public Long getFeatureID() {
        return featureID;
    }

    public void setFeatureID(Long featureID) {
        this.featureID = featureID;
    }

    public Long getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(Long updatedBy) {
        this.updatedBy = updatedBy;
    }

    public Integer getFeatureStatusID() {
        return featureStatusID;
    }

    public void setFeatureStatusID(Integer featureStatusID) {
        this.featureStatusID = featureStatusID;
    }

    public Integer getPriorityID() {
        return priorityID;
    }

    public void setPriorityID(Integer priorityID) {
        this.priorityID = priorityID;
    }

    public Long getAssignedTo() {
        return assignedTo;
    }

    public void setAssignedTo(Long assignedTo) {
        this.assignedTo = assignedTo;
    }

    public String getURL() {
        return URL;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }
}


