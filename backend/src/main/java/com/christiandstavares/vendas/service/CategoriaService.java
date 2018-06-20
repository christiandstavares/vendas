package com.christiandstavares.vendas.service;

import com.christiandstavares.vendas.entity.Categoria;
import com.christiandstavares.vendas.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    public Categoria buscarPorId(Long id) {
        Optional<Categoria> categoria = categoriaRepository.findById(id);
        return categoria.orElse(null);
    }
}
