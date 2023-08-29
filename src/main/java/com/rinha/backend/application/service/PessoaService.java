package com.rinha.backend.application.service;

import com.rinha.backend.application.exception.ApelidoExistenteException;
import com.rinha.backend.application.exception.PessoaNaoEncontradaException;
import com.rinha.backend.application.presentation.PessoaRequest;
import com.rinha.backend.infrastructure.database.PessoaEntity;
import com.rinha.backend.infrastructure.database.PessoaRepository;
import jakarta.inject.Singleton;
import lombok.RequiredArgsConstructor;
import org.hibernate.exception.ConstraintViolationException;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Singleton
@RequiredArgsConstructor
public class PessoaService {

    private final PessoaRepository pessoaRepository;

    public PessoaEntity save(PessoaRequest pessoaRequest) {
        UUID id = UUID.randomUUID();
        PessoaEntity pessoaEntity = PessoaEntity.builder()
                .id(id)
                .apelido(pessoaRequest.getApelido())
                .nome(pessoaRequest.getNome())
                .nascimento(pessoaRequest.getNascimento())
                .stack(pessoaRequest.getStack())
                .build();
        try {
            return pessoaRepository.save(pessoaEntity);
        }  catch (ConstraintViolationException ex) {
            throw new ApelidoExistenteException(pessoaEntity.getApelido());
        }
    }

    public PessoaEntity findById(UUID id) {
        Optional<PessoaEntity> pessoaOptional = pessoaRepository.findById(id);
        if (pessoaOptional.isEmpty()) {
            throw new PessoaNaoEncontradaException(id);
        }
        return pessoaOptional.get();
    }

    public List<PessoaEntity> findByTerm(String term) {
        return pessoaRepository.findByTerm(term);
    }

    public String populacao() {
        return pessoaRepository.countAllRows();
    }
}
