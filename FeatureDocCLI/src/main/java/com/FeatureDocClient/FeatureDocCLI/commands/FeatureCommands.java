package com.FeatureDocClient.FeatureDocCLI.commands;

import com.FeatureDocClient.FeatureDocCLI.model.model.FeatureCreatedResponse;
import com.FeatureDocClient.FeatureDocCLI.model.model.UpdateFeatureRequest;
import com.FeatureDocClient.FeatureDocCLI.services.FeatureService;
import com.FeatureDocClient.FeatureDocCLI.services.FeatureStatusService;
import com.FeatureDocClient.FeatureDocCLI.services.PriorityService;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import reactor.core.publisher.Mono;
import com.fasterxml.jackson.databind.ObjectMapper;

@ShellComponent
public class FeatureCommands {

    private final FeatureService featureService;
    private final FeatureStatusService featureStatusService;
    private final PriorityService priorityService;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public FeatureCommands(FeatureService featureService, FeatureStatusService featureStatusService, PriorityService priorityService) {
        this.featureService = featureService;
        this.featureStatusService = featureStatusService;
        this.priorityService = priorityService;
    }

    @ShellMethod(key = "get-feature-history", value = "get a feature's history by id")
    public String getFeatureHistoryById(Integer id) {
        Mono<String> response = featureService.getFeatureHistoryById(id);
        return response.block(); // Block to get the result (for simplicity in a shell command)
    }

    @ShellMethod(key = "get-latest-feature-version", value = "get a feature's latest version by id")
    public String getLatestFeatureVersionById(Integer id) {
        Mono<String> response = featureService.getLatestFeatureVersionById(id);
        return response.block(); // Block to get the result (for simplicity in a shell command)
    }

    @ShellMethod(key = "get-feature-statuses", value = "get all features statuses")
    public String getFeatureStatuses() {
        Mono<String> response = featureStatusService.getAllFeatureStatuses();
        return response.block(); // Block to get the result (for simplicity in a shell command)
    }

    @ShellMethod(key = "get-feature-status-by-id", value = "get features statuses by id")
    public String getFeatureStatusesByID(Integer id) {
        Mono<String> response = featureStatusService.getFeatureStatusById(id);
        return response.block(); // Block to get the result (for simplicity in a shell command)
    }

    @ShellMethod(key = "create-feature-status", value = "create feature status")
    public String createFeatureStatus(String description) {
        Mono<String> response = featureStatusService.createFeatureStatus(description);
        return response.block(); // Block to get the result (for simplicity in a shell command)
    }

    @ShellMethod(key = "delete-feature-status", value = "delete feature status")
    public String deleteFeatureStatus(Integer id) {
        Mono<String> response = featureStatusService.deleteFeatureStatusById(id);
        return response.block(); // Block to get the result (for simplicity in a shell command)
    }

    @ShellMethod(key = "get-priorities", value = "get all priorities")
    public String getAllPriorities() {
        Mono<String> response = priorityService.getAllPriorities();
        return response.block(); // Block to get the result (for simplicity in a shell command)
    }

    @ShellMethod(key = "get-priority-by-id", value = "get a priority by id")
    public String getPriorityById(Integer id) {
        Mono<String> response = priorityService.getPriorityById(id);
        return response.block(); // Block to get the result (for simplicity in a shell command)
    }

    @ShellMethod(key = "create-priority", value = "create a priority")
    public String createPriority(String description) {
        Mono<String> response = priorityService.createPriority(description);
        return response.block(); // Block to get the result (for simplicity in a shell command)
    }

    @ShellMethod(key = "add-feature", value = "add a feature")
    public String addFeature(@ShellOption(help = "Created by user ID", defaultValue = ShellOption.NULL) Integer createdBy,
                             @ShellOption(help = "Updated by user ID", defaultValue = ShellOption.NULL) Integer updatedBy,
                             @ShellOption(help = "Feature status by ID", defaultValue = ShellOption.NULL) Integer featureStatusID,
                             @ShellOption(help = "Priority ID", defaultValue = ShellOption.NULL) Integer priorityID,
                             @ShellOption(help = "Assigned to user ID", defaultValue = ShellOption.NULL) Integer assignedTo,
                             @ShellOption(help = "Feature name", defaultValue = ShellOption.NULL) String name,
                             @ShellOption(help = "Short description", defaultValue = ShellOption.NULL) String shortDescription,
                             @ShellOption(help = "Feature URL", defaultValue = ShellOption.NULL) String URL) {

        FeatureCreatedResponse createRequest = new FeatureCreatedResponse();
        if (createdBy != null) createRequest.setCreatedBy(createdBy);
        if (name != null) createRequest.setName(name);
        if (shortDescription != null) createRequest.setShortDescription(shortDescription);
        if (featureStatusID != null) createRequest.setFeatureStatusID(featureStatusID);
        if (priorityID != null) createRequest.setPriorityID(priorityID);
        if (assignedTo != null) createRequest.setAssignedTo(assignedTo);
        if (updatedBy != null) createRequest.setUpdatedBy(updatedBy);
        if (URL != null) createRequest.setURL(URL);

        try {
            Mono<String> response = featureService.createFeature(createRequest);
            return response.block();
        } catch (Exception e) {
            return "Invalid Input: " + e.getMessage();
        }

    }

    @ShellMethod(key= "delete-priority", value = "delete a priority")
    public String deletePriority(Integer id) {
        Mono<String> response = priorityService.deletePriority(id);
        return response.block(); // Block to get the result (for simplicity in a shell command)
    }


    @ShellMethod(key = "update-feature", value = "Update a feature")
    public String updateFeature(
            @ShellOption(help = "Feature ID") Long featureID,
            @ShellOption(help = "Feature name", defaultValue = ShellOption.NULL) String name,
            @ShellOption(help = "Short description", defaultValue = ShellOption.NULL) String shortDescription,
            @ShellOption(help = "Feature status ID", defaultValue = ShellOption.NULL) Integer featureStatusID,
            @ShellOption(help = "Priority ID", defaultValue = ShellOption.NULL) Integer priorityID,
            @ShellOption(help = "Assigned to user ID", defaultValue = ShellOption.NULL) Long assignedTo,
            @ShellOption(help = "Updated by user ID", defaultValue = ShellOption.NULL) Long updatedBy,
            @ShellOption(help = "Feature URL", defaultValue = ShellOption.NULL) String URL) {

        // Create the UpdateFeatureRequest object
        UpdateFeatureRequest updateRequest = new UpdateFeatureRequest();

        // Set fields only if they are provided (not null)
        if (featureID != null) updateRequest.setFeatureID(featureID);
        if (name != null) updateRequest.setName(name);
        if (shortDescription != null) updateRequest.setShortDescription(shortDescription);
        if (featureStatusID != null) updateRequest.setFeatureStatusID(featureStatusID);
        if (priorityID != null) updateRequest.setPriorityID(priorityID);
        if (assignedTo != null) updateRequest.setAssignedTo(assignedTo);
        if (updatedBy != null) updateRequest.setUpdatedBy(updatedBy);
        if (URL != null) updateRequest.setURL(URL);

        try {

            Mono<String> response = featureService.updateFeature(updateRequest);
            return response.block();
        } catch (Exception e) {
            return "Invalid Input: " + e.getMessage();
        }
    }


}
