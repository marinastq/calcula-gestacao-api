package br.com.marinas.calculo_gestacao;

import br.com.marinas.calculo_gestacao.domain.agenda.Consulta;
import br.com.marinas.calculo_gestacao.service.AgendaService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class AgendaServiceTest {

    @InjectMocks
    AgendaService agendaService;

    @Test
    @DisplayName("deve gerar agenda de consultas com sucesso")
    void deveGerarAgendaConsultasComSucesso(){
        LocalDate dum = LocalDate.of(2025,11,8);
        LocalDate dataInicio = LocalDate.of(2026, 4, 19);
        LocalDate dataUltimaConsulta = LocalDate.of(2026, 3, 9);

        List<Consulta> agendaConsultasList =
                agendaService.gerarAgendaConsultas(dum, dataInicio, dataUltimaConsulta);

        Consulta primeiro = agendaConsultasList.get(0);

        assertEquals(LocalDate.of(2026, 4, 9), primeiro.data());
        assertEquals(23, primeiro.semana());
        assertEquals("sábado", primeiro.diaSemana());
    }
}
