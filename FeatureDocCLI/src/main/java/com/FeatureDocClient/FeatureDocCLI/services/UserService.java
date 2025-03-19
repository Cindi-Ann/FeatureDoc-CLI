package com.FeatureDocClient.FeatureDocCLI.services;

import com.FeatureDocClient.FeatureDocCLI.model.model.PriorityResponse;
import com.FeatureDocClient.FeatureDocCLI.model.model.RegistrationResponse;
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

    public UserService(WebClient webClient, WebClient redirectClient) {
        this.webClient = webClient;
    }

    public Mono<String> registerUser(String name, String email) {
        RegistrationResponse request = new RegistrationResponse(name, email);
        return webClient.post()
                .uri("/register") // The endpoint to call
                .bodyValue(request) // Set the request body
                .retrieve() // Send the request and retrieve the response
                .bodyToMono(String.class); // Convert the response body to a Mono<String>
    }

    public Mono<String> loginUser(String authCode) {
        return webClient.post()
                .uri("/login") // Endpoint to create a new priority
                .cookie("JSESSIONID", "DBA44AF5A2D0898ABA101C98CF3F9230")
                .bodyValue(authCode) // Send the request body (description only)
                .retrieve()
                .bodyToMono(String.class);
    }

    // Get a list of all users
    public Mono<String> getAllUsers() {
        return webClient.get()
                .uri("/users")
                .cookie("JSESSIONID", "DBA44AF5A2D0898ABA101C98CF3F9230")
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
                .cookie("JSESSIONID", "DBA44AF5A2D0898ABA101C98CF3F9230")
                .retrieve()
                .bodyToFlux(UserRoleResponse.class);

    }

}