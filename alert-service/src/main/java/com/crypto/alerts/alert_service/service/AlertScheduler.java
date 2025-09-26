package com.crypto.alerts.alert_service.service;

import com.crypto.alerts.alert_service.client.PriceServiceClient;
import com.crypto.alerts.alert_service.client.UserServiceClient;
import com.crypto.alerts.alert_service.dto.UserResponseDTO;
import com.crypto.alerts.alert_service.entity.Alert;
import com.crypto.alerts.alert_service.repository.AlertRepository;
import com.crypto.alerts.alert_service.service.JwtUtilService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Component
public class AlertScheduler {

    private static final Logger log = LoggerFactory.getLogger(AlertScheduler.class);

    private final AlertRepository alertRepository;
    private final PriceServiceClient priceServiceClient;
    private final UserServiceClient userServiceClient;

    public AlertScheduler(AlertRepository alertRepository, PriceServiceClient priceServiceClient, UserServiceClient userServiceClient) {
        this.alertRepository = alertRepository;
        this.priceServiceClient = priceServiceClient;
        this.userServiceClient = userServiceClient;
    }

    @Scheduled(fixedRate = 5000)
    public void checkAlerts() {
        log.info("Running scheduled alert check...");

        List<Alert> alerts = alertRepository.findAll();
        if (alerts.isEmpty()) {
            log.info("No alerts found.");
            return;
        }

        for (Alert alert : alerts) {
            log.info("Checking alert " + alert);
            BigDecimal currentPrice = priceServiceClient.getPrice(alert.getSymbol());
            log.info("Current price: " + currentPrice);
            boolean triggered = false;

            switch (alert.getDirection()) {
                case ABOVE -> triggered = currentPrice.compareTo(alert.getThreshold()) > 0;
                case BELOW -> triggered = currentPrice.compareTo(alert.getThreshold()) < 0;
            }

            if (triggered) {
                log.info("ALERT TRIGGERED: User {} - {} {} {} (current price: {})",
                        alert.getUserId(),
                        alert.getSymbol(),
                        alert.getDirection(),
                        alert.getThreshold(),
                        currentPrice
                );

                UserResponseDTO user = userServiceClient.getUser(alert.getUserId());
                log.info("User email found: " + user.getEmail());

                alertRepository.delete(alert);

                // TODO: send notification to user
            }
        }
    }
}
