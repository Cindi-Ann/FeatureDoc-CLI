package com.FeatureDocClient.FeatureDocCLI.services;

import com.FeatureDocClient.FeatureDocCLI.JWTUtils;
import com.FeatureDocClient.FeatureDocCLI.commands.LoginCommand;
import com.FeatureDocClient.FeatureDocCLI.model.model.RoleResponse;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoleService {

    // WebClient is used to make HTTP requests to other services
    private final WebClient webClient;

    public RoleService(WebClient webClient) {
        this.webClient = webClient;
    }

    public Mono<String> getAllRoles() {
        return webClient.get()
                .uri("/roles")
                .header("Authorization", "Bearer " + JWTUtils.getJwt())
                .retrieve()
                .bodyToFlux(RoleResponse.class)
                .collectList()
                .map(users -> users.isEmpty()
                        ? "No roles found."
                        : "roles:\n" + users.stream()
                        .map(RoleResponse::toString)
                        .collect(Collectors.joining("\n"))
                )
                .onErrorResume(e -> {
                    System.err.println("Error occurred: " + e.getMessage());
                    return Mono.just("Error retrieving roles: " + e.getMessage());
                });
    }

    public Mono<String> createRole(String roleName) {
        RoleResponse request = new RoleResponse(roleName);
        return webClient.post()
                .uri("/roles") // Endpoint to create a new Role
                .header("Authorization", "Bearer " + JWTUtils.getJwt())
                .bodyValue(request) // Send the request body (description only)
                .retrieve()
                .bodyToMono(RoleResponse.class) // Expect a single RoleResponse in the response
                .map(Role -> "Role created successfully: " + Role.toString())
                .onErrorResume(e -> {
                    System.err.println("Error occurred: " + e.getMessage());
                    return Mono.just("Error creating Role: " + e.getMessage());
                });
    }

    public Mono<String> deleteRole(Integer RoleID) {
        return webClient.delete()
                .uri("/roles/{id}", RoleID) // Use path variable for RoleID
                .header("Authorization", "Bearer " + JWTUtils.getJwt())
                .exchangeToMono(response -> {
                    HttpStatus status = (HttpStatus) response.statusCode();
                    if (status.is2xxSuccessful()) {
                        return Mono.just("Role deleted successfully: " + RoleID);
                    } else {
                        return response.bodyToMono(String.class)
                                .defaultIfEmpty("Unknown error") // Handle empty error body
                                .flatMap(errorBody -> Mono.error(new RuntimeException("Error deleting role: " + errorBody)));
                    }
                })
                .onErrorResume(e -> Mono.just(e.getMessage()));
    }

    public Mono<String> getRoleById(Integer id) {
        return webClient.get()
                .uri("/roles/{id}", id)
                .header("Authorization", "Bearer " + JWTUtils.getJwt())
                .retrieve()
                .bodyToMono(RoleResponse.class)
                .flatMap(role -> role.getRoleID() != null
                        ? Mono.just("Role:\n" + role.toString())
                        : Mono.just("Role not found.")
                );
    }

}
