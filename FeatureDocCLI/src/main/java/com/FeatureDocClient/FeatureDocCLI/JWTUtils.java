package com.FeatureDocClient.FeatureDocCLI;

public class JWTUtils {
    private static String jwt;


    public static String getJwt() {
        return jwt;
    }

    public static void setJwt(String jwt) {
        JWTUtils.jwt = jwt;
    }
}
