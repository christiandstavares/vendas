package com.christiandstavares.vendas.controller;

import com.christiandstavares.vendas.entity.Categoria;
import com.christiandstavares.vendas.service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "categorias")
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;

    @GetMapping
    public List<Categoria> listar() {
        List<Categoria> categorias = new ArrayList<>();
        Categoria cat1 = new Categoria(1L, "Informática");
        Categoria cat2 = new Categoria(2L, "Escritório");

        categorias.add(cat1);
        categorias.add(cat2);

        return categorias;
    }

    @GetMapping(value = "{id}")
    public ResponseEntity<Categoria> buscarPorId(@PathVariable Long id) {
        Categoria categoria = categoriaService.buscarPorId(id);

        return ResponseEntity.ok(categoria);
    }
}
