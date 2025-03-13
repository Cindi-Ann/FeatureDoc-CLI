package com.FeatureDocClient.FeatureDocCLI.commands;
import com.FeatureDocClient.FeatureDocCLI.services.UserService;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import reactor.core.publisher.Mono;

@ShellComponent
public class UserCommands {

    private final UserService userService;

    public UserCommands(UserService userService) {
        this.userService = userService;
    }

    @ShellMethod(key = "register", value = "Register a user with name and email")
    public String register(String name, String email) {
        Mono<String> response = userService.registerUser(name, email);
        return response.block(); // Block to get the result (for simplicity in a shell command)
    }

    @ShellMethod(key = "get-all-users", value = "Get all users")
    public String getAllUsers() {
        Mono<String> response = userService.getAllUsers();
        return response.block();
    }

    @ShellMethod(key = "get-user", value = "Get user by id")
    public String getUsers(Integer userID) {
        Mono<String> response = userService.getUserById(userID);
        return response.block();
    }
}
