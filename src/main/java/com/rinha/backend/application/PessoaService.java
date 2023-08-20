package com.rinha.backend.application;

import com.rinha.backend.application.exception.PessoaNaoEncontradaException;
import com.rinha.backend.application.presentation.PessoaRequest;
import com.rinha.backend.infrastructure.database.PessoaEntity;
import com.rinha.backend.infrastructure.database.PessoaRepository;
import jakarta.inject.Singleton;
import lombok.RequiredArgsConstructor;

import java.util.Optional;
import java.util.UUID;

@Singleton
@RequiredArgsConstructor
public class PessoaService {

    private final PessoaRepository pessoaRepository;

    public PessoaEntity save(PessoaRequest pessoaRequest) {
        PessoaEntity pessoaEntity = PessoaEntity.builder()
                .apelido(pessoaRequest.getApelido())
                .nome(pessoaRequest.getNome())
                .nascimento(pessoaRequest.getNascimento())
                .stack(pessoaRequest.getStack())
                .build();
        return pessoaRepository.save(pessoaEntity);
    }

    public PessoaEntity findById(UUID id) {
        Optional<PessoaEntity> pessoaOptional = pessoaRepository.findById(id);
        if (pessoaOptional.isEmpty()) {
            throw new PessoaNaoEncontradaException();
        }
        return pessoaOptional.get();
    }
}
