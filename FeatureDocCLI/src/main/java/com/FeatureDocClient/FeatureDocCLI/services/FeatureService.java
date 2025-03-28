package com.FeatureDocClient.FeatureDocCLI.services;

import com.FeatureDocClient.FeatureDocCLI.JWTUtils;
import com.FeatureDocClient.FeatureDocCLI.commands.LoginCommand;
import com.FeatureDocClient.FeatureDocCLI.model.model.*;
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


    /// ////////////// Feature ///////////
    public Mono<String> createFeature(FeatureCreatedResponse request) {
        return webClient.post()
                .uri("/feature") // Endpoint to create a new priority
                .header("Authorization", "Bearer " + JWTUtils.getJwt())
                .bodyValue(request) // Send the request body (description only)
                .retrieve()
                .bodyToMono(FeatureResponse.class)
                .map(priority -> "Feature created successfully: " + priority.toString())
                .onErrorResume(e -> {
                    System.err.println("Error occurred: " + e.getMessage());
                    return Mono.just("Error creating feature: " + e.getMessage());
                });
    }

    public Mono<String> updateFeature(UpdateFeatureRequest  request) {
        return webClient.put()
                .uri("/feature") // Endpoint to create a new priority
                .header("Authorization", "Bearer " + JWTUtils.getJwt())
                .bodyValue(request) // Send the request body (description only)
                .retrieve()
                .bodyToMono(UpdateFeatureResponse.class)
                .map(priority -> "Feature updated successfully:\n" + priority.toString())
                .onErrorResume(e -> {
                    System.err.println("Error occurred: " + e.getMessage());
                    return Mono.just("Error updating feature: " + e.getMessage());
                });
    }


    /// ////////////// Feature Version ///////////
    // Get a single feature by their ID
    public Mono<String> getFeatureHistoryById(Integer id) {
        return webClient.get()
                .uri("/feature-versions/{id}/history", id)
                .header("Authorization", "Bearer " + JWTUtils.getJwt())
                .retrieve()
                .bodyToFlux(FeatureResponse.class)
                .collectList()
                .map(users -> users.isEmpty()
                        ? "No history found."
                        : "Feature History:\n" + users.stream()
                        .map(FeatureResponse::toString)
                        .collect(Collectors.joining("\n"))
                )
                .onErrorResume(e -> {
                    System.err.println("Error occurred: " + e.getMessage());
                    return Mono.just("Error retrieving feature history: " + e.getMessage());
                });
    }
    public Mono<String> getLatestFeatureVersionById(Integer id) {
        return webClient.get()
                .uri("/feature-versions/{id}", id)
                .header("Authorization", "Bearer " + JWTUtils.getJwt())
                .retrieve()
                .bodyToMono(FeatureResponse.class)
                .flatMap(feat -> feat.getFeatureID() != null
                        ? Mono.just("Feature Version:\n" + feat.toString())
                        : Mono.just("Feature Version not found.")
                );
    }

    public Mono<String> getAllLatestFeatureVersions() {
        return webClient.get()
                .uri("/feature-versions")
                .header("Authorization", "Bearer " + JWTUtils.getJwt())
                .retrieve()
                .bodyToFlux(FeatureResponse.class)
                .collectList()
                .map(users -> users.isEmpty()
                        ? "No feature versions found"
                        : "Features:\n" + users.stream()
                        .map(FeatureResponse::toString)
                        .collect(Collectors.joining("\n"))
                )
                .onErrorResume(e -> {
                    System.err.println("Error occurred: " + e.getMessage());
                    return Mono.just("Error retrieving latest features: " + e.getMessage());
                });
    }

}
