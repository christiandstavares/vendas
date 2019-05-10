package com.christiandstavares.vendas.dto;

import com.christiandstavares.vendas.entity.Cidade;
import com.christiandstavares.vendas.entity.Cliente;
import com.christiandstavares.vendas.entity.Endereco;
import com.christiandstavares.vendas.enums.TipoCliente;

import java.io.Serializable;

public class NovoClienteDTO implements Serializable {

    // Dados do cliente
    private String nome;
    private String email;
    private String cpfOuCnpj;
    private Integer tipo;
    // Dados do endereço
    private String cep;
    private String bairro;
    private String logradouro;
    private String numero;
    private String complemento;
    private Long idCidade;
    // Telefones
    private String telefone1;
    private String telefone2;
    private String telefone3;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCpfOuCnpj() {
        return cpfOuCnpj;
    }

    public void setCpfOuCnpj(String cpfOuCnpj) {
        this.cpfOuCnpj = cpfOuCnpj;
    }

    public Integer getTipo() {
        return tipo;
    }

    public void setTipo(Integer tipo) {
        this.tipo = tipo;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public Long getIdCidade() {
        return idCidade;
    }

    public void setIdCidade(Long idCidade) {
        this.idCidade = idCidade;
    }

    public String getTelefone1() {
        return telefone1;
    }

    public void setTelefone1(String telefone1) {
        this.telefone1 = telefone1;
    }

    public String getTelefone2() {
        return telefone2;
    }

    public void setTelefone2(String telefone2) {
        this.telefone2 = telefone2;
    }

    public String getTelefone3() {
        return telefone3;
    }

    public void setTelefone3(String telefone3) {
        this.telefone3 = telefone3;
    }

    public Cliente toEntity() {
        Cliente cliente = new Cliente(null, getNome(), getEmail(), getCpfOuCnpj(), TipoCliente.toEnum(getTipo()));
        Cidade cidade = new Cidade(getIdCidade(), null, null);
        Endereco endereco = new Endereco(null, getCep(), getBairro(), getLogradouro(), getNumero(), getComplemento(), cliente, cidade);

        cliente.getEnderecos().add(endereco);
        cliente.getTelefones().add(getTelefone1());

        if (getTelefone2() != null) {
            cliente.getTelefones().add(getTelefone2());
        }

        if (getTelefone3() != null) {
            cliente.getTelefones().add(getTelefone3());
        }

        return cliente;
    }
}
