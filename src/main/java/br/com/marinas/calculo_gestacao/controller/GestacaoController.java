package br.com.marinas.calculo_gestacao.controller;

import br.com.marinas.calculo_gestacao.domain.gestacao.Gestacao;
import br.com.marinas.calculo_gestacao.domain.gestacao.IdadeGestacional;
import br.com.marinas.calculo_gestacao.dto.CalculoDppUltraRequest;
import br.com.marinas.calculo_gestacao.dto.IdadeGestacionalResponse;
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

    @GetMapping("/gestacoes/calculo-ddp-por-dum")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<IdadeGestacionalResponse> calcularDppPorDum(@RequestParam LocalDate dum) {
        IdadeGestacional idadeGestacional = gestacaoService.calcularDppPorDum(dum);

        return ResponseEntity.ok(IdadeGestacionalResponse.from(idadeGestacional));
    }

    @GetMapping("/gestacoes/calculo-ddp-por-ultra")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<IdadeGestacionalResponse> calcularDppPorUltra(@ModelAttribute CalculoDppUltraRequest calculoUltra) {
        IdadeGestacional idadeGestacional = gestacaoService.calcularDppPorUltra(
                calculoUltra.dataUltra(),
                calculoUltra.semanas(),
                calculoUltra.dias());

        return ResponseEntity.ok(IdadeGestacionalResponse.from(idadeGestacional));

    }

}
