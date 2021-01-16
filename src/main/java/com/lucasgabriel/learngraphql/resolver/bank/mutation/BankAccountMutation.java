package com.lucasgabriel.learngraphql.resolver.bank.mutation;

import com.lucasgabriel.learngraphql.domain.bank.BankAccount;
import com.lucasgabriel.learngraphql.domain.bank.Currency;
import com.lucasgabriel.learngraphql.domain.bank.input.CreateBankAccountInput;
import graphql.kickstart.tools.GraphQLMutationResolver;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@Slf4j
public class BankAccountMutation implements GraphQLMutationResolver {

    public BankAccount createBankAccount(CreateBankAccountInput createBankAccountInput) {
            log.info("Creating a bank account for {}", createBankAccountInput);
            return BankAccount.builder().id(UUID.randomUUID()).currency(Currency.USD).build();
    }
}
