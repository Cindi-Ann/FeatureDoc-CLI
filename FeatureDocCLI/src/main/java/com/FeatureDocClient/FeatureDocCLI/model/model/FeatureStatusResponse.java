package com.FeatureDocClient.FeatureDocCLI.model.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FeatureStatusResponse {
    private Integer featureStatusID;
    private String description;

    @Override
    public String toString() {
        return "{" +
                "featureVersionID=" + featureStatusID +
                ", description='" + description + '\'' +
                '}';
    }
}
