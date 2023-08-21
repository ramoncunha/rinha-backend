package com.rinha.backend.infrastructure.database;

import io.micronaut.data.annotation.Query;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.CrudRepository;

import java.util.List;
import java.util.UUID;

@Repository
public interface PessoaRepository extends CrudRepository<PessoaEntity, UUID> {

    @Query("FROM PessoaEntity p WHERE p.apelido LIKE :term OR p.nome LIKE :term OR p.stack LIKE :term")
    List<PessoaEntity> findByTerm(String term);
}
