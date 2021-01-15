package com.lucasgabriel.learngraphql.resolver.bank;

import com.lucasgabriel.learngraphql.domain.bank.BankAccount;
import com.lucasgabriel.learngraphql.domain.bank.Client;
import graphql.GraphQLError;
import graphql.GraphQLException;
import graphql.execution.DataFetcherResult;
import graphql.kickstart.execution.error.GenericGraphQLError;
import graphql.kickstart.tools.GraphQLResolver;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.UUID;

@Slf4j
@Component
public class ClientResolver implements GraphQLResolver<BankAccount> {

    public DataFetcherResult<Client> client(BankAccount bankAccount) {
        log.info("Requesting Client data for bank account id {}", bankAccount.getId());

//        throw new GraphQLException("Client Unavailable");
//        throw new RuntimeException("Client Unavailable");

        return DataFetcherResult.<Client>newResult()
                .data(Client.builder()
                .id(UUID.randomUUID())
                .firstName("Lucas")
                .middleNames(Arrays.asList("Gabriel","Simão"))
                .lastName("Alves1").build())
                .error(new GenericGraphQLError("could not get subclient id")).build();
    }

}
