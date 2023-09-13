package com.rinha.backend.infrastructure.database;

import com.rinha.backend.application.presentation.PessoaRequest;
import com.rinha.backend.infrastructure.database.model.tables.records.PessoasRecord;
import jakarta.inject.Singleton;
import org.jooq.Configuration;
import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.Result;

import java.util.*;

import static com.rinha.backend.infrastructure.database.model.Tables.PESSOAS;

@Singleton
public class PessoaRepository {

    private final DSLContext dslContext;

    public PessoaRepository(Configuration configuration) {
        this.dslContext = configuration.dsl();
    }

    public List<PessoasRecord> findByTerm(String term) {
        Result<Record> records = dslContext.select()
                .from(PESSOAS)
                .where(PESSOAS.SEARCH_TERM.likeIgnoreCase("%" + term + "%"))
                .limit(50)
                .fetch();

        if (records.isEmpty()) {
            return Collections.emptyList();
        }

        List<PessoasRecord> pessoas = new ArrayList<>();

        for (Record pessoaRecord: records) {
            PessoasRecord pessoa = new PessoasRecord();
            pessoa.setId(pessoaRecord.get(PESSOAS.ID));
            pessoa.setApelido(pessoaRecord.get(PESSOAS.APELIDO));
            pessoa.setNome(pessoaRecord.get(PESSOAS.NOME));
            pessoa.setNascimento(pessoaRecord.get(PESSOAS.NASCIMENTO));
            pessoa.setStack(pessoaRecord.get(PESSOAS.STACK));

            pessoas.add(pessoa);
        }

        return pessoas;
    }

    public String countAllRows() {
        return dslContext.selectCount().from(PESSOAS)
                .fetchOne()
                .into(String.class);
    }

    public PessoasRecord save(PessoaRequest pessoaRequest) {
        var pessoaRecord = new PessoasRecord();
        pessoaRecord.setId(UUID.randomUUID());
        pessoaRecord.setApelido(pessoaRequest.getApelido());
        pessoaRecord.setNome(pessoaRequest.getNome());
        pessoaRecord.setNascimento(pessoaRequest.getNascimento());

        String stack = convertToDatabaseColumn(pessoaRequest.getStack());
        pessoaRecord.setStack(stack);

        String searchTerm = pessoaRequest.getApelido()
                + ";" + pessoaRequest.getNome()
                + ";" + stack;
        pessoaRecord.setSearchTerm(searchTerm);

        dslContext.insertInto(PESSOAS).set(pessoaRecord).execute();

        return pessoaRecord;
    }

    public Optional<PessoasRecord> findById(UUID id) {
        PessoasRecord pessoaRecord = dslContext.fetchOne(PESSOAS, PESSOAS.ID.eq(id));
        return pessoaRecord == null ? Optional.empty() : Optional.of(pessoaRecord);
    }

    private String convertToDatabaseColumn(List<String> stringList) {
        return stringList != null ? String.join(";", stringList) : "";
    }
}
