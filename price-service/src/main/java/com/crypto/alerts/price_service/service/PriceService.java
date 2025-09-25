package com.crypto.alerts.price_service.service;

import com.crypto.alerts.price_service.client.CryptoApiClient;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class PriceService {

    private final CryptoApiClient cryptoApiClient;

    public PriceService(CryptoApiClient cryptoApiClient) {
        this.cryptoApiClient = cryptoApiClient;
    }

    public BigDecimal fetchPrice(String symbol) {
        return cryptoApiClient.getPrice(symbol);
    }
}

