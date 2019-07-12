package com.christiandstavares.vendas.service;

import com.christiandstavares.vendas.dto.CidadeDTO;
import com.christiandstavares.vendas.entity.Cidade;
import com.christiandstavares.vendas.repository.CidadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CidadeService {

    @Autowired
    private CidadeRepository cidadeRepository;

    public List<Cidade> salvarLista(List<Cidade> cidades) {
        return cidadeRepository.saveAll(cidades);
    }

    public List<CidadeDTO> buscarPorEstado(Long idEstado) {
        return cidadeRepository.findAllByEstado_IdOrderByNome(idEstado).stream().map(CidadeDTO::new).collect(Collectors.toList());
    }
}
