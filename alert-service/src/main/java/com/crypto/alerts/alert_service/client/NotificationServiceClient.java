package com.crypto.alerts.alert_service.client;

import com.crypto.alerts.alert_service.dto.NotificationRequestDTO;
import com.crypto.alerts.alert_service.service.JwtUtilService;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class NotificationServiceClient {

    private final WebClient webClient;

    public NotificationServiceClient(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("http://localhost:8084").build();
    }

    public void sendNotification(NotificationRequestDTO notificationRequestDTO) {
//        String token = JwtUtilService.generateToken();

        webClient.post()
                .uri("/notifications/send")
//                .header("Authorization", "Bearer " + token)
                .bodyValue(notificationRequestDTO)
                .retrieve()
                .toBodilessEntity()
                .block();
    }
}
