package br.com.marinas.calculo_gestacao.controller;

import br.com.marinas.calculo_gestacao.domain.gestacao.Gestacao;
import br.com.marinas.calculo_gestacao.dto.CalculoDumRequest;
import br.com.marinas.calculo_gestacao.dto.DppResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import br.com.marinas.calculo_gestacao.service.GestacaoService;

import java.time.LocalDate;

@RestController
@RequestMapping("/v1")
public class GestacaoController {
    private GestacaoService gestacaoService;

    public GestacaoController(GestacaoService gestacaoService) {
        this.gestacaoService = gestacaoService;
    }

    @PostMapping("/gestacoes")
    @ResponseStatus(HttpStatus.CREATED)
    public Gestacao criarGestacao(@RequestBody Gestacao gestacao) throws Exception {
        return gestacaoService.criarGestacao(gestacao);
    }

    @GetMapping("/gestacoes/calculo-idade-por-dum")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<DppResponse> calcular(@RequestParam CalculoDumRequest calculoDum) {
        return ResponseEntity.ok(gestacaoService.calcular(calculoDum.dum()));
    }

}
