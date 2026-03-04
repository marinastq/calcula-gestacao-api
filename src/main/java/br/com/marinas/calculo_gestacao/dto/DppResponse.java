package br.com.marinas.calculo_gestacao.dto;

import java.time.LocalDate;

public record DppResponse(
        LocalDate dum,
        LocalDate dpp,
        int semanas,
        int dias,
        int trimestre
) {
}