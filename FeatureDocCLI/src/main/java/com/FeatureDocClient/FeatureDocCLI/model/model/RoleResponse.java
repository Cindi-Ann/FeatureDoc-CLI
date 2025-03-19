package com.FeatureDocClient.FeatureDocCLI.model.model;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class RoleResponse {
    private Integer roleID;
    private String roleName;

    public RoleResponse(String roleName) {
        this.roleName = roleName;
    }

}
