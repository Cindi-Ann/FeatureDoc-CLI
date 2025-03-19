package com.FeatureDocClient.FeatureDocCLI;

public class GoogleOAuth2Config {
    public static final String AUTHORIZATION_URI = "https://accounts.google.com/o/oauth2/auth";
    public static final String TOKEN_URI = "/auth/token";
    public static final String REDIRECT_URI = "http://localhost:3000/login/oauth2/code/google";
    public static final String CLIENT_ID = "8421127185-l0d2bmeb37ri8f7qohh3miot46hgj6l4.apps.googleusercontent.com";
    public static final String CLIENT_SECRET = "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";
    public static final String SCOPE = "openid profile email";
}
