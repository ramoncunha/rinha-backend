package com.rinha.backend.application.presentation;

import io.micronaut.serde.annotation.Serdeable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Serdeable
@Getter
@Setter
public class PessoaRequest {

    @NotBlank
    private String apelido;
    @NotBlank @Pattern(regexp = "/^([^0-9]*)$/")
    private String nome;
    @NotNull
    private LocalDate nascimento;
    private List<@Pattern(regexp = "/^([^0-9]*)$/") @Size(max = 32) String> stack;
}
