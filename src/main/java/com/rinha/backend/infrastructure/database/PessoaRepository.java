package com.rinha.backend.infrastructure.database;

import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.CrudRepository;

import java.util.UUID;

@Repository
public interface PessoaRepository extends CrudRepository<PessoaEntity, UUID> {
}
