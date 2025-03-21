package com.FeatureDocClient.FeatureDocCLI.model.model;


import java.sql.Timestamp;

public class UpdateFeatureResponse {
    private Long featureVersionID;
    private Integer updatedBy;
    private Integer featureID;
    private Integer featureStatusID;
    private Integer priorityID;
    private Integer assignedTo;
    private String name;
    private String shortDescription;
    private Timestamp updatedDate;
    private Timestamp deletedDate;
    private String URL;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("+-----------------------+---------------------------------------+\n");

        appendIfNotNull(sb, "featureVersionID", featureVersionID);
        appendIfNotNull(sb, "featureID", featureID);
        appendIfNotNull(sb, "updatedBy", updatedBy);
        appendIfNotNull(sb, "featureStatusID", featureStatusID);
        appendIfNotNull(sb, "priorityID", priorityID);
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

    public Long getFeatureVersionID() {
        return featureVersionID;
    }

    public void setFeatureVersionID(Long featureVersionID) {
        this.featureVersionID = featureVersionID;
    }

    public Integer getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(Integer updatedBy) {
        this.updatedBy = updatedBy;
    }

    public Integer getFeatureID() {
        return featureID;
    }

    public void setFeatureID(Integer featureID) {
        this.featureID = featureID;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAssignedTo() {
        return assignedTo;
    }

    public void setAssignedTo(Integer assignedTo) {
        this.assignedTo = assignedTo;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public Timestamp getDeletedDate() {
        return deletedDate;
    }

    public void setDeletedDate(Timestamp deletedDate) {
        this.deletedDate = deletedDate;
    }

    public Timestamp getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Timestamp updatedDate) {
        this.updatedDate = updatedDate;
    }

    public String getURL() {
        return URL;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }
}

