package com.FeatureDocClient.FeatureDocCLI.model.model;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
public class RoleResponse {
    private Integer roleID;
    private String roleName;

    public RoleResponse(String roleName) {
        this.roleName = roleName;
    }

    @Override
    public String toString() {
        // Use String.format for cleaner formatting
        return String.format("RoleID: %-20s | Role Name: %-20s", roleID, roleName);
    }
}
