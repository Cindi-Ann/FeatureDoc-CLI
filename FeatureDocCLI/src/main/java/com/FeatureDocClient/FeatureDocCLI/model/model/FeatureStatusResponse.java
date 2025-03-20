package com.FeatureDocClient.FeatureDocCLI.model.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


public class FeatureStatusResponse {
    private Integer featureStatusID;
    private String description;

    public Integer getFeatureStatusID() {
        return featureStatusID;
    }

    public void setFeatureStatusID(Integer featureStatusID) {
        this.featureStatusID = featureStatusID;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public FeatureStatusResponse(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        // Use String.format for cleaner formatting
        return String.format("Feature Status ID: %-10s | Description: %-10s", featureStatusID, description);
    }
}
