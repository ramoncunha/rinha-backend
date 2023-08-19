package com.rinha.backend.application.presentation;

import io.micronaut.serde.annotation.Serdeable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Serdeable
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PessoaResponse {

    private UUID id;
    private String apelido;
    private String nome;
    private LocalDate dataNascimento;
    private List<String> stack;
}
