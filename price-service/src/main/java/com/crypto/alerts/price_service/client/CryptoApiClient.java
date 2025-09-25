package com.crypto.alerts.price_service.client;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.math.BigDecimal;

@Component
public class CryptoApiClient {

    private final WebClient webClient;

    public CryptoApiClient(WebClient cryptoWebClient) {
        this.webClient = cryptoWebClient;
    }

    public BigDecimal getPrice(String symbol) {
        String endpoint = "/simple/price?ids=" + symbol + "&vs_currencies=usd";

        JsonNode jsonResp = webClient.get()
                .uri(endpoint)
                .retrieve()
                .bodyToMono(JsonNode.class)
                .block();

        if (jsonResp == null || jsonResp.get(symbol) == null || jsonResp.get(symbol).get("usd") == null) {
            throw new RuntimeException("Unexpected response: " + jsonResp);
        }

        return new BigDecimal(jsonResp.get(symbol).get("usd").asText());
    }
}

