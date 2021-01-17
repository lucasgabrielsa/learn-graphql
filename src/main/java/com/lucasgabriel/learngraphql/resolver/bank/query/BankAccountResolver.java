package com.lucasgabriel.learngraphql.resolver.bank.query;

import com.lucasgabriel.learngraphql.connection.CursorUtil;
import com.lucasgabriel.learngraphql.domain.bank.BankAccount;
import com.lucasgabriel.learngraphql.domain.bank.Currency;
import com.lucasgabriel.learngraphql.repository.BankAccountRepository;
import graphql.kickstart.tools.GraphQLQueryResolver;
import graphql.relay.*;
import graphql.schema.DataFetchingEnvironment;
import graphql.schema.SelectedField;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@Component
@RequiredArgsConstructor
public class BankAccountResolver implements GraphQLQueryResolver {

  private final BankAccountRepository bankAccountRepository;
  private final CursorUtil cursorUtil;

  public BankAccount bankAccount(UUID id, DataFetchingEnvironment e) {
    log.info("Retrieving bank account id {}", id);

    var fieldsRequested =
        e.getSelectionSet().getFields().stream()
            .map(SelectedField::getName)
            .collect(Collectors.toSet());

    log.info("Fields Requested {}", fieldsRequested);

    return BankAccount.builder().id(id).currency(Currency.USD).build();
  }

  public Connection<BankAccount> bankAccounts(int first, @Nullable String cursor) {

    List<Edge<BankAccount>> edges = getBankAccounts(cursor)
            .stream()
            .map(bankAccount -> new DefaultEdge<>(bankAccount, cursorUtil.createCursorFromUUID(bankAccount.getId())))
            .limit(first)
            .collect(Collectors.toUnmodifiableList());

    var firstCursor = cursorUtil.getFirstCursorFrom(edges);
    var lastCursor = cursorUtil.getLastCursorFrom(edges);

    var defaultPageInfo = new DefaultPageInfo(firstCursor, lastCursor, cursor != null, edges.size() >= first);
    return new DefaultConnection<>(edges, defaultPageInfo);
  }

  private List<BankAccount> getBankAccounts(String cursor) {
    return cursor == null ? bankAccountRepository.getBankAccounts() : bankAccountRepository.getBankAccountsAfter(cursorUtil.decodeCursorWith(cursor));
  }
}
