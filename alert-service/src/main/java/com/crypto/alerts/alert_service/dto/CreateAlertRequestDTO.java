package com.crypto.alerts.alert_service.dto;

import com.crypto.alerts.alert_service.entity.Alert;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class CreateAlertRequestDTO {

    @NotBlank
    private String userId;

    @NotBlank
    private String symbol;

    @NotNull
    private BigDecimal threshold;

    @NotNull
    private Alert.Direction direction;
}
