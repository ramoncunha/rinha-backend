# Rinha de Backend 

## Stack

- Java 17
- Micronaut
- PostgreSQL
- jOOQ
- NGINX

## Como executar

Para executar o projeto localmente execute o Docker

```
 docker-compose -f docker-compose-local.yaml up -d 
```

O container de banco de dados irá subir e você podera executar o projeto
localmente e fazer suas modificações.

Para testar as modificações com o docker-compose é necessário descomentar o trecho
`build: context: .` do `Dockerfile`. Dessa forma, o docker-compose irá utilizar o
código alterado.

No docker-compose também tem um `entrypoint: ["/app/rinha-api"]`, isso faz parte de uma tentativa 
de rodar a aplicação de forma nativa, ainda não consegui. Caso queira me ajudar, fique a vontade. 

Caso modifique a DDL e precise gerar as novas classes jOOQ, execute:

```
mvn clean compile -Pdbgen
```

## Código Fonte

[ramoncunha/rinha-backend](https://github.com/ramoncunha/rinha-backend)