package com.rinha.backend.application.service;

import com.rinha.backend.application.exception.ApelidoExistenteException;
import com.rinha.backend.application.exception.PessoaNaoEncontradaException;
import com.rinha.backend.application.presentation.PessoaRequest;
import com.rinha.backend.infrastructure.database.PessoaRepository;
import com.rinha.backend.infrastructure.database.model.tables.records.PessoasRecord;
import jakarta.inject.Singleton;
import lombok.RequiredArgsConstructor;
import org.jooq.exception.IntegrityConstraintViolationException;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Singleton
@RequiredArgsConstructor
public class PessoaService {

    private final PessoaRepository pessoaRepository;

    public PessoasRecord save(PessoaRequest pessoaRequest) {
        try {
            return pessoaRepository.save(pessoaRequest);
        }  catch (IntegrityConstraintViolationException ex) {
            throw new ApelidoExistenteException(pessoaRequest.getApelido());
        }
    }

    public PessoasRecord findById(UUID id) {
        Optional<PessoasRecord> pessoaOptional = pessoaRepository.findById(id);
        if (pessoaOptional.isEmpty()) {
            throw new PessoaNaoEncontradaException(id);
        }
        return pessoaOptional.get();
    }

    public List<PessoasRecord> findByTerm(String term) {
        return pessoaRepository.findByTerm(term);
    }

    public String populacao() {
        return pessoaRepository.countAllRows();
    }
}
