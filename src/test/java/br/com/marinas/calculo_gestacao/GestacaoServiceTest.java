package br.com.marinas.calculo_gestacao;

import br.com.marinas.calculo_gestacao.domain.gestacao.IdadeGestacional;
import br.com.marinas.calculo_gestacao.exception.GestacaoNaoEncontradaException;
import br.com.marinas.calculo_gestacao.domain.gestacao.Classificacao;
import br.com.marinas.calculo_gestacao.domain.gestacao.Gestacao;
import br.com.marinas.calculo_gestacao.infrastructure.GestacaoRepositoryJpa;
import br.com.marinas.calculo_gestacao.service.GestacaoService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;


@ExtendWith(MockitoExtension.class)
public class GestacaoServiceTest {
    @Mock
    private GestacaoRepositoryJpa gestacaoRepository;

    @InjectMocks
    private GestacaoService gestacaoService;

    @Test
    @DisplayName("deve salvar a gestacao com sucesso")
    void deveSalvarGestacaoComSucesso(){
        Gestacao gestacao = buildMockGestacao();

        this.gestacaoService.criarGestacao(gestacao);

        verify(this.gestacaoRepository).save(gestacao);
    }

    @Test
    @DisplayName("deve listar a gestacao por id com sucesso")
    void deveListarGestacaoPeloIdComSucesso() throws GestacaoNaoEncontradaException {
        Gestacao gestacao = buildMockGestacao();

        when(gestacaoRepository.findById(1L)).thenReturn(Optional.of(gestacao));

        Gestacao gestacaoEncontrada = this.gestacaoService.buscarGestacaoPeloId(1L);

        assertEquals(LocalDate.of(2024, 12, 28), gestacaoEncontrada.getDataDUM());
        verify(this.gestacaoRepository).findById(1L);
    }

    @Test
    @DisplayName("deve excluir a gestacao por id com sucesso")
    void deveExcluirGestacaoPeloIdComSucesso() throws GestacaoNaoEncontradaException {
        Gestacao gestacao = buildMockGestacao();

        when(gestacaoRepository.findById(1L)).thenReturn(Optional.of(gestacao));

        this.gestacaoService.excluirGestacao(1L);

        verify(this.gestacaoRepository).deleteById(1L);
    }

    @Test
    @DisplayName("deve atualizar a gestacao por id com sucesso")
    void deveAtualizarGestacaoPeloIdComSucesso() throws GestacaoNaoEncontradaException {
        Gestacao gestacao = buildMockGestacao();

        Gestacao gestacaoDadosNovos = new Gestacao(
                "Fulana",
                LocalDate.parse("2024-12-26"),
                Classificacao.GESTACAO_NORMAL);

        when(gestacaoRepository.findById(1L)).thenReturn(Optional.of(gestacao));

        when(gestacaoRepository.save(any(Gestacao.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));


        Gestacao gestacaoAlterada = this.gestacaoService.alterarGestacao(1L, gestacaoDadosNovos);

        assertEquals("2024-12-26", gestacaoAlterada.getDataDUM().toString());
        verify(this.gestacaoRepository).findById(1L);
        verify(gestacaoRepository).save(any(Gestacao.class));

    }


    @Test
    @DisplayName("deve apresentar a contagem de 40 semanas da gestacao pelo id com sucesso")
    void deveApresentarContagem40SemanasDaGestacaoPeloIdComSucesso() throws GestacaoNaoEncontradaException {
        Gestacao gestacao = buildMockGestacao();

        when(gestacaoRepository.findById(1L)).thenReturn(Optional.of(gestacao));

       LocalDate data40Semanas = this.gestacaoService.calculaGestacao(1L);

        assertEquals("2025-10-04", data40Semanas.toString());
    }

    @Test
    @DisplayName("deve apresentar a contagem de 40 semanas da gestacao por dum")
    void deveApresentarContagem40SemanasDaGestacaoPorDum(){
        LocalDate dum = LocalDate.of(2024,12,28);
        LocalDate hoje = LocalDate.of(2025, 1, 12);

        IdadeGestacional idadeGestacional = gestacaoService.calcularDppPorDum(dum, hoje);

        assertEquals(LocalDate.of(2025,10,4), idadeGestacional.dpp());
        assertEquals(2, idadeGestacional.semanas());
        assertEquals(1, idadeGestacional.dias());
        assertEquals(1, idadeGestacional.trimestre());
    }

    @Test
    @DisplayName("deve apresentar a contagem de 40 semanas da gestacao por ultra")
    void deveApresentarContagem40SemanasDaGestacaoPorUltra(){
        LocalDate ultra = LocalDate.of(2026,1,1);
        LocalDate hoje = LocalDate.of(2026, 1, 8);

        IdadeGestacional idadeGestacional = gestacaoService.calcularDppPorUltra(
                ultra,
                10,
                0,
                hoje
        );

        assertEquals(LocalDate.of(2026,7,30), idadeGestacional.dpp());
        assertEquals(11, idadeGestacional.semanas());
        assertEquals(0, idadeGestacional.dias());
        assertEquals(1, idadeGestacional.trimestre());
    }



    private Gestacao buildMockGestacao() {
        return new Gestacao(
                "Fulana",
                LocalDate.parse("2024-12-28"),
                Classificacao.GESTACAO_NORMAL
        );
    }
}
