package com.christiandstavares.vendas.dto;

import com.christiandstavares.vendas.entity.Estado;

import java.io.Serializable;

public class EstadoDTO implements Serializable {

    private Long id;
    private String nome;

    public EstadoDTO() {
    }

    public EstadoDTO(Long id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public EstadoDTO(Estado estado) {
        this.id = estado.getId();
        this.nome = estado.getNome();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
