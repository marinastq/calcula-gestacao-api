package br.com.marinas.calculo_gestacao;

import br.com.marinas.calculo_gestacao.domain.gestacao.Classificacao;
import br.com.marinas.calculo_gestacao.domain.gestacao.Gestacao;
import br.com.marinas.calculo_gestacao.infrastructure.GestacaoRepositoryJpa;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class GestacaoRepositoryTest {

    @Autowired
    GestacaoRepositoryJpa gestacaoRepository;

    public GestacaoRepositoryTest() {
    }

    @Test
    @DisplayName("deve gravar a gestacao com sucesso")
    void deveGravarGestacaoComSucesso(){
        Gestacao gestacaoSalva = this.gestacaoRepository.save(buildMockGestacao());

        assertNotNull(gestacaoSalva);
        assertEquals("2024-12-28", gestacaoSalva.getDataDUM().toString());
    }

    @Test
    @DisplayName("deve listar a gestacao por id com sucesso")
    void deveListarGestacaoPorIdComSucesso(){
        Gestacao gestacaoSalva = this.gestacaoRepository.save(buildMockGestacao());

        Optional<Gestacao> gestacao = this.gestacaoRepository.findById(gestacaoSalva.getId());

        assertTrue(gestacao.isPresent());
        assertEquals("2024-12-28", gestacaoSalva.getDataDUM().toString());
    }

    @Test
    @DisplayName("deve excluir a gestacao por id com sucesso")
    void deveExcluirGestacaoPorIdComSucesso(){
        Gestacao gestacaoSalva = this.gestacaoRepository.save(buildMockGestacao());

        this.gestacaoRepository.deleteById(gestacaoSalva.getId());

        Optional<Gestacao> gestacao = this.gestacaoRepository.findById(gestacaoSalva.getId());

        assertFalse(gestacao.isPresent());
    }



    private Gestacao buildMockGestacao() {
        return new Gestacao(
                "Fulana",
                LocalDate.parse("2024-12-28"),
                Classificacao.GESTACAO_NORMAL
                );
    }
}
