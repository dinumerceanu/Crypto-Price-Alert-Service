package com.crypto.alerts.alert_service.repository;

import com.crypto.alerts.alert_service.entity.Alert;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AlertRepository extends JpaRepository<Alert, String> {
    List<Alert> findByUserId(String userId);
    List<Alert> findBySymbol(String symbol);
}
