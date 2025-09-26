package com.crypto.alerts.alert_service.controller;

import com.crypto.alerts.alert_service.dto.CreateAlertRequestDTO;
import com.crypto.alerts.alert_service.entity.Alert;
import com.crypto.alerts.alert_service.service.AlertService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/alerts")
public class AlertController {

    private final AlertService alertService;

    public AlertController(AlertService alertService) {
        this.alertService = alertService;
    }

    @PostMapping
    public ResponseEntity<Alert> createAlert(@Valid @RequestBody CreateAlertRequestDTO request) {
        Alert alert = alertService.createAlert(request);
        return ResponseEntity.ok(alert);
    }

    @GetMapping
    public ResponseEntity<List<Alert>> getAlerts(@RequestParam String userId) {
        return ResponseEntity.ok(alertService.getAlertsByUser(userId));
    }
}
