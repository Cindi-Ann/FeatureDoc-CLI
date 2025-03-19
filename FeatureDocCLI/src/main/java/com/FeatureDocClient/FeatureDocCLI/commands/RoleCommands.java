package com.FeatureDocClient.FeatureDocCLI.commands;

import com.FeatureDocClient.FeatureDocCLI.services.FeatureService;
import com.FeatureDocClient.FeatureDocCLI.services.RoleService;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import reactor.core.publisher.Mono;

@ShellComponent
public class RoleCommands {
    private final RoleService roleService;

    public RoleCommands(RoleService roleService) {
        this.roleService = roleService;
    }

    @ShellMethod(key = "get-roles", value = "get all user roles")
    public String getAllRoles() {
        Mono<String> response = roleService.getAllRoles();
        return response.block(); // Block to get the result (for simplicity in a shell command)
    }

    @ShellMethod(key = "get-role-by-id", value = "get a role by id")
    public String getRoleById(Integer id) {
        Mono<String> response = roleService.getRoleById(id);
        return response.block(); // Block to get the result (for simplicity in a shell command)
    }

    @ShellMethod(key= "create-role", value = "create a role")
    public String createRole(String roleName) {
        Mono<String> response = roleService.createRole(roleName);
        return response.block(); // Block to get the result (for simplicity in a shell command)
    }

    @ShellMethod(key= "delete-role", value = "delete a role")
    public String deleteRole(Integer id) {
        Mono<String> response = roleService.deleteRole(id);
        return response.block(); // Block to get the result (for simplicity in a shell command)
    }

}
