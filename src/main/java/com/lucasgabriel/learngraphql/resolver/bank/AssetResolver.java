package com.lucasgabriel.learngraphql.resolver.bank;

import com.lucasgabriel.learngraphql.domain.bank.Asset;
import com.lucasgabriel.learngraphql.domain.bank.BankAccount;
import graphql.kickstart.tools.GraphQLResolver;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
@Component
public class AssetResolver implements GraphQLResolver<BankAccount> {

  private final ExecutorService executorService =
      Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

  public CompletableFuture<List<Asset>> assets(BankAccount bankAccount) {
    return CompletableFuture.supplyAsync(
        () -> {
          log.info("Requesting Asset data for bank account id {}", bankAccount.getId());
          return Collections.singletonList(
              Asset.builder()
                  .id(UUID.randomUUID())
                  .name("Asset Default")
                  .description("Default Asset description")
                  .build());
        },
        executorService);
  }
}
