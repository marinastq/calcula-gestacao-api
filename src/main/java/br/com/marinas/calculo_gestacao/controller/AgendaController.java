package br.com.marinas.calculo_gestacao.controller;

import br.com.marinas.calculo_gestacao.domain.agenda.Consulta;
import br.com.marinas.calculo_gestacao.dto.AgendaConsultasResponse;
import br.com.marinas.calculo_gestacao.dto.IdadeGestacionalResponse;
import br.com.marinas.calculo_gestacao.service.AgendaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/v1")
public class AgendaController {
    private AgendaService agendaService;

    public AgendaController(AgendaService agendaService) {
        this.agendaService = agendaService;
    }

    @GetMapping("/agenda-consultas")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<AgendaConsultasResponse>> geraAgendaConsultas(
            @RequestParam LocalDate dum,
            @RequestParam LocalDate dataInicio,
            @RequestParam LocalDate dataUltimaConsulta
            ) {

        List<Consulta> consultas = agendaService.gerarAgendaConsultas(dum, dataInicio, dataUltimaConsulta);

        return ResponseEntity.ok(consultas.stream()
                .map(AgendaConsultasResponse::from)
                .toList());
    }
}
