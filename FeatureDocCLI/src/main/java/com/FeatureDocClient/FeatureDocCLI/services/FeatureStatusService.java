package com.FeatureDocClient.FeatureDocCLI.services;

import com.FeatureDocClient.FeatureDocCLI.model.model.FeatureResponse;
import com.FeatureDocClient.FeatureDocCLI.model.model.FeatureStatusResponse;
import com.FeatureDocClient.FeatureDocCLI.model.model.RegistrationResponse;
import com.FeatureDocClient.FeatureDocCLI.model.model.UserResponse;
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
                .retrieve()
                .bodyToMono(FeatureStatusResponse.class)
                .map(featureStatus -> "Feature-Status:\n" + featureStatus.toString())
                .defaultIfEmpty("featureStatus not found.")
                .onErrorResume(e -> {
                    System.err.println("Error occurred: " + e.getMessage());
                    return Mono.just("Error retrieving featureStatus: " + e.getMessage());
                });
    }

    // Delete feature status by id
//    public Mono<String> deleteFeatureStatusById(Integer id) {
//        return webClient.delete()
//                .uri("/feature-statuses/{id}", id)
//                .retrieve()
//                .bodyToMono(FeatureResponse.class)
//                .then(Mono.just("Feature status with ID " + id + " deleted successfully."))
//                .onErrorResume(e -> {
//                    System.err.println("Error occurred: " + e.getMessage());
//                    return Mono.just("Error deleting feature status: " + e.getMessage());
//                });
//    }
}
