package com.FeatureDocClient.FeatureDocCLI.commands;
import com.FeatureDocClient.FeatureDocCLI.model.model.UserRoleResponse;
import com.FeatureDocClient.FeatureDocCLI.services.UserService;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import java.awt.Desktop;
import java.net.URI;

@ShellComponent
public class UserCommands {

    private final UserService userService;


    public UserCommands(UserService userService) {
        this.userService = userService;
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

    @ShellMethod(key = "get-user-roles", value = "Get all roles for a user")
    public String getUserRolesById(Integer id) {
        Flux<String> response = userService.getRolesByUserId(id);
        // Blocking call for simplicity (not recommended for production)
        Iterable<String> userRoles = response.toIterable();

        StringBuilder result = new StringBuilder("User Roles:\n");
        for (String userRole : userRoles) {
            result.append(userRole).append("\n");
        }

        return result.toString();
    }

    @ShellMethod(key = "create-user-role", value = "Create user role")
    public String createUserRoles(Integer userID, Integer roleID){
        try {
            UserRoleResponse.UserRoleId userRoleId = new UserRoleResponse.UserRoleId(roleID, userID);
            Mono<String> response = userService.createUserRole(userRoleId);

            return "Role successfully assigned: " + response.block();
        } catch (Exception e) {
            return "Failed to assign role: " + e.getMessage();
        }
    }
}

