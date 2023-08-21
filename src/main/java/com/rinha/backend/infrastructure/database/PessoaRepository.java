package com.rinha.backend.infrastructure.database;

import io.micronaut.data.annotation.Query;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.CrudRepository;

import java.util.List;
import java.util.UUID;

@Repository
public interface PessoaRepository extends CrudRepository<PessoaEntity, UUID> {

    @Query(value = "SELECT * FROM pessoas p WHERE " +
            "LOWER(p.apelido) LIKE CONCAT('%', :term, '%') OR " +
            "LOWER(p.nome) LIKE CONCAT('%', :term, '%') OR " +
            "LOWER(p.stack) LIKE CONCAT('%', :term, '%')",
            nativeQuery = true)
    List<PessoaEntity> findByTerm(String term);
}
