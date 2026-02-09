package br.com.marinas.calculo_gestacao.controller;

import br.com.marinas.calculo_gestacao.model.Gestacao;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import br.com.marinas.calculo_gestacao.service.GestacaoService;

@RestController
@RequestMapping("/v1")
public class GestacaoController {
    private GestacaoService gestacaoService;

    public GestacaoController(GestacaoService gestacaoService) {
        this.gestacaoService = gestacaoService;
    }

    @PostMapping("/gestacoes")
    @ResponseStatus(HttpStatus.CREATED)
    public Gestacao CriarGestacao(@RequestBody Gestacao gestacao) throws Exception {
        return gestacaoService.criarGestacao(gestacao);
    }

}
