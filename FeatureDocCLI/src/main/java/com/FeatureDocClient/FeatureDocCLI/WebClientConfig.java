package com.FeatureDocClient.FeatureDocCLI;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

    //also provides a default
    @Value("${api_url}")
    private String baseUrl;

    private String authToken;

    @Bean
    public WebClient webClient() {

        return WebClient.builder()
                .baseUrl(baseUrl)
                .defaultHeaders(httpHeaders -> {
                    httpHeaders.add("Accept", "application/json");
                    if (authToken != null && !authToken.isEmpty()) {
                        httpHeaders.add("Authorization", "Bearer " + authToken);
                    }
                })
                .build();
    }

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }

    public String getAuthToken() {
        return authToken;
    }
}