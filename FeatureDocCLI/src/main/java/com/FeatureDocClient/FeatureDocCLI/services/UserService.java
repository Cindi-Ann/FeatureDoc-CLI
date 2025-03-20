package com.FeatureDocClient.FeatureDocCLI.services;

import com.FeatureDocClient.FeatureDocCLI.commands.LoginCommand;

import com.FeatureDocClient.FeatureDocCLI.model.model.FeatureResponse;
import com.FeatureDocClient.FeatureDocCLI.model.model.UserResponse;
import com.FeatureDocClient.FeatureDocCLI.model.model.UserRoleResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


import java.util.stream.Collectors;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;


@Service
public class UserService {

    // WebClient is used to make HTTP requests to other services
    private final WebClient webClient;

    public UserService(WebClient webClient, WebClient redirectClient) {
        this.webClient = webClient;
    }

    public Mono<String> loginUser(String authCode) {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder.path("/auth/token")  // Define the path
                        .queryParam("code", authCode)  // Add the 'code' as a query parameter
                        .build())
                .retrieve()
                .bodyToMono(String.class)  // Retrieve the response body as a string
                .map(response -> {

                    try {

                        ObjectMapper objectMapper = new ObjectMapper();

                        JsonNode jsonNode = objectMapper.readTree(response);
                        // Extract te access_token
                        String accessToken = jsonNode.get("access_token").asText();

                        return accessToken;
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    return "No token received";
                });
    }

    // Get a list of all users
    public Mono<String> getAllUsers() {
        return webClient.get()
                .uri("/users")
                .header("Authorization", "Bearer " + LoginCommand.getAccessToken())
                .retrieve()
                .bodyToFlux(UserResponse.class)
                .collectList()
                .map(users -> users.isEmpty()
                        ? "No users found."
                        : "Users:\n" + users.stream()
                        .map(UserResponse::toString)
                        .collect(Collectors.joining("\n"))
                )
                .onErrorResume(e -> {
                    System.err.println("Error occurred: " + e.getMessage());
                    return Mono.just("Error retrieving users: " + e.getMessage());
                });
    }

    // Get a single user by their ID
    public Mono<String> getUserById(Integer id) {
        return webClient.get()
                .uri("/users/{id}", id)
                .header("Authorization", "Bearer " + LoginCommand.getAccessToken())
                .retrieve()
                .bodyToMono(UserResponse.class)
                .map(user -> "User:\n" + user.toString())
                .defaultIfEmpty("User not found.")
                .onErrorResume(e -> {
                    System.err.println("Error occurred: " + e.getMessage());
                    return Mono.just("Error retrieving user: " + e.getMessage());
                });
    }

    public Flux<UserRoleResponse> getRolesByUserId(Integer id) {
        return webClient.get()
                .uri("/user-roles/user/{id}", id)
                .header("Authorization", "Bearer " + LoginCommand.getAccessToken())
                .retrieve()
                .bodyToFlux(UserRoleResponse.class);

    }

    public Mono<String> createUserRole(UserRoleResponse.UserRoleId userRolId) {

        return webClient.post()
                .uri("/user-roles")
                .header("Authorization", "Bearer " + LoginCommand.getAccessToken())
                .bodyValue(userRolId)
                .retrieve()
                .bodyToMono(UserRoleResponse.class)
                .map(priority -> "User Role created successfully: " + priority.toString())
                .onErrorResume(e -> {
                    System.err.println("Error occurred: " + e.getMessage());
                    return Mono.just("Error creating priority: " + e.getMessage());
                });
    }

}