package com.christiandstavares.vendas.dto.parser;

import com.christiandstavares.vendas.dto.CategoriaDTO;
import com.christiandstavares.vendas.entity.Categoria;

public class CategoriaParser {

    public static CategoriaDTO toDTO(Categoria entity) {
        return new CategoriaDTO(entity.getId(), entity.getNome());
    }
}