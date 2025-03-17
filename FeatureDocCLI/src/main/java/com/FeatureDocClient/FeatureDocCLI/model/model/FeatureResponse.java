package com.FeatureDocClient.FeatureDocCLI.model.model;

import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;

@Getter
@Setter
public class FeatureResponse {
    private Integer featureVersionID;
    private String featureName;
    private String createdBy;
    private LocalDateTime createdAt;
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
        return "{" +
                "featureVersionID=" + featureVersionID +
                ", featureName='" + featureName + '\'' +
                ", createdBy='" + createdBy + '\'' +
                ", createdAt=" + createdAt +
                ", featureID=" + featureID +
                ", updateBy='" + updateBy + '\'' +
                ", featureStatus='" + featureStatus + '\'' +
                ", priority='" + priority + '\'' +
                ", assignedTo='" + assignedTo + '\'' +
                ", name='" + name + '\'' +
                ", shortDescription='" + shortDescription + '\'' +
                ", URL='" + URL + '\'' +
                ", updatedDate=" + updatedDate +
                ", deletedDate=" + deletedDate +
                '}';
    }

}
