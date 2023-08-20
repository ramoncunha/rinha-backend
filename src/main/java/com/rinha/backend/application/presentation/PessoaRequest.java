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

    @Size(max = 32, message = "Limite de 32 caracteres excedido")
    @NotBlank(message = "Apelido não pode ser nulo ou vazio")
    private String apelido;

    @Size(max = 100, message = "Limite de 100 caracteres excedido")
    @NotBlank(message = "Nome não pode ser nulo ou vazio" )
    @Pattern(regexp = "^[A-Za-z\\p{L}\\s!@#]+$", message = "Utilize apenas letras e caracter especial")
    private String nome;

    @NotNull(message = "Data de Nascimento não pode ser nula")
    private LocalDate nascimento;

    private List<
            @Pattern(regexp = "^[A-Za-z\\s!@#]+$", message = "Utilize apenas letras e caracter especial")
            @Size(max = 32, message = "Limite de 32 caracteres excedido")
            String> stack;
}
