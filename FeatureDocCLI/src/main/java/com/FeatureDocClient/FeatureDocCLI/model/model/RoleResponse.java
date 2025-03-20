package com.FeatureDocClient.FeatureDocCLI.model.model;


public class RoleResponse {
    private Integer roleID;
    private String roleName;

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public Integer getRoleID() {
        return roleID;
    }

    public void setRoleID(Integer roleID) {
        this.roleID = roleID;
    }

    public RoleResponse(String roleName) {
        this.roleName = roleName;
    }

    @Override
    public String toString() {
        // Use String.format for cleaner formatting
        return String.format("RoleID: %-20s | Role Name: %-20s", roleID, roleName);
    }
}
