package com.rinha.backend.application;

import com.rinha.backend.domain.Pessoa;
import com.rinha.backend.infrastructure.PessoaEntity;
import com.rinha.backend.infrastructure.PessoaRepository;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Post;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;

@Controller("/pessoas")
@RequiredArgsConstructor
public class PessoasController {

    private final PessoaRepository pessoaRepository;

    @Post
    public HttpResponse<Pessoa> criarPessoa() {
        PessoaEntity pessoaEntity = PessoaEntity.builder()
                .apelido("Ze")
                .nome("John Doe")
                .nascimento(LocalDate.of(2000, 7,1))
                .build();
        PessoaEntity pessoaSalva = pessoaRepository.save(pessoaEntity);
        return null;
    }
}
