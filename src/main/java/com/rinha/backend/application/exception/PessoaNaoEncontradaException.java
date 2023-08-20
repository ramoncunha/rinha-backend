package com.rinha.backend.application.exception;

import io.micronaut.http.HttpStatus;
import io.micronaut.http.exceptions.HttpStatusException;
import io.micronaut.problem.HttpStatusType;
import org.zalando.problem.Problem;

import java.net.URI;
import java.util.UUID;

public class PessoaNaoEncontradaException extends HttpStatusException {

    public PessoaNaoEncontradaException(UUID id) {
        super(HttpStatus.NOT_FOUND, Problem.builder()
                .withTitle("Pessoa não encontrada")
                .withDetail("Pessoa com ID " + id + " não encontrada")
                .withStatus(new HttpStatusType(HttpStatus.NOT_FOUND))
                .withType(URI.create("rinha:backend:erro:pessoa-nao-encontrada"))
                .build());
    }
}
