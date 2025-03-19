package com.FeatureDocClient.FeatureDocCLI.services;

import com.FeatureDocClient.FeatureDocCLI.model.model.RoleResponse;
import com.FeatureDocClient.FeatureDocCLI.model.model.RegistrationResponse;
import com.FeatureDocClient.FeatureDocCLI.model.model.UserResponse;
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

    public Mono<String> getAllroles() {
        return webClient.get()
                .uri("/roles")
                .header("Authorization", "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhY2Nlc3NfdG9rZW4iOiJ5YTI5LmEwQWVYUlBwN3RyMDBQTTZielQxNWQzY2NkaDBZQ2xLLU9ucGNqWUx0STFjbDFGcHZDMHBjbVctRF84eWpMSEJmNTcycFA1NGsxZGtYM1BjQ21jLXV1U0gxTFZHTGttTmZnRlp6N0dNUlRjYm15TmlzaGNJcmNzTnFTWGE2UHlJa1k2OThxTjZ6b2tXaGNBU1hfTUdRTE5qT1p6TDZQbzUwaDROMDJYY2MyR3dhQ2dZS0FXRVNBUkFTRlFIR1gyTWljX0ttMi1zb2xiazV0eFFVNFZiVEFRMDE3NyIsImlhdCI6MTc0MjM5NDMyMSwiZXhwIjoxNzQyMzk3OTIxfQ.eCNIcC6f1_XaRBVGIwPF94q8BJs4v2ssoCi4L-EOuTw")
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
                .header("Authorization", "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhY2Nlc3NfdG9rZW4iOiJ5YTI5LmEwQWVYUlBwN3RyMDBQTTZielQxNWQzY2NkaDBZQ2xLLU9ucGNqWUx0STFjbDFGcHZDMHBjbVctRF84eWpMSEJmNTcycFA1NGsxZGtYM1BjQ21jLXV1U0gxTFZHTGttTmZnRlp6N0dNUlRjYm15TmlzaGNJcmNzTnFTWGE2UHlJa1k2OThxTjZ6b2tXaGNBU1hfTUdRTE5qT1p6TDZQbzUwaDROMDJYY2MyR3dhQ2dZS0FXRVNBUkFTRlFIR1gyTWljX0ttMi1zb2xiazV0eFFVNFZiVEFRMDE3NyIsImlhdCI6MTc0MjM5NDMyMSwiZXhwIjoxNzQyMzk3OTIxfQ.eCNIcC6f1_XaRBVGIwPF94q8BJs4v2ssoCi4L-EOuTw")
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
                .header("Authorization", "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhY2Nlc3NfdG9rZW4iOiJ5YTI5LmEwQWVYUlBwN3RyMDBQTTZielQxNWQzY2NkaDBZQ2xLLU9ucGNqWUx0STFjbDFGcHZDMHBjbVctRF84eWpMSEJmNTcycFA1NGsxZGtYM1BjQ21jLXV1U0gxTFZHTGttTmZnRlp6N0dNUlRjYm15TmlzaGNJcmNzTnFTWGE2UHlJa1k2OThxTjZ6b2tXaGNBU1hfTUdRTE5qT1p6TDZQbzUwaDROMDJYY2MyR3dhQ2dZS0FXRVNBUkFTRlFIR1gyTWljX0ttMi1zb2xiazV0eFFVNFZiVEFRMDE3NyIsImlhdCI6MTc0MjM5NDMyMSwiZXhwIjoxNzQyMzk3OTIxfQ.eCNIcC6f1_XaRBVGIwPF94q8BJs4v2ssoCi4L-EOuTw")
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
                .header("Authorization", "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhY2Nlc3NfdG9rZW4iOiJ5YTI5LmEwQWVYUlBwN3RyMDBQTTZielQxNWQzY2NkaDBZQ2xLLU9ucGNqWUx0STFjbDFGcHZDMHBjbVctRF84eWpMSEJmNTcycFA1NGsxZGtYM1BjQ21jLXV1U0gxTFZHTGttTmZnRlp6N0dNUlRjYm15TmlzaGNJcmNzTnFTWGE2UHlJa1k2OThxTjZ6b2tXaGNBU1hfTUdRTE5qT1p6TDZQbzUwaDROMDJYY2MyR3dhQ2dZS0FXRVNBUkFTRlFIR1gyTWljX0ttMi1zb2xiazV0eFFVNFZiVEFRMDE3NyIsImlhdCI6MTc0MjM5NDMyMSwiZXhwIjoxNzQyMzk3OTIxfQ.eCNIcC6f1_XaRBVGIwPF94q8BJs4v2ssoCi4L-EOuTw")
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
