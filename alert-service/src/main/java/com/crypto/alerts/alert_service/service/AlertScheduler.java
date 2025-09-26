package com.crypto.alerts.alert_service.service;

import com.crypto.alerts.alert_service.client.PriceServiceClient;
import com.crypto.alerts.alert_service.entity.Alert;
import com.crypto.alerts.alert_service.repository.AlertRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
public class AlertScheduler {

    private static final Logger log = LoggerFactory.getLogger(AlertScheduler.class);

    private final AlertRepository alertRepository;
    private final PriceServiceClient priceServiceClient;

    public AlertScheduler(AlertRepository alertRepository, PriceServiceClient priceServiceClient) {
        this.alertRepository = alertRepository;
        this.priceServiceClient = priceServiceClient;
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

                alertRepository.delete(alert);

                // TODO: send notification to user
            }
        }
    }
}
