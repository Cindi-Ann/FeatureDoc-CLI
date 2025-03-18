package com.FeatureDocClient.FeatureDocCLI;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;

import java.awt.*;
import java.io.IOException;
import java.net.URI;

@Configuration
public class WebClientConfig {

    @Bean
    public WebClient webClient() {

        HttpClient httpClient = HttpClient.create()
                .baseUrl("http://localhost:8080")
                .followRedirect(true)
                .doOnRedirect((httpClientResponse, connection) -> {
                    System.out.println(httpClientResponse.status());
                    if (httpClientResponse.responseHeaders().getAsString("Location").contains("accounts.google.com")) {

                        if (Desktop.isDesktopSupported()) {

                            Desktop desktop = Desktop.getDesktop();
                            try {
                                desktop.browse(URI.create(httpClientResponse.responseHeaders().getAsString("Location")));
                            } catch (IOException e) {
                                System.out.println(e.getMessage());;
                            }

                        }
                    }
                    System.out.println(httpClientResponse.responseHeaders().entries());
                })
                ;

        return WebClient.builder()
                .clientConnector(new ReactorClientHttpConnector(httpClient))
                .defaultHeader("Accept", "text/html")
                .build();
    }
}