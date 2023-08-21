package com.rinha.backend.application.exception;

import io.micronaut.http.HttpStatus;
import io.micronaut.http.exceptions.HttpStatusException;
import io.micronaut.problem.HttpStatusType;
import org.zalando.problem.Problem;

import java.net.URI;

@SuppressWarnings("java:S110")
public class ApelidoExistenteException extends HttpStatusException {

    public ApelidoExistenteException(String apelido) {
        super(HttpStatus.UNPROCESSABLE_ENTITY, Problem.builder()
                .withTitle("Apelido existente")
                .withDetail(String.format("Apelido %s já existe", apelido))
                .withStatus(new HttpStatusType(HttpStatus.UNPROCESSABLE_ENTITY))
                .withType(URI.create("rinha:backend:erro:apelido-existente"))
                .build());
    }
}
