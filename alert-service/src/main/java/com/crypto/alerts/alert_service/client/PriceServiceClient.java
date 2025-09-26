package com.crypto.alerts.alert_service.client;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.math.BigDecimal;

@Component
public class PriceServiceClient {

    private final WebClient webClient;

    public PriceServiceClient(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("http://localhost:8082").build();
    }

    public BigDecimal getPrice(String symbol) {
        return webClient.get()
                .uri("/prices/{symbol}", symbol)
                .retrieve()
                .bodyToMono(BigDecimal.class)
                .block();
    }
}
