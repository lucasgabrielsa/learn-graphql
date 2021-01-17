package com.lucasgabriel.learngraphql.resolver.bank;

import com.lucasgabriel.learngraphql.context.dataloader.DataLoaderRegistryFactory;
import com.lucasgabriel.learngraphql.domain.bank.BankAccount;
import graphql.kickstart.tools.GraphQLResolver;
import graphql.schema.DataFetchingEnvironment;
import lombok.extern.slf4j.Slf4j;
import org.dataloader.DataLoader;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Component
@Slf4j
public class BankAccountResolver implements GraphQLResolver<BankAccount> {
    public CompletableFuture<BigDecimal> balance(BankAccount bankAccount, DataFetchingEnvironment environment) {
        log.info("Getting balance for {}", bankAccount.getId());
        DataLoader<UUID, BigDecimal> dataLoader = environment.getDataLoader(DataLoaderRegistryFactory.BALANCE_DATA_LOADER);
        return dataLoader.load(bankAccount.getId());
    }
}
