package com.lucasgabriel.learngraphql.resolver.bank;

import com.lucasgabriel.learngraphql.domain.bank.BankAccount;
import com.lucasgabriel.learngraphql.domain.bank.Client;
import graphql.kickstart.tools.GraphQLResolver;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.UUID;

@Slf4j
@Component
public class ClientResolver implements GraphQLResolver<BankAccount> {

    public Client client(BankAccount bankAccount) {
        log.info("Requesting Client data for bank account id {}", bankAccount.getId());
        return Client
                .builder()
                .id(UUID.randomUUID())
                .firstName("Lucas")
                .middleNames(Arrays.asList("Gabriel","Simão"))
                .lastName("Alves1")
                .build();
    }

}
