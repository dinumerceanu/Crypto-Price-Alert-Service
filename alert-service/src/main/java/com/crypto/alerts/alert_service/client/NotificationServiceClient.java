package com.crypto.alerts.alert_service.client;

import com.crypto.alerts.alert_service.dto.NotificationRequestDTO;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class NotificationServiceClient {

    private final WebClient webClient;

    public NotificationServiceClient(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("http://localhost:8084").build();
    }

    public void sendNotification(NotificationRequestDTO notificationRequestDTO) {

        webClient.post()
                .uri("/notifications/send")
                .bodyValue(notificationRequestDTO)
                .retrieve()
                .toBodilessEntity()
                .block();
    }
}
