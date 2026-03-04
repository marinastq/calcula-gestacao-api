package br.com.marinas.calculo_gestacao.infrastructure;

import br.com.marinas.calculo_gestacao.domain.gestacao.Gestacao;
import br.com.marinas.calculo_gestacao.domain.gestacao.GestacaoRepository;
import org.springframework.stereotype.Repository;

@Repository
public class GestacaoRepositoryImpl implements GestacaoRepository {
    private final GestacaoRepositoryJpa gestacaoRepositoryJpa;

    public GestacaoRepositoryImpl(GestacaoRepositoryJpa gestacaoRepositoryJpa) {
        this.gestacaoRepositoryJpa = gestacaoRepositoryJpa;
    }

    public Gestacao salvar(Gestacao gestacao) {
        return gestacaoRepositoryJpa.save(gestacao);
    }
}
