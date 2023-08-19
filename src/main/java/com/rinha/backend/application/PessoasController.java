package com.rinha.backend.application;

import com.rinha.backend.application.presentation.PessoaRequest;
import com.rinha.backend.application.presentation.PessoaResponse;
import com.rinha.backend.infrastructure.PessoaEntity;
import com.rinha.backend.infrastructure.PessoaRepository;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.*;
import io.micronaut.validation.Validated;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import java.util.Optional;
import java.util.UUID;

@Validated
@Controller("/pessoas")
@RequiredArgsConstructor
public class PessoasController {

    private final PessoaRepository pessoaRepository;

    @Post
    public HttpResponse<PessoaResponse> create(@Body @Valid PessoaRequest pessoaRequest) {
        PessoaEntity pessoaEntity = PessoaEntity.builder()
                .apelido(pessoaRequest.getApelido())
                .nome(pessoaRequest.getNome())
                .nascimento(pessoaRequest.getDataNascimento())
                .stack(pessoaRequest.getStack())
                .build();
        PessoaEntity pessoaSalva = pessoaRepository.save(pessoaEntity);

        PessoaResponse response = PessoaResponse.builder()
                .id(pessoaSalva.getId())
                .apelido(pessoaSalva.getApelido())
                .nome(pessoaSalva.getNome())
                .dataNascimento(pessoaSalva.getNascimento())
                .stack(pessoaSalva.getStack())
                .build();

        return HttpResponse.created(response);
    }

    @Get("/{id}")
    public HttpResponse<PessoaResponse> findById(@PathVariable UUID id) {
        Optional<PessoaEntity> pessoaOptional = pessoaRepository.findById(id);
        if (pessoaOptional.isEmpty()) {
            throw new RuntimeException("not found");
        }

        PessoaEntity pessoa = pessoaOptional.get();

        PessoaResponse response = PessoaResponse.builder()
                .id(pessoa.getId())
                .apelido(pessoa.getApelido())
                .nome(pessoa.getNome())
                .dataNascimento(pessoa.getNascimento())
                .stack(pessoa.getStack())
                .build();

        return HttpResponse.ok(response);
    }
}
