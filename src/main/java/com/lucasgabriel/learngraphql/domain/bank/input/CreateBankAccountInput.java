package com.lucasgabriel.learngraphql.domain.bank.input;

import lombok.Data;

@Data
public class CreateBankAccountInput {
  String firstName;
  int age;
}
