package com.lucasgabriel.learngraphql.resolver.bank.query;

import com.lucasgabriel.learngraphql.domain.bank.BankAccount;
import com.lucasgabriel.learngraphql.domain.bank.Currency;
import graphql.kickstart.tools.GraphQLQueryResolver;
import graphql.schema.DataFetchingEnvironment;
import graphql.schema.SelectedField;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Slf4j
@Component
public class BankAccountResolver implements GraphQLQueryResolver {

    public BankAccount bankAccount(UUID id, DataFetchingEnvironment e) {
      log.info("Retrieving bank account id {}", id);

      e.getSelectionSet().getFields().stream().map(SelectedField::getName).forEach(System.out::println);

      return BankAccount.builder().id(id).currency(Currency.USD)
              .build();
    }

}
