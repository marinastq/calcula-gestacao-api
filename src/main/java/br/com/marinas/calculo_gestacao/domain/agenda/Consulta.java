package br.com.marinas.calculo_gestacao.domain.agenda;

import java.time.DayOfWeek;
import java.time.LocalDate;

public record Consulta(
        LocalDate data,
        int semana,
        String diaSemana) {
}
