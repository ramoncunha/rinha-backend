package com.rinha.backend.application.exception;

import io.micronaut.context.annotation.Replaces;
import io.micronaut.core.annotation.NonNull;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.server.exceptions.ExceptionHandler;
import io.micronaut.problem.HttpStatusType;
import io.micronaut.problem.violations.ConstraintViolationThrowableProblem;
import io.micronaut.problem.violations.ProblemConstraintViolationExceptionHandler;
import io.micronaut.problem.violations.Violation;
import jakarta.inject.Singleton;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;

import java.net.URI;
import java.util.List;

@Singleton
@Replaces(ProblemConstraintViolationExceptionHandler.class)
public class CustomConstraintExceptionHandler implements ExceptionHandler<ConstraintViolationException, HttpResponse<?>> {

    @Override
    public HttpResponse<?> handle(HttpRequest request, ConstraintViolationException exception) {
        var status = HttpStatus.UNPROCESSABLE_ENTITY;

        for (ConstraintViolation<?> constraintViolation : exception.getConstraintViolations()) {
            if (constraintViolation.getMessage().contains("must match")) {
                status = HttpStatus.BAD_REQUEST;
            }
        }
        List<Violation> violations = exception.getConstraintViolations()
                .stream()
                .map(this::createViolation)
                .toList();
        ConstraintViolationThrowableProblem problem = new ConstraintViolationThrowableProblem(URI.create("rinha:backend:erro:validacao-dados"),
                new HttpStatusType(status),
                violations);

        return HttpResponse.status(status).body(problem);
    }

    private Violation createViolation(@NonNull ConstraintViolation<?> constraintViolation) {
        return new Violation(constraintViolation.getPropertyPath().toString(), constraintViolation.getMessage());
    }
}
