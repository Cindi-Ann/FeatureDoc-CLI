package com.FeatureDocClient.FeatureDocCLI.model.model;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;

import java.sql.Timestamp;

@Getter
@Setter
@ToString
public class FeatureCreatedResponse {
    private Integer createdBy;
    private Integer featureID;
    private Integer updatedBy;
    private Integer featureStatusID;
    private Integer priorityID;
    private Integer assignedTo;
    private String name;
    private String shortDescription;
    private String URL;
}
