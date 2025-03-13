package com.FeatureDocClient.FeatureDocCLI;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

@SpringBootApplication
public class FeatureDocCliApplication {

	public static void main(String[] args) {
		SpringApplication.run(FeatureDocCliApplication.class, args);
	}

}
