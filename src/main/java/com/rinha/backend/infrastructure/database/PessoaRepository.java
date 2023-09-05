package com.rinha.backend.infrastructure.database;

import jakarta.inject.Singleton;
import lombok.RequiredArgsConstructor;
import org.jooq.Configuration;
import org.jooq.DSLContext;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Singleton
public class PessoaRepository {

    private final DSLContext dslContext;

    public PessoaRepository(Configuration configuration) {
        this.dslContext = configuration.dsl();
    }

    //    @Query(value = "SELECT * FROM pessoas p WHERE " +
//            "LOWER(p.apelido) LIKE CONCAT('%', :term, '%') OR " +
//            "LOWER(p.nome) LIKE CONCAT('%', :term, '%') OR " +
//            "LOWER(p.stack) LIKE CONCAT('%', :term, '%') " +
//            "LIMIT 50",
//            nativeQuery = true)
    public List<PessoaEntity> findByTerm(String term) {
//        dslContext.sele
        return Collections.emptyList();
    }

//    @Query(value = "SELECT COUNT(*) FROM pessoas", nativeQuery = true)
    public String countAllRows() {
        return "100";
    }

    public PessoaEntity save(PessoaEntity pessoaEntity) {
        return null;
    }

    public Optional<PessoaEntity> findById(UUID id) {
        return Optional.empty();
    }
}
