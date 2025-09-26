package com.crypto.alerts.alert_service.service;

import com.crypto.alerts.alert_service.dto.CreateAlertRequestDTO;
import com.crypto.alerts.alert_service.entity.Alert;
import com.crypto.alerts.alert_service.repository.AlertRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class AlertService {

    private final AlertRepository alertRepository;

    public AlertService(AlertRepository alertRepository) {
        this.alertRepository = alertRepository;
    }

    public Alert createAlert(CreateAlertRequestDTO request) {
        Alert alert = new Alert();
        alert.setId(UUID.randomUUID().toString());
        alert.setUserId(request.getUserId());
        alert.setSymbol(request.getSymbol());
        alert.setThreshold(request.getThreshold());
        alert.setDirection(request.getDirection());

        return alertRepository.save(alert);
    }

    public List<Alert> getAlertsByUser(String userId) {
        return alertRepository.findByUserId(userId);
    }

    public List<Alert> getAlertsBySymbol(String symbol) {
        return alertRepository.findBySymbol(symbol.toUpperCase());
    }
}
