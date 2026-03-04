package br.com.marinas.calculo_gestacao.domain.gestacao;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "gestacao")
public class Gestacao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String nome;

    @Column(nullable = false)
    private LocalDate dataDUM;

    @Column(nullable = false)
    private Classificacao classificacao;

    @CreationTimestamp
    @Column(name = "criado_em", nullable = false, updatable = false)
    private LocalDateTime criadoEm;

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public LocalDate getDataDUM() {
        return dataDUM;
    }

    public Classificacao getClassificacao() {
        return classificacao;
    }

    @CreationTimestamp
    @Column(name = "criado_em", nullable = false, updatable = false)
    public LocalDateTime getCriadoEm() {
        return criadoEm;
    }

    public Gestacao(String nome, LocalDate dataDUM, Classificacao classificacao) {
        this.nome = nome;
        this.dataDUM = dataDUM;
        this.classificacao = classificacao;
    }

    public Gestacao atualizar(String nome, LocalDate dataDUM, Classificacao classificacao){
        this.nome = nome;
        this.dataDUM = dataDUM;
        this.classificacao = classificacao;

        return this;
    }
}
