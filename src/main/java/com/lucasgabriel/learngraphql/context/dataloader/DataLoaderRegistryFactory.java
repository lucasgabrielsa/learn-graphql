package com.lucasgabriel.learngraphql.context.dataloader;

import com.lucasgabriel.learngraphql.domain.bank.BankAccount;
import com.lucasgabriel.learngraphql.service.BalanceService;
import lombok.RequiredArgsConstructor;
import org.dataloader.BatchLoaderEnvironment;
import org.dataloader.DataLoader;
import org.dataloader.DataLoaderOptions;
import org.dataloader.DataLoaderRegistry;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

@Component
@RequiredArgsConstructor
public class DataLoaderRegistryFactory {

    private final BalanceService balanceService;

    public static final String BALANCE_DATA_LOADER = "BALANCE_DATA_LOADER";
    private static final Executor balanceThreadPool = Executors.newFixedThreadPool(
            Runtime.getRuntime().availableProcessors()
    );

    public DataLoaderRegistry create(String userId) {
        var registry = new DataLoaderRegistry();
        registry.register(BALANCE_DATA_LOADER, createBalanceDataLoader(userId));
        return registry;
    }

    private DataLoader<UUID, BigDecimal> createBalanceDataLoader(String userId) {
        return DataLoader.newMappedDataLoader((Set<UUID> bankAccountIds)
                -> CompletableFuture.supplyAsync(() -> balanceService.getBalanceFor(bankAccountIds, userId), balanceThreadPool));
    }

}
