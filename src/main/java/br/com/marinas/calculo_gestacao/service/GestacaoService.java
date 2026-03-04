package br.com.marinas.calculo_gestacao.service;

import br.com.marinas.calculo_gestacao.dto.DppResponse;
import br.com.marinas.calculo_gestacao.exception.GestacaoNaoEncontradaException;
import br.com.marinas.calculo_gestacao.domain.gestacao.Gestacao;
import br.com.marinas.calculo_gestacao.infrastructure.GestacaoRepositoryJpa;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class GestacaoService {

    private GestacaoRepositoryJpa gestacaoRepository;

    public DppResponse calcular(LocalDate dum) {

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
