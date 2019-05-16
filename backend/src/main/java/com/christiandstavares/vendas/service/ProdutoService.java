package com.christiandstavares.vendas.service;

import com.christiandstavares.vendas.dto.ProdutoDTO;
import com.christiandstavares.vendas.entity.Produto;
import com.christiandstavares.vendas.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    public List<Produto> salvarLista(List<Produto> produtos) {
        return produtoRepository.saveAll(produtos);
    }

    @Transactional(readOnly = true)
    public Page<ProdutoDTO> buscar(String nome, List<Long> categorias, Integer pagina, Integer itensPorPagina, String direcao, String ordenacao) {
        PageRequest pageRequest = PageRequest.of(pagina, itensPorPagina, Sort.Direction.valueOf(direcao), ordenacao);
        return produtoRepository.buscar(nome, categorias, pageRequest).map(p -> new ProdutoDTO(p));
    }
}
