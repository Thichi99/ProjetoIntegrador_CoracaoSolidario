package com.generation.CoracaoSolidario.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.util.List;

@Entity
@Table(name = "tb_destinos")
public class Destino {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Este atributo é obrigatório!")
    private String nome;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "destino", cascade = CascadeType.REMOVE)
    @JsonIgnoreProperties("destino")
    private List<Doacao> doacao;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public @NotNull(message = "Este atributo é obrigatório!") String getNome() {
        return nome;
    }

    public void setNome(@NotNull(message = "Este atributo é obrigatório!") String nome) {
        this.nome = nome;
    }

    public List<Doacao> getDoacao() {
        return doacao;
    }

    public void setDoacao(List<Doacao> doacao) {
        this.doacao = doacao;
    }
}
