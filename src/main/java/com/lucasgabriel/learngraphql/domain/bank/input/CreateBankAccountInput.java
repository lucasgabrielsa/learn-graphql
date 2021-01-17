package com.lucasgabriel.learngraphql.domain.bank.input;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class CreateBankAccountInput {
  @NotBlank(message = "firstName is required!")
  String firstName;
  int age;
}
