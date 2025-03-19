package com.FeatureDocClient.FeatureDocCLI.model.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UserResponse {
    private Integer userID;
    private String name;
    private String email;

}
