package com.rinha.backend.application;

import com.rinha.backend.application.mapper.PessoaResponseMapper;
import com.rinha.backend.application.presentation.PessoaRequest;
import com.rinha.backend.application.presentation.PessoaResponse;
import com.rinha.backend.infrastructure.database.PessoaEntity;
import io.micronaut.http.HttpHeaders;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.annotation.*;
import io.micronaut.validation.Validated;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.UUID;

@Validated
@Controller("/pessoas")
@RequiredArgsConstructor
public class PessoaController {

    private final PessoaService pessoaService;
    private final PessoaResponseMapper pessoaResponseMapper;

    @Post
    public HttpResponse<PessoaResponse> create(@Body @Valid PessoaRequest pessoaRequest) {
        PessoaEntity pessoa = pessoaService.save(pessoaRequest);
        PessoaResponse response = pessoaResponseMapper.map(pessoa);

        return HttpResponse.status(HttpStatus.CREATED)
                .header(HttpHeaders.LOCATION, "/pessoas/" + response.getId())
                .body(response);
    }

    @Get("/{id}")
    public HttpResponse<PessoaResponse> findById(@PathVariable UUID id) {
        PessoaEntity pessoa = pessoaService.findById(id);
        PessoaResponse response = pessoaResponseMapper.map(pessoa);

        return HttpResponse.ok(response);
    }

    @Get("{?term}")
    public HttpResponse<List<PessoaResponse>> findByTerm(@QueryValue String t) {
        List<PessoaEntity> pessoas = pessoaService.findByTerm(t);

        return HttpResponse.ok(pessoas.stream()
                .map(pessoaResponseMapper::map)
                .toList());
    }
}
