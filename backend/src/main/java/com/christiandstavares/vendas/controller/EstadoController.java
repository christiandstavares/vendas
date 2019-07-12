package com.christiandstavares.vendas.controller;

import com.christiandstavares.vendas.dto.CidadeDTO;
import com.christiandstavares.vendas.dto.EstadoDTO;
import com.christiandstavares.vendas.service.CidadeService;
import com.christiandstavares.vendas.service.EstadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "estados")
public class EstadoController {

    @Autowired
    private EstadoService estadoService;

    @Autowired
    private CidadeService cidadeService;

    @GetMapping
    public ResponseEntity<List<EstadoDTO>> buscarTodos() {
        return ResponseEntity.ok(estadoService.buscarTodos());
    }

    @GetMapping("{id}/cidades")
    public ResponseEntity<List<CidadeDTO>> buscarCidadesPorEstado(@PathVariable(value = "id") Long idEstado) {
        return ResponseEntity.ok(cidadeService.buscarPorEstado(idEstado));
    }
}
