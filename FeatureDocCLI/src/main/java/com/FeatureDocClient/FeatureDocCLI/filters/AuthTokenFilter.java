package com.FeatureDocClient.FeatureDocCLI.filters;

import org.springframework.web.reactive.function.client.ClientRequest;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import org.springframework.web.reactive.function.client.ExchangeFunction;
import reactor.core.publisher.Mono;

public class AuthTokenFilter implements ExchangeFilterFunction {

    private final String authToken;

    public AuthTokenFilter(String authToken) {
        this.authToken = authToken;
    }

    @Override
    public Mono<ClientResponse> filter(ClientRequest request, ExchangeFunction next) {
        if (authToken != null && !authToken.isEmpty()) {
            request = ClientRequest.from(request)
                    .header("Authorization", "Bearer " + authToken)
                    .build();
        }
        return next.exchange(request);
    }
}
