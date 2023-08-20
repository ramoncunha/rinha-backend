package com.rinha.backend.application.exception;

import org.zalando.problem.Problem;

public class PessoaNaoEncontradaException extends RuntimeException implements Problem {

    public PessoaNaoEncontradaException() {
    }
}
