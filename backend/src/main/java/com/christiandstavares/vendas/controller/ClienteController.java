package com.christiandstavares.vendas.controller;

import com.christiandstavares.vendas.dto.ClienteDTO;
import com.christiandstavares.vendas.dto.NovoClienteDTO;
import com.christiandstavares.vendas.entity.Cliente;
import com.christiandstavares.vendas.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping(value = "{id}")
    public ResponseEntity<Cliente> buscarPorId(@PathVariable Long id) {
        Cliente cliente = clienteService.buscarPorId(id);

        return ResponseEntity.ok(cliente);
    }

    @GetMapping
    public ResponseEntity<List<ClienteDTO>> buscarTodos() {
        List<ClienteDTO> clienteDTOList = clienteService.buscarTodos();

        return ResponseEntity.ok(clienteDTOList);
    }

    @GetMapping(value = "paginacao")
    public ResponseEntity<Page<ClienteDTO>> buscarComPaginacao(
            @RequestParam(value = "pagina", defaultValue = "0") Integer pagina,
            @RequestParam(value = "itensPorPagina", defaultValue = "10") Integer itensPorPagina,
            @RequestParam(value = "direcao", defaultValue = "ASC") String direcao,
            @RequestParam(value = "ordenacao", defaultValue = "nome") String ordenacao) {
        Page<ClienteDTO> clienteDTOPage = clienteService.buscarComPaginacao(pagina, itensPorPagina, direcao, ordenacao);

        return ResponseEntity.ok(clienteDTOPage);
    }

    @PostMapping
    public ResponseEntity<Void> cadastrar(@Valid @RequestBody NovoClienteDTO novoClienteDTO) {
        ClienteDTO clienteDTO = clienteService.cadastrar(novoClienteDTO);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(clienteDTO.getId()).toUri();

        return ResponseEntity.created(uri).build();
    }

    @PutMapping(value = "{id}")
    public ResponseEntity<Void> editar(@PathVariable Long id, @Valid @RequestBody ClienteDTO cliente) {
        clienteService.editar(id, cliente);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(value = "{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        clienteService.excluir(id);

        return ResponseEntity.noContent().build();
    }
}
