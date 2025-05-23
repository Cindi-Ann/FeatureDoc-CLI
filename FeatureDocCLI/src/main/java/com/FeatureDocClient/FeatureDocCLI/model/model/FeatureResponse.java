package com.FeatureDocClient.FeatureDocCLI.model.model;

import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;


public class FeatureResponse {
    private Integer featureVersionID;
    private String featureName;
    private String createdBy;
    private LocalDateTime createdAt;

    public Integer getFeatureVersionID() {
        return featureVersionID;
    }

    public void setFeatureVersionID(Integer featureVersionID) {
        this.featureVersionID = featureVersionID;
    }

    public String getFeatureName() {
        return featureName;
    }

    public void setFeatureName(String featureName) {
        this.featureName = featureName;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public Integer getFeatureID() {
        return featureID;
    }

    public void setFeatureID(Integer featureID) {
        this.featureID = featureID;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public String getFeatureStatus() {
        return featureStatus;
    }

    public void setFeatureStatus(String featureStatus) {
        this.featureStatus = featureStatus;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getAssignedTo() {
        return assignedTo;
    }

    public void setAssignedTo(String assignedTo) {
        this.assignedTo = assignedTo;
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

    public String getURL() {
        return URL;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }

    public LocalDateTime getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(LocalDateTime updatedDate) {
        this.updatedDate = updatedDate;
    }

    public LocalDateTime getDeletedDate() {
        return deletedDate;
    }

    public void setDeletedDate(LocalDateTime deletedDate) {
        this.deletedDate = deletedDate;
    }

    private Integer featureID;
    private String updateBy;
    private String featureStatus;
    private String priority;
    private String assignedTo;
    private String name;
    private String shortDescription;
    private String URL;
    private LocalDateTime updatedDate;
    private LocalDateTime deletedDate;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\n+-----------------------+---------------------------------------+\n");

        appendIfNotNull(sb, "featureVersionID", featureVersionID);
        appendIfNotNull(sb, "featureID", featureID);
        appendIfNotNull(sb, "createdBy", createdBy);
        appendIfNotNull(sb, "updatedBy", updateBy);
        appendIfNotNull(sb, "featureStatusID", featureStatus);
        appendIfNotNull(sb, "priorityID", priority);
        appendIfNotNull(sb, "assignedTo", assignedTo);
        appendIfNotNull(sb, "name", name);
        appendIfNotNull(sb, "shortDescription", shortDescription);
        appendIfNotNull(sb, "URL", URL);
        appendIfNotNull(sb, "updatedDate", updatedDate);
        appendIfNotNull(sb, "deletedDate", deletedDate);

        sb.append("+-----------------------+---------------------------------------+");

        return sb.toString();
    }

    private void appendIfNotNull(StringBuilder sb, String label, Object value) {
        if (value != null) {
            sb.append(String.format("| %-21s | %-37s |\n", label, value));
        }
    }

}
