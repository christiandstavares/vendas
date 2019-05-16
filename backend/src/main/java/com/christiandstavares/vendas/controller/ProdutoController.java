package com.christiandstavares.vendas.controller;

import com.christiandstavares.vendas.dto.ProdutoDTO;
import com.christiandstavares.vendas.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "produtos")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @GetMapping
    public ResponseEntity<Page<ProdutoDTO>> buscarComPaginacao(
            @RequestParam(value = "nome", defaultValue = "") String nome,
            @RequestParam(value = "categorias", defaultValue = "") List<Long> categorias,
            @RequestParam(value = "pagina", defaultValue = "0") Integer pagina,
            @RequestParam(value = "itensPorPagina", defaultValue = "10") Integer itensPorPagina,
            @RequestParam(value = "direcao", defaultValue = "ASC") String direcao,
            @RequestParam(value = "ordenacao", defaultValue = "nome") String ordenacao) {

        Page<ProdutoDTO> produtoDTOPage = produtoService.buscar(nome, categorias, pagina, itensPorPagina, direcao, ordenacao);

        return ResponseEntity.ok(produtoDTOPage);
    }
}
