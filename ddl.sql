SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;

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
    search_term varchar(255),
    primary key (id)
);

CREATE EXTENSION IF NOT EXISTS pg_trgm SCHEMA pg_catalog;

CREATE INDEX idx_search_term ON public.pessoas USING gin("search_term" gin_trgm_ops);

ALTER TABLE PUBLIC.pessoas OWNER TO dbaccess;