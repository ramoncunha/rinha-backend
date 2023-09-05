package com.rinha.backend.application.mapper;

import com.rinha.backend.application.presentation.PessoaResponse;
import com.rinha.backend.infrastructure.database.model.tables.records.PessoasRecord;
import jakarta.inject.Singleton;

import java.util.Arrays;
import java.util.List;

@Singleton
public class PessoaResponseMapper {

    public PessoaResponse map(PessoasRecord pessoasRecord) {
        List<String> stack = databaseColumnToList(pessoasRecord.getStack());

        return PessoaResponse.builder()
                .id(pessoasRecord.getId())
                .apelido(pessoasRecord.getApelido())
                .nome(pessoasRecord.getNome())
                .dataNascimento(pessoasRecord.getNascimento())
                .stack(stack)
                .build();
    }

    public List<PessoaResponse> map(List<PessoasRecord> pessoasRecords) {
        return pessoasRecords.stream()
                .map(this::map)
                .toList();
    }

    private List<String> databaseColumnToList(String value) {
        return Arrays.asList(value.split(";"));
    }
}
