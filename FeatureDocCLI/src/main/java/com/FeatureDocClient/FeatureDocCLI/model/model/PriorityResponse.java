package com.FeatureDocClient.FeatureDocCLI.model.model;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PriorityResponse {

    private Integer priorityID;
    private String description;

    public PriorityResponse(String description) {
        this.description = description;
    }


    @Override
    public String toString() {
        return String.format("Priority ID: %-10s | Description: %-10s", priorityID, description);
    }
}
