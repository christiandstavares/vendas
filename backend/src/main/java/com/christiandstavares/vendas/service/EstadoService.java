package com.christiandstavares.vendas.service;

import com.christiandstavares.vendas.entity.Estado;
import com.christiandstavares.vendas.repository.EstadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EstadoService {

    @Autowired
    private EstadoRepository estadoRepository;

    public List<Estado> salvarLista(List<Estado> estados) {
        return estadoRepository.saveAll(estados);
    }
}
