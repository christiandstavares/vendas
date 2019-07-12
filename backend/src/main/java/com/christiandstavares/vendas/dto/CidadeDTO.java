package com.christiandstavares.vendas.dto;

import com.christiandstavares.vendas.entity.Cidade;

import java.io.Serializable;

public class CidadeDTO implements Serializable {

    private Long id;
    private String nome;

    public CidadeDTO() {
    }

    public CidadeDTO(Long id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public CidadeDTO(Cidade cidade) {
        this.id = cidade.getId();
        this.nome = cidade.getNome();
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
