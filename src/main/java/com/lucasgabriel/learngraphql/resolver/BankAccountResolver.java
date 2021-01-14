package com.lucasgabriel.learngraphql.resolver;

import com.lucasgabriel.learngraphql.domain.bank.BankAccount;
import com.lucasgabriel.learngraphql.domain.bank.Client;
import com.lucasgabriel.learngraphql.domain.bank.Currency;
import graphql.kickstart.tools.GraphQLQueryResolver;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.UUID;

@Slf4j
@Component
public class BankAccountResolver implements GraphQLQueryResolver {

    public BankAccount bankAccount(UUID id) {
      log.info("Retrieving bank account id {}", id);
      return BankAccount.builder().id(id).currency(Currency.USD)
              .client(Client
                        .builder()
                        .id(UUID.randomUUID())
                        .firstName("Lucas")
                        .middleNames(Arrays.asList("Gabriel","Simão"))
                        .lastName("Alves")
                        .build())
              .build();
    }

}
