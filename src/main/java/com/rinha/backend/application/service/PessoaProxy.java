package com.rinha.backend.application.service;

import com.rinha.backend.application.presentation.PessoaRequest;
import com.rinha.backend.infrastructure.database.PessoaEntity;

import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class PessoaProxy {

    private static final ConcurrentHashMap<UUID, PessoaEntity> PESSOAS_QUEUE = new ConcurrentHashMap<>();

    public PessoaEntity save(PessoaRequest pessoaRequest) {
        UUID id = UUID.randomUUID();
        PessoaEntity pessoaEntity = PessoaEntity.builder()
                .id(id)
                .apelido(pessoaRequest.getApelido())
                .nome(pessoaRequest.getNome())
                .nascimento(pessoaRequest.getNascimento())
                .stack(pessoaRequest.getStack())
                .build();

        PESSOAS_QUEUE.put(id, pessoaEntity);

        return pessoaEntity;
    }
}
