package com.FeatureDocClient.FeatureDocCLI.services;

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

    private final WebClient redirectClient;

    public UserService(WebClient webClient, WebClient redirectClient) {
        this.webClient = webClient;
        this.redirectClient = redirectClient;
    }

    public Mono<String> registerUser(String name, String email) {
        RegistrationResponse request = new RegistrationResponse(name, email);
        return webClient.post()
                .uri("/register") // The endpoint to call
                .bodyValue(request) // Set the request body
                .retrieve() // Send the request and retrieve the response
                .bodyToMono(String.class); // Convert the response body to a Mono<String>
    }

    // Get a list of all users
    public Mono<String> getAllUsers() {
        return webClient.get()
                .uri("/users")
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
                .retrieve()
                .bodyToFlux(UserRoleResponse.class);

    }

    public void login(String redirectUrl) {
        String baseUrl = "http://localhost:8080"; // Replace with your base URL

        redirectClient.get()
                .uri(baseUrl)
                .exchangeToMono(clientResponse -> {
                    if (clientResponse.statusCode().is3xxRedirection()) {
                        String redirectUrl2 = clientResponse.headers().header("Location").get(0);
                        System.out.println("Redirecting to: " + redirectUrl2);

                        if (redirectUrl.contains("accounts.google.com")) {
                            openBrowser(redirectUrl);
                        }
                    }
                    return clientResponse.bodyToMono(String.class);
                })
                .block(); // Block to execute the request synchronously
    }

    public void openBrowser(String url) {
        try {
            String os = System.getProperty("os.name").toLowerCase();
            if (os.contains("win")) {
                // Windows
                Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler " + url);
            } else if (os.contains("mac")) {
                // macOS
                Runtime.getRuntime().exec("open " + url);
            } else if (os.contains("nix") || os.contains("nux") || os.contains("aix")) {
                // Linux/Unix
                Runtime.getRuntime().exec("xdg-open " + url);
            } else {
                System.out.println("Unsupported operating system.");
                return;
            }
            System.out.println("Browser opened successfully.");
        } catch (Exception e) {
            System.out.println("Failed to open browser: " + e.getMessage());
        }
    }

}