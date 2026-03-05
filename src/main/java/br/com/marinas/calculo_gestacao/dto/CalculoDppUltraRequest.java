package br.com.marinas.calculo_gestacao.dto;

import java.time.LocalDate;

public record CalculoDppUltraRequest(
        LocalDate dataUltra,
        int semanas,
        int dias
){
}
