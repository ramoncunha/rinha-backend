package com.rinha.backend.application.mapper;

import com.rinha.backend.application.presentation.PessoaResponse;
import com.rinha.backend.infrastructure.database.PessoaEntity;
import jakarta.inject.Singleton;

import java.util.List;

@Singleton
public class PessoaResponseMapper {

    public PessoaResponse map(PessoaEntity pessoaEntity) {
        return PessoaResponse.builder()
                .id(pessoaEntity.getId())
                .apelido(pessoaEntity.getApelido())
                .nome(pessoaEntity.getNome())
                .dataNascimento(pessoaEntity.getNascimento())
                .stack(pessoaEntity.getStack())
                .build();
    }

    public List<PessoaResponse> map(List<PessoaEntity> pessoasEntity) {
        return pessoasEntity.stream()
                .map(this::map)
                .toList();
    }
}
