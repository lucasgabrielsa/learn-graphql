package com.lucasgabriel.learngraphql.domain.bank;

import lombok.Builder;
import lombok.Setter;

import java.util.UUID;

@Builder
@Setter
public class Asset {
    UUID id;
    String name;
    String description;
}
