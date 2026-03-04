package br.com.marinas.calculo_gestacao.infrastructure;

import br.com.marinas.calculo_gestacao.domain.gestacao.Gestacao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GestacaoRepositoryJpa extends JpaRepository<Gestacao, Long> {
}