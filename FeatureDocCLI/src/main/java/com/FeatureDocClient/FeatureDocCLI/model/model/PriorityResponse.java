package com.FeatureDocClient.FeatureDocCLI.model.model;


import lombok.Getter;
import lombok.Setter;

public class PriorityResponse {
    private Integer priorityID;
    private String description;

    public Integer getPriorityID() {
        return priorityID;
    }

    public void setPriorityID(Integer priorityID) {
        this.priorityID = priorityID;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public PriorityResponse(String description) {
        this.description = description;
    }


    @Override
    public String toString() {
        return String.format("Priority ID: %-1s | Description: %-1s", priorityID, description);
    }
}
