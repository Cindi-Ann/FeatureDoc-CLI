package com.FeatureDocClient.FeatureDocCLI.model.model;



import lombok.*;
import java.sql.Timestamp;

@Getter
@Setter
public class FeatureVersion {
    private Long featureVersionID;
    private Long updateBy;
    private  Long featureID;
    private  Integer featureStatusID;
    private  Integer priorityID;
    private Long assignedTo;
    private  String name;
    private String shortDescription;
    private Timestamp updatedDate;

    private @NonNull String URL;



}
