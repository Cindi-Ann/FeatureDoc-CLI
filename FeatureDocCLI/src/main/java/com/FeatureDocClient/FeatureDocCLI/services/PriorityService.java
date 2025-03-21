package com.FeatureDocClient.FeatureDocCLI.services;

import com.FeatureDocClient.FeatureDocCLI.JWTUtils;
import com.FeatureDocClient.FeatureDocCLI.commands.LoginCommand;
import com.FeatureDocClient.FeatureDocCLI.model.model.PriorityResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PriorityService {

    // WebClient is used to make HTTP requests to other services
    private final WebClient webClient;

    public PriorityService(WebClient webClient) {
        this.webClient = webClient;
    }

    public Mono<String> getAllPriorities() {
        return webClient.get()
                .uri("/priorities")
                .header("Authorization", "Bearer " + JWTUtils.getJwt())
                .retrieve()
                .bodyToFlux(PriorityResponse.class)
                .collectList()
                .map(users -> users.isEmpty()
                        ? "No priorities found."
                        : "Priorities:\n" + users.stream()
                        .map(PriorityResponse::toString)
                        .collect(Collectors.joining("\n"))
                )
                .onErrorResume(e -> {
                    System.err.println("Error occurred: " + e.getMessage());
                    return Mono.just("Error retrieving priorities: " + e.getMessage());
                });
    }

    public Mono<String> createPriority(String description) {
        PriorityResponse request = new PriorityResponse(description);
        return webClient.post()
                .uri("/priorities") // Endpoint to create a new priority
                .header("Authorization", "Bearer " + JWTUtils.getJwt())
                .bodyValue(request) // Send the request body (description only)
                .retrieve()
                .bodyToMono(PriorityResponse.class) // Expect a single PriorityResponse in the response
                .map(priority -> "Priority created successfully: " + priority.toString())
                .onErrorResume(e -> {
                    System.err.println("Error occurred: " + e.getMessage());
                    return Mono.just("Error creating priority: " + e.getMessage());
                });
    }

    public Mono<String> deletePriority(Integer priorityID) {
        return webClient.delete()
                .uri("/priorities/{id}", priorityID) // Use path variable for priorityID
                .header("Authorization", "Bearer " + JWTUtils.getJwt())
                .retrieve()
                .bodyToMono(Void.class)
                .thenReturn("Priority deleted successfully: " + priorityID)
                .onErrorResume(e -> {
                    System.err.println("Error occurred: " + e.getMessage());
                    return Mono.just("Error deleting priority: " + e.getMessage());
                });
    }

    public Mono<String> getPriorityById(Integer id) {
        return webClient.get()
                .uri("/priorities/{id}", id)
                .header("Authorization", "Bearer " + JWTUtils.getJwt())
                .retrieve()
                .bodyToMono(PriorityResponse.class)
                .map(user -> "Priority:\n" + user.toString())
                .defaultIfEmpty("Priority not found.")
                .onErrorResume(e -> {
                    System.err.println("Error occurred: " + e.getMessage());
                    return Mono.just("Error retrieving priority: " + e.getMessage());
                });
    }

}
