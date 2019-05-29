package com.christiandstavares.vendas.controller;

import com.christiandstavares.vendas.entity.Pedido;
import com.christiandstavares.vendas.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

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

    @GetMapping
    public ResponseEntity<Page<Pedido>> buscarComPaginacao(
            @RequestParam(value = "pagina", defaultValue = "0") Integer pagina,
            @RequestParam(value = "itensPorPagina", defaultValue = "10") Integer itensPorPagina,
            @RequestParam(value = "direcao", defaultValue = "DESC") String direcao,
            @RequestParam(value = "ordenacao", defaultValue = "instante") String ordenacao) {

        return ResponseEntity.ok(pedidoService.buscarPorCliente(pagina, itensPorPagina, direcao, ordenacao));
    }
}
