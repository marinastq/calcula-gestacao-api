package br.com.marinas.calculo_gestacao.service;

import br.com.marinas.calculo_gestacao.domain.agenda.Consulta;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Service
public class AgendaService {

    public List<Consulta> gerarAgendaConsultas(LocalDate dum, LocalDate dataInicio, LocalDate dataUltimaConsulta){
        if(dataInicio == null){
            dataInicio = LocalDate.now();
        }
        if(dataUltimaConsulta == null){
            dataUltimaConsulta = LocalDate.now();
        }

        //se dataUltimaConsulta > dataInicio
        //erro

        //se dum > dataInicio
        //erro

        List<Consulta> consultas = new ArrayList<>();
        DayOfWeek dayOfWeek = dum.getDayOfWeek();
        String diaVirada = dayOfWeek.getDisplayName(TextStyle.FULL, new Locale("pt", "BR"));


        long totalDias = ChronoUnit.DAYS.between(dum, dataInicio);
        int semana = (int) (totalDias / 7);

        LocalDate dataConsulta = dataUltimaConsulta;

        while(semana < 42){
            if (semana < 28) {
                dataConsulta = dataConsulta.plusMonths(1);
                semana = semana + 4;
            } else if (semana < 36) {
                dataConsulta = dataConsulta.plusDays(15);
                semana = semana + 2;
            } else {
                dataConsulta = dataConsulta.plusDays(7);
                semana = semana + 1;
            }

            consultas.add(new Consulta(dataConsulta, semana, diaVirada));

        }

        return  consultas;
    }
}
