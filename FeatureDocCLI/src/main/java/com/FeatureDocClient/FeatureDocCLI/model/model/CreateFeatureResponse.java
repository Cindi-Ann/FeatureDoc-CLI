package com.FeatureDocClient.FeatureDocCLI.model.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class CreateFeatureResponse {
    private Integer featureID;
    private String createdBy;



    @Override
    public String toString() {
        return String.format("+-----------------------+---------------------------------------+\n" +
                        "| %-21s | %-37s |\n" +
                        "| %-21s | %-37s |\n" +
                        "+-----------------------+---------------------------------------+",

                "createdBy", createdBy,
                "featureID", featureID);
    }

}
