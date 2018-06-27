package com.christiandstavares.vendas.service;

import com.christiandstavares.vendas.entity.Pedido;
import com.christiandstavares.vendas.exception.ObjectNotFoundException;
import com.christiandstavares.vendas.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    public Pedido buscarPorId(Long id) {
        Optional<Pedido> pedido = pedidoRepository.findById(id);
        return pedido.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Classe: " + Pedido.class.getName()));
    }

    public List<Pedido> salvarLista(List<Pedido> pedidos) {
        return pedidoRepository.saveAll(pedidos);
    }
}
