package com.crypto.alerts.price_service.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

    @Bean
    public WebClient cryptoWebClient(@Value("${crypto.api.base-url}") String baseUrl,
                                     @Value("${crypto.api.key}") String apiKey) {
        return WebClient.builder()
                .baseUrl(baseUrl)
                .defaultHeader("x-cg-demo-api-key", apiKey)
                .build();
    }
}

