package com.FeatureDocClient.FeatureDocCLI.model.model;



public class UserResponse {
    private Integer userID;
    private String name;
    private String email;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        // Use String.format for cleaner formatting
        return String.format("ID: %-7d Name: %-20s | Email: %-30s",userID, name, email);
    }

    public Integer getUserID() {
        return userID;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
    }
}
