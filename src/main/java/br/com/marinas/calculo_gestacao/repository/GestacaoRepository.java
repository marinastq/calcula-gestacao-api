package br.com.marinas.calculo_gestacao.repository;

import br.com.marinas.calculo_gestacao.model.Gestacao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GestacaoRepository extends JpaRepository<Gestacao, Long> {
}