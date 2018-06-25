package com.christiandstavares.vendas.service;

import com.christiandstavares.vendas.entity.Endereco;
import com.christiandstavares.vendas.repository.EnderecoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EnderecoService {

    @Autowired
    private EnderecoRepository enderecoRepository;

    public List<Endereco> salvarLista(List<Endereco> enderecos) {
        return enderecoRepository.saveAll(enderecos);
    }
}
