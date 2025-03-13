package com.FeatureDocClient.FeatureDocCLI.model.model;

public class RegistrationResponse {
    private String name;
    private String email;

    // Constructors, getters, and setters
    public RegistrationResponse() {}

    public RegistrationResponse(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}