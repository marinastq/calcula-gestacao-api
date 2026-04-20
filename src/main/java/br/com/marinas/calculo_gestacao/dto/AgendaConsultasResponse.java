package br.com.marinas.calculo_gestacao.dto;

import br.com.marinas.calculo_gestacao.domain.agenda.Consulta;

import java.time.LocalDate;
import java.util.List;

public record AgendaConsultasResponse(
        LocalDate data,
        int semana,
        String diaSemana
) {
    public static AgendaConsultasResponse from(Consulta consulta) {
        return new AgendaConsultasResponse(consulta.data(),
                consulta.semana(),
                consulta.diaSemana());

    }
}
