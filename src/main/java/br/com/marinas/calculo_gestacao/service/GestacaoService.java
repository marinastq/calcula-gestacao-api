package br.com.marinas.calculo_gestacao.service;

import br.com.marinas.calculo_gestacao.domain.gestacao.IdadeGestacional;
import br.com.marinas.calculo_gestacao.exception.GestacaoNaoEncontradaException;
import br.com.marinas.calculo_gestacao.domain.gestacao.Gestacao;
import br.com.marinas.calculo_gestacao.infrastructure.GestacaoRepositoryJpa;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Service
public class GestacaoService {

    private GestacaoRepositoryJpa gestacaoRepository;

    public IdadeGestacional calcularDppPorDum(LocalDate dum) {
        long totalDias = ChronoUnit.DAYS.between(dum, LocalDate.now());

        return calculaIdadeGestacional(totalDias, dum.plusDays(280));
    }

    private IdadeGestacional calculaIdadeGestacional(long totalDias, LocalDate dpp) {
        int semanas = (int) (totalDias / 7);
        int dias = (int) (totalDias % 7);

        return new IdadeGestacional(
                dpp,
                semanas,
                dias,
                semanas < 13 ? 1 : semanas < 28 ? 2 : 3
        );
    }

    public IdadeGestacional calcularDppPorUltra(LocalDate dataUltra, int semanas, int dias){
        int idadeGestacionalEmDias = semanas * 7 + dias;

        long diasDesdeExame = ChronoUnit.DAYS.between(dataUltra, LocalDate.now());

        long totalDias = idadeGestacionalEmDias + diasDesdeExame;

        return calculaIdadeGestacional(totalDias, dataUltra.plusDays((280 - idadeGestacionalEmDias)));
    }

    public GestacaoService(GestacaoRepositoryJpa gestacaoRepository) {
        this.gestacaoRepository = gestacaoRepository;
    }

    public Gestacao criarGestacao(Gestacao gestacao) {
        return gestacaoRepository.save(gestacao);
    }

    public Gestacao buscarGestacaoPeloId(Long id) throws GestacaoNaoEncontradaException {
        return gestacaoRepository.findById(id)
                .orElseThrow(() -> new GestacaoNaoEncontradaException(id));
    }

    public void excluirGestacao(long id) throws GestacaoNaoEncontradaException {

        gestacaoRepository.findById(id)
                .orElseThrow(() -> new GestacaoNaoEncontradaException(id));

        gestacaoRepository.deleteById(id);
    }

    public Gestacao alterarGestacao(Long id, Gestacao gestacao) throws GestacaoNaoEncontradaException {
        Gestacao gestacaoEncontrada = gestacaoRepository.findById(id)
                .map((g) -> g.atualizar(
                        gestacao.getNome(),
                        gestacao.getDataDUM(),
                        gestacao.getClassificacao()))
                .orElseThrow(() -> new GestacaoNaoEncontradaException(id));

        return gestacaoRepository.save(gestacaoEncontrada);
    }

    public LocalDate calculaGestacao(long id) throws GestacaoNaoEncontradaException {
        return gestacaoRepository.findById(id)
                .map((g) -> g.getDataDUM().plusWeeks(40))
                .orElseThrow(() -> new GestacaoNaoEncontradaException(id));

    }


}
