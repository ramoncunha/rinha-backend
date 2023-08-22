package com.rinha.backend.application;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller("/contagem-pessoas")
public class ContagemPessoasController {

    private final PessoaService pessoaService;

    @Get
    public HttpResponse<String> getPopulacao() {
        String populacao = pessoaService.populacao();
        return HttpResponse.ok(populacao);
    }
}
