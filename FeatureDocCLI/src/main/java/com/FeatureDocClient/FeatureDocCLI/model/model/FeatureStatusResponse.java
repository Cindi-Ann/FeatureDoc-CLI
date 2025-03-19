package com.FeatureDocClient.FeatureDocCLI.model.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
public class FeatureStatusResponse {
    private Integer featureStatusID;
    private String description;

    public FeatureStatusResponse(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        // Use String.format for cleaner formatting
        return String.format("Feature Status ID: %-10s | Description: %-10s", featureStatusID, description);
    }
}
