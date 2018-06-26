package com.christiandstavares.vendas.service;

import com.christiandstavares.vendas.entity.Pedido;
import com.christiandstavares.vendas.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    public List<Pedido> salvarLista(List<Pedido> pedidos) {
        return pedidoRepository.saveAll(pedidos);
    }
}
