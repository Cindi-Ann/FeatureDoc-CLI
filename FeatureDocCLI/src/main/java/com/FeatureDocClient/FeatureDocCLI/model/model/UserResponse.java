package com.FeatureDocClient.FeatureDocCLI.model.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
public class UserResponse {
    private Integer userID;
    private String name;
    private String email;

    @Override
    public String toString() {
        // Use String.format for cleaner formatting
        return String.format("Name: %-20s | Email: %-30s", name, email);
    }
}
