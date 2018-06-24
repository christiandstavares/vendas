package com.christiandstavares.vendas.service;

import com.christiandstavares.vendas.entity.Produto;
import com.christiandstavares.vendas.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    public List<Produto> salvarLista(List<Produto> produtos) {
        return produtoRepository.saveAll(produtos);
    }
}
