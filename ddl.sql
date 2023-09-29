SET client_encoding = 'UTF8';
SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', 'public', false); -- Defina o esquema como 'public' aqui
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

ALTER SCHEMA PUBLIC OWNER TO dbaccess;

SET default_tablespace = '';

SET default_table_access_method = heap;

DROP TABLE IF EXISTS public.pessoas;

CREATE TABLE public.pessoas (
    id uuid not null,
    apelido varchar(32) unique not null,
    nascimento date not null,
    nome varchar(100) not null,
    stack varchar(255),
    search_term text,
    primary key (id)
);

CREATE EXTENSION IF NOT EXISTS pg_trgm SCHEMA pg_catalog;

-- Defina o esquema 'public' novamente antes de criar o índice
SELECT pg_catalog.set_config('search_path', 'public', false);

CREATE INDEX CONCURRENTLY IF NOT EXISTS idx_search_term ON public.pessoas USING gist("search_term" gist_trgm_ops);

ALTER TABLE PUBLIC.pessoas OWNER TO dbaccess;