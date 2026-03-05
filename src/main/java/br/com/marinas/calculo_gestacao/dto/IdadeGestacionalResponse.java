package br.com.marinas.calculo_gestacao.dto;

import br.com.marinas.calculo_gestacao.domain.gestacao.IdadeGestacional;

import java.time.LocalDate;

public record IdadeGestacionalResponse(
        LocalDate dpp,
        int semanas,
        int dias,
        int trimestre
) {
    public static IdadeGestacionalResponse from(IdadeGestacional ig) {
        return new IdadeGestacionalResponse(
                ig.dpp(),
                ig.semanas(),
                ig.dias(),
                ig.trimestre()
        );
    }
}