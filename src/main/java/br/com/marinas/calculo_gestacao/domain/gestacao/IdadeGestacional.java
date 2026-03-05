package br.com.marinas.calculo_gestacao.domain.gestacao;

import java.time.LocalDate;

public record IdadeGestacional(
        LocalDate dpp,
        int semanas,
        int dias,
        int trimestre
) {

    public IdadeGestacional(LocalDate dpp) {
        this(dpp, 0, 0, 0);
    }
}