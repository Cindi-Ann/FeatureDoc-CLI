package com.FeatureDocClient.FeatureDocCLI.services;

import com.FeatureDocClient.FeatureDocCLI.commands.LoginCommand;
import com.FeatureDocClient.FeatureDocCLI.model.model.RoleResponse;
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
                .header("Authorization", "Bearer " + LoginCommand.getAccessToken())
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
                .header("Authorization", "Bearer " + LoginCommand.getAccessToken())
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
                .header("Authorization", "Bearer " + LoginCommand.getAccessToken())
                .retrieve()
                .bodyToMono(Void.class)
                .thenReturn("Role deleted successfully: " + RoleID)
                .onErrorResume(e -> {
                    System.err.println("Error occurred: " + e.getMessage());
                    return Mono.just("Error deleting Role: " + e.getMessage());
                });
    }

    public Mono<String> getRoleById(Integer id) {
        return webClient.get()
                .uri("/roles/{id}", id)
                .header("Authorization", "Bearer " + LoginCommand.getAccessToken())
                .retrieve()
                .bodyToMono(RoleResponse.class)
                .map(user -> "Role:\n" + user.toString())
                .defaultIfEmpty("Role not found.")
                .onErrorResume(e -> {
                    System.err.println("Error occurred: " + e.getMessage());
                    return Mono.just("Error retrieving Role: " + e.getMessage());
                });
    }

}
