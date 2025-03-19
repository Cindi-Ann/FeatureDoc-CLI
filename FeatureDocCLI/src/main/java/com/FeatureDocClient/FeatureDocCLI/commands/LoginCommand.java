package com.FeatureDocClient.FeatureDocCLI.commands;

import com.FeatureDocClient.FeatureDocCLI.GoogleOAuth2Config;
import com.FeatureDocClient.FeatureDocCLI.OAuthSocketServer;
import com.FeatureDocClient.FeatureDocCLI.services.RoleService;
import com.FeatureDocClient.FeatureDocCLI.services.UserService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.client.WebClient;

import java.io.IOException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

@ShellComponent
public class LoginCommand {

    private final WebClient webClient = WebClient.builder().defaultHeader("Accept", "application/x-www-form-urlencoded")
            .build();
    private UserService userService;

    public LoginCommand(UserService userService) {
        this.userService = userService;
    }

    private String accessToken;

    @ShellMethod(key = "login", value = "Perform login and open the browser for authentication")
    public void login() {
        String authUrl = GoogleOAuth2Config.AUTHORIZATION_URI +
                "?client_id=" + GoogleOAuth2Config.CLIENT_ID +
                "&redirect_uri=" + GoogleOAuth2Config.REDIRECT_URI +
                "&response_type=code" +
                "&scope=" + GoogleOAuth2Config.SCOPE +
                "&access_type=offline";
        System.out.println("Opening browser for Google login...");
        openBrowser(authUrl);
        System.out.println("If not redirected to login, please go to this link: " + authUrl);

        try {
            // Start the socket server and wait for the authorization code
            String authorizationCode = OAuthSocketServer.startAndWaitForCode(3000);
            // Exchange the auth code for a jwt from the server
            String requestBody = "{\"authCode\" : \"" + authorizationCode + "\"}";
            //TODO: return JWT
            //JWT = resposne from server
          //  userService.loginUser(requestBody);
            //TODO: move to server side
            accessToken = exchangeCodeForToken(authorizationCode);
            System.out.println("Access Token: " + accessToken);

        } catch (IOException e) {
            System.out.println("Error during login: " + e.getMessage());
        }
    }

    //TODO: Move to server side - user should send the auth code to the server and the server should generate a jwt token
    private String exchangeCodeForToken(String authorizationCode) {
        String decodedAuthCode = URLDecoder.decode(authorizationCode, StandardCharsets.UTF_8);
        MultiValueMap<String, String> requestBody = new LinkedMultiValueMap<>();
        requestBody.add("code", decodedAuthCode);
        requestBody.add("client_id", GoogleOAuth2Config.CLIENT_ID);
        requestBody.add("client_secret", GoogleOAuth2Config.CLIENT_SECRET);
        requestBody.add("redirect_uri", GoogleOAuth2Config.REDIRECT_URI);
        requestBody.add("grant_type", "authorization_code");

        // Send the POST request using WebClient
        Map<String, String> response = webClient.post()
                .uri(GoogleOAuth2Config.TOKEN_URI)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_FORM_URLENCODED_VALUE)
                .bodyValue(requestBody)
                .retrieve()
                .bodyToMono(Map.class)
                .block();

        // Extract and return the ID token
        return response.get("id_token");
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