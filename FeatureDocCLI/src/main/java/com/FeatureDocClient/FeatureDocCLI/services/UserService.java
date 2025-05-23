package com.FeatureDocClient.FeatureDocCLI.services;

import com.FeatureDocClient.FeatureDocCLI.JWTUtils;
import com.FeatureDocClient.FeatureDocCLI.commands.LoginCommand;

import com.FeatureDocClient.FeatureDocCLI.model.model.AccessTokenResponse;
import com.FeatureDocClient.FeatureDocCLI.model.model.UserResponse;
import com.FeatureDocClient.FeatureDocCLI.model.model.UserRoleResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


import java.util.stream.Collectors;


@Service
public class UserService {

    // WebClient is used to make HTTP requests to other services
    private final WebClient webClient;

    public UserService(WebClient webClient) {
        this.webClient = webClient;
    }

    public AccessTokenResponse loginUser(String authCode) {
        // Store token in field
        return webClient.get()
                .uri(uriBuilder -> uriBuilder.path("/auth/token")
                        .queryParam("code", authCode)
                        .queryParam("channel", "CLI")
                        .build())
                .retrieve()
                .bodyToMono(AccessTokenResponse.class)  // Directly parse to AccessTokenResponse
                .doOnSuccess(response -> System.out.println("Response: " + response))
                .doOnError(error -> System.out.println("Error: " + error.getMessage()))
                .block();

    }

    // Get a list of all users
    public Mono<String> getAllUsers() {
        return webClient.get()
                .uri("/users")
                .header("Authorization", "Bearer " + JWTUtils.getJwt())
                .retrieve()
                .bodyToFlux(UserResponse.class)
                .collectList()
                .map(users -> users.isEmpty()
                        ? "No users found."
                        : "Users:\n" + users.stream()
                        .map(UserResponse::toString)
                        .collect(Collectors.joining("\n"))
                );
    }

    // Get a single user by their ID
    public Mono<String> getUserById(Integer id) {
        return webClient.get()
                .uri("/users/{userID}", id)
                .header("Authorization", "Bearer " + JWTUtils.getJwt())
                .retrieve()
                .bodyToMono(UserResponse.class)
                .flatMap(user -> user.getUserID() != null
                        ? Mono.just("User:\n" + user.toString())
                        : Mono.just("User not found.")
                );
    }

    public Flux<String> getRolesByUserId(Integer id) {
        return webClient.get()
                .uri("/user-roles/user/{id}", id)
                .header("Authorization", "Bearer " + JWTUtils.getJwt())
                .retrieve()
                .bodyToFlux(UserRoleResponse.class)
                .flatMap(user -> user.getId() != null
                ? Mono.just(user.toString())
                : Mono.just("User ID not found.")
        );

    }

    public Mono<String> createUserRole(UserRoleResponse.UserRoleId userRolId) {

        return webClient.post()
                .uri("/user-roles")
                .header("Authorization", "Bearer " + JWTUtils.getJwt())
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