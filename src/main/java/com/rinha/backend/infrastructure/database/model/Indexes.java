/*
 * This file is generated by jOOQ.
 */
package com.rinha.backend.infrastructure.database.model;


import com.rinha.backend.infrastructure.database.model.tables.Pessoas;

import org.jooq.Index;
import org.jooq.OrderField;
import org.jooq.impl.DSL;
import org.jooq.impl.Internal;


/**
 * A class modelling indexes of tables in public.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Indexes {

    // -------------------------------------------------------------------------
    // INDEX definitions
    // -------------------------------------------------------------------------

    public static final Index IDX_PESSOAS_APELIDO = Internal.createIndex(DSL.name("idx_pessoas_apelido"), Pessoas.PESSOAS, new OrderField[] { Pessoas.PESSOAS.APELIDO }, false);
    public static final Index IDX_PESSOAS_NOME = Internal.createIndex(DSL.name("idx_pessoas_nome"), Pessoas.PESSOAS, new OrderField[] { Pessoas.PESSOAS.NOME }, false);
}
