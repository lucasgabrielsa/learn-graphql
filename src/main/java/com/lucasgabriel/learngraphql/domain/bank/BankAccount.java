package com.lucasgabriel.learngraphql.domain.bank;

import lombok.Builder;
import lombok.Setter;
import lombok.Value;

import java.util.UUID;

@Builder
@Setter
public class BankAccount {
    UUID id;
    Client client;
    Currency currency;
}
