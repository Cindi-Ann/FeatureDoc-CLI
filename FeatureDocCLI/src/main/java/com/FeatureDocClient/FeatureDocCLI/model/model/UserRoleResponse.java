package com.FeatureDocClient.FeatureDocCLI.model.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@ToString
public class UserRoleResponse {

    private UserRoleId id; // Nested composite key

    public UserRoleResponse(UserRoleId id){
        this.id = id;
    }

    // Inner class to represent the composite key
    @Getter
    @Setter
    public static class UserRoleId {
        private Integer roleID;
        private Integer userID;

        // No-argument constructor (required for deserialization)
        public UserRoleId() {}

        // All-argument constructor (optional, for convenience)
        public UserRoleId(Integer roleID, Integer userID) {
            this.roleID = roleID;
            this.userID = userID;
        }

        @Override
        public String toString() {
            return String.format("User ID: %-5s | Role ID: %-5s", userID, roleID);
        }
    }
}
