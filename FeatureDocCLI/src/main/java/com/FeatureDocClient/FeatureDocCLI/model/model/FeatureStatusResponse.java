package com.FeatureDocClient.FeatureDocCLI.model.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class FeatureStatusResponse {
    private Integer featureStatusID;
    private String description;
}
