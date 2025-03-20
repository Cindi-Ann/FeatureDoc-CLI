package com.FeatureDocClient.FeatureDocCLI.model.model;


import lombok.Getter;
import lombok.Setter;
import lombok.NonNull;

import java.sql.Timestamp;

@Getter
@Setter
public class CreateFeatureVersionResponse {
    private Long featureVersionID;
    private Long updateBy;
    private Long featureID;
    private Integer featureStatusID;
    private Integer priorityID;
    private Long assignedTo;
    private String name;
    private String shortDescription;
    private Timestamp updatedDate;

    private @NonNull String URL;

    @Override
    public String toString() {
        return String.format("+--------------------------------+--------------------------------------------+\n" +
                        "| %-30s | %-40s |\n" +
                        "| %-30s | %-40s |\n" +
                        "| %-30s | %-40s |\n" +
                        "| %-30s | %-40s |\n" +
                        "| %-30s | %-40s |\n" +
                        "| %-30s | %-40s |\n" +
                        "| %-30s | %-40s |\n" +
                        "| %-30s | %-40s |\n" +
                        "| %-30s | %-40s |\n" +
                        "| %-30s | %-40s |\n" +
                        "+--------------------------------+--------------------------------------------+",
                "featureVersionID", featureVersionID,
                "updateBy", updateBy,
                "featureID", featureID,
                "featureStatusID", featureStatusID,
                "priorityID", priorityID,
                "assignedTo", assignedTo,
                "name", name,
                "shortDescription", shortDescription,
                "updatedDate", updatedDate,
                "URL", URL);
    }
}
