package com.lucasgabriel.learngraphql.resolver.bank.mutation;

import com.lucasgabriel.learngraphql.domain.bank.BankAccount;
import com.lucasgabriel.learngraphql.domain.bank.Currency;
import com.lucasgabriel.learngraphql.domain.bank.input.CreateBankAccountInput;
import graphql.kickstart.tools.GraphQLMutationResolver;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.time.Clock;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.UUID;

@Component
@Slf4j
@RequiredArgsConstructor
@Validated
public class BankAccountMutation implements GraphQLMutationResolver {

  private final Clock clock;

  public BankAccount createBankAccount(@Valid CreateBankAccountInput createBankAccountInput) {
    log.info("Creating a bank account for {}", createBankAccountInput);
    return BankAccount.builder().id(UUID.randomUUID())
            .createdAt(ZonedDateTime.now(clock))
            .createdOn(LocalDate.now(clock))
            .currency(Currency.USD).build();
  }
}
