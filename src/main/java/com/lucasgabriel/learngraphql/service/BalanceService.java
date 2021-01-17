package com.lucasgabriel.learngraphql.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

@Slf4j
@Service
public class BalanceService {
    public Map<UUID, BigDecimal> getBalanceFor(Set<UUID> bankAccountIds, String userId) {
        log.info("Requesting bank accounts ids: {}  - userId: {}", bankAccountIds, userId);
        return Map.of(UUID.fromString("48e4a484-af2c-4366-8cd4-25330597473f"), BigDecimal.ONE, UUID.fromString("024bb503-5c0f-4d60-aa44-db19d87042f4"), BigDecimal.TEN);
    }
}
