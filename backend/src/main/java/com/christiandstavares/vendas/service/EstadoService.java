package com.christiandstavares.vendas.service;

import com.christiandstavares.vendas.dto.EstadoDTO;
import com.christiandstavares.vendas.entity.Estado;
import com.christiandstavares.vendas.repository.EstadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EstadoService {

    @Autowired
    private EstadoRepository estadoRepository;

    public List<Estado> salvarLista(List<Estado> estados) {
        return estadoRepository.saveAll(estados);
    }

    public List<EstadoDTO> buscarTodos() {
        return estadoRepository.findAllByOrderByNome().stream().map(EstadoDTO::new).collect(Collectors.toList());
    }
}
