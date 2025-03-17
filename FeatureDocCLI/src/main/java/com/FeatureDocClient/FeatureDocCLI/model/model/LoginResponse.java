package com.FeatureDocClient.FeatureDocCLI.model.model;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginResponse {
    private String username;
    private String email;
}
