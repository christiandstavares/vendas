package com.christiandstavares.vendas.service;

import com.christiandstavares.vendas.entity.ItemPedido;
import com.christiandstavares.vendas.repository.ItemPedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemPedidoService {

    @Autowired
    private ItemPedidoRepository itemPedidoRepository;

    public List<ItemPedido> salvarLista(List<ItemPedido> itens) {
        return itemPedidoRepository.saveAll(itens);
    }
}
