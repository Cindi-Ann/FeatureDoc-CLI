package com.FeatureDocClient.FeatureDocCLI.services;

import com.FeatureDocClient.FeatureDocCLI.model.model.FeatureResponse;
import com.FeatureDocClient.FeatureDocCLI.model.model.RegistrationResponse;
import com.FeatureDocClient.FeatureDocCLI.model.model.UserResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import java.util.stream.Collectors;

@Service
public class FeatureService {

    // WebClient is used to make HTTP requests to other services
    private final WebClient webClient;

    public FeatureService(WebClient webClient) {
        this.webClient = webClient;
    }

    // Get a single feature by their ID
    public Mono<String> getFeatureById(Integer id) {
        return webClient.get()
                .uri("/feature/{id}", id)
                .retrieve()
                .bodyToMono(FeatureResponse.class)
                .map(feature -> "Feature:\n" + feature.toString())
                .defaultIfEmpty("Feature not found.")
                .onErrorResume(e -> {
                    System.err.println("Error occurred: " + e.getMessage());
                    return Mono.just("Error retrieving feature: " + e.getMessage());
                });
    }



}
