package com.christiandstavares.vendas.controller;

import com.christiandstavares.vendas.entity.Pedido;
import com.christiandstavares.vendas.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping(value = "pedidos")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    @GetMapping(value = "{id}")
    public ResponseEntity<Pedido> buscarPorId(@PathVariable Long id) {
        Pedido pedido = pedidoService.buscarPorId(id);

        return ResponseEntity.ok(pedido);
    }

    @PostMapping
    public ResponseEntity<Void> cadastrar(@Valid @RequestBody Pedido pedido) {
        Pedido novoPedido = pedidoService.cadastrar(pedido);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(novoPedido.getId()).toUri();

        return ResponseEntity.created(uri).build();
    }
}
