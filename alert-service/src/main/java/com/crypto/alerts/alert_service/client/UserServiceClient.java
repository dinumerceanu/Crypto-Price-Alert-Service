package com.crypto.alerts.alert_service.client;

import com.crypto.alerts.alert_service.dto.UserResponseDTO;
import com.crypto.alerts.alert_service.service.JwtUtilService;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class UserServiceClient {

    private final WebClient webClient;

    public UserServiceClient(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("http://localhost:8081").build();
    }

    public UserResponseDTO getUser(String userId) {
        String token = JwtUtilService.generateToken();

        return webClient.get()
                .uri("/users/{userId}", userId)
                .header("Authorization", "Bearer " + token)
                .retrieve()
                .bodyToMono(UserResponseDTO.class)
                .block();
    }
}
