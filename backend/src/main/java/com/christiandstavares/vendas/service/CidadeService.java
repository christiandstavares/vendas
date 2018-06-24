package com.christiandstavares.vendas.service;

import com.christiandstavares.vendas.entity.Cidade;
import com.christiandstavares.vendas.repository.CidadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CidadeService {

    @Autowired
    private CidadeRepository cidadeRepository;

    public List<Cidade> salvarLista(List<Cidade> cidades) {
        return cidadeRepository.saveAll(cidades);
    }
}
