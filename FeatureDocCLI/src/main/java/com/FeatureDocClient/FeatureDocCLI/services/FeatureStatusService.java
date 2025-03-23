package com.FeatureDocClient.FeatureDocCLI.services;

import com.FeatureDocClient.FeatureDocCLI.JWTUtils;
import com.FeatureDocClient.FeatureDocCLI.commands.LoginCommand;
import com.FeatureDocClient.FeatureDocCLI.model.model.*;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import java.util.stream.Collectors;

@Service
public class FeatureStatusService {

    // WebClient is used to make HTTP requests to other services
    private final WebClient webClient;

    public FeatureStatusService(WebClient webClient) {
        this.webClient = webClient;
    }

    // Get all feature statuses
    public Mono<String> getAllFeatureStatuses() {
        return webClient.get()
                .uri("/feature-statuses")
                .header("Authorization", "Bearer " + JWTUtils.getJwt())
                .retrieve()
                .bodyToFlux(FeatureStatusResponse.class)
                .collectList()
                .map(featureStatus -> featureStatus.isEmpty()
                        ? "No feature statuses found."
                        : "Feature Statuses:\n" + featureStatus.stream()
                        .map(FeatureStatusResponse::toString)
                        .collect(Collectors.joining("\n"))
                )
                .onErrorResume(e -> {
                    System.err.println("Error occurred: " + e.getMessage());
                    return Mono.just("Error retrieving users: " + e.getMessage());
                });
    }

    // Get feature status by id
    public Mono<String> getFeatureStatusById(Integer id) {
        return webClient.get()
                .uri("/feature-statuses/{id}", id)
                .header("Authorization", "Bearer " + JWTUtils.getJwt())
                .retrieve()
                .bodyToMono(FeatureStatusResponse.class)
                .flatMap(status -> status.getFeatureStatusID() != null
                        ? Mono.just("Feature Status:\n" + status.toString())
                        : Mono.just("Status not found.")
                );
    }

    // Delete feature status by id
    public Mono<String> deleteFeatureStatusById(Integer id) {
        return webClient.delete()
                .uri("/feature-statuses/{id}", id)
                .header("Authorization", "Bearer " + JWTUtils.getJwt())
                .exchangeToMono(response -> {
                    HttpStatus status = (HttpStatus) response.statusCode();
                    if (status.is2xxSuccessful()) {
                        return Mono.just("Feature Status deleted successfully: " + id);
                    } else {
                        return response.bodyToMono(String.class)
                                .defaultIfEmpty("Unknown error") // Handle empty error body
                                .flatMap(errorBody -> Mono.error(new RuntimeException("Error deleting role: " + errorBody)));
                    }
                })
                .onErrorResume(e -> Mono.just(e.getMessage()));
    }

    public Mono<String> createFeatureStatus(String description) {
        FeatureStatusResponse request = new FeatureStatusResponse(description);
        return webClient.post()
                .uri("/feature-statuses") // Endpoint to create a new Feature-Status
                .header("Authorization", "Bearer " + JWTUtils.getJwt())
                .bodyValue(request) // Send the request body (description only)
                .retrieve()
                .bodyToMono(FeatureStatusResponse.class)
                .map(featureStatus -> "Feature-Status created successfully: " + featureStatus.toString())
                .onErrorResume(e -> {
                    System.err.println("Error occurred: " + e.getMessage());
                    return Mono.just("Error creating Feature-Status: " + e.getMessage());
                });
    }
}
