package com.FeatureDocClient.FeatureDocCLI.commands;

import com.FeatureDocClient.FeatureDocCLI.services.FeatureService;
import com.FeatureDocClient.FeatureDocCLI.services.FeatureStatusService;
import com.FeatureDocClient.FeatureDocCLI.services.PriorityService;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import reactor.core.publisher.Mono;

@ShellComponent
public class FeatureCommands {

    private final FeatureService featureService;
    private final FeatureStatusService featureStatusService;
    private final PriorityService priorityService;

    public FeatureCommands(FeatureService featureService, FeatureStatusService featureStatusService, PriorityService priorityService) {
        this.featureService = featureService;
        this.featureStatusService = featureStatusService;
        this.priorityService = priorityService;
    }

    @ShellMethod(key = "get-feature-by-id", value = "get a feature by id")
    public String getFeatureById(Integer id) {
        Mono<String> response = featureService.getFeatureById(id);
        return response.block(); // Block to get the result (for simplicity in a shell command)
    }

    @ShellMethod(key = "get-feature-statuses", value = "get all features statuses")
    public String getFeatureStatuses() {
        Mono<String> response = featureStatusService.getAllFeatureStatuses();
        return response.block(); // Block to get the result (for simplicity in a shell command)
    }

    @ShellMethod(key = "get-feature-status-by-id", value = "get all features statuses")
    public String getFeatureStatusesByID(Integer id) {
        Mono<String> response = featureStatusService.getFeatureStatusById(id);
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

    @ShellMethod(key= "create-priority", value = "create a priority")
    public String createPriority(String description) {
        Mono<String> response = priorityService.createPriority(description);
        return response.block(); // Block to get the result (for simplicity in a shell command)
    }

    @ShellMethod(key= "delete-priority", value = "delete a priority")
    public String deletePriority(Integer id) {
        Mono<String> response = priorityService.deletePriority(id);
        return response.block(); // Block to get the result (for simplicity in a shell command)
    }





//    @ShellMethod(key = "delete-feature-status-by-id", value = "get all features statuses")
//    public String deleteFeatureStatusesByID(Integer id) {
//        Mono<String> response = featureStatusService.deleteFeatureStatusById(id);
//        return response.block(); // Block to get the result (for simplicity in a shell command)
//    }

}
