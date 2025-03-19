package com.FeatureDocClient.FeatureDocCLI.commands;

import com.FeatureDocClient.FeatureDocCLI.model.model.FeatureCreatedResponse;
import com.FeatureDocClient.FeatureDocCLI.model.model.FeatureResponse;
import com.FeatureDocClient.FeatureDocCLI.services.FeatureService;
import com.FeatureDocClient.FeatureDocCLI.services.FeatureStatusService;
import com.FeatureDocClient.FeatureDocCLI.services.PriorityService;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import reactor.core.publisher.Mono;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.nio.file.Files;
import java.nio.file.Paths;

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
    public String getFeatureHistoryById(Integer id){
        Mono<String> response = featureService.getFeatureHistoryById(id);
        return response.block(); // Block to get the result (for simplicity in a shell command)
    }

    @ShellMethod(key = "get-latest-feature-version", value = "get a feature's latest version by id")
    public String getLatestFeatureVersionById(Integer id){
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

    @ShellMethod(key= "create-priority", value = "create a priority")
    public String createPriority(String description) {
        Mono<String> response = priorityService.createPriority(description);
        return response.block(); // Block to get the result (for simplicity in a shell command)
    }

    @ShellMethod(key= "add-feature", value = "add a feature")
    public String addFeature(@ShellOption(value = "--args") String args) {
        StringBuilder result = new StringBuilder();
        try {
                String[] columns = args.split(",");
                if (columns.length != 8) {
                    return "Invalid CSV format.";
                }
                FeatureCreatedResponse featureResponse = new FeatureCreatedResponse();
                featureResponse.setCreatedBy(Integer.parseInt(columns[0].trim()));
                featureResponse.setUpdatedBy(Integer.parseInt(columns[1].trim()));
                featureResponse.setFeatureStatusID(Integer.parseInt(columns[2].trim()));
                featureResponse.setPriorityID(Integer.parseInt(columns[3].trim()));
                featureResponse.setAssignedTo(Integer.parseInt(columns[4].trim()));
                featureResponse.setName(columns[5].trim());
                featureResponse.setShortDescription(columns[6].trim());
                featureResponse.setURL(columns[7].trim());

                Mono<String> response = featureService.createFeature(featureResponse);
                return response.block(); // Block to get the result (for simplicity in a shell command)

        } catch (Exception e) {
            e.printStackTrace();
            return "Error processing CSV: " + e.getMessage();
        }
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
