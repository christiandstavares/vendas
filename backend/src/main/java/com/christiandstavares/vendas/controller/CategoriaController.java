package com.christiandstavares.vendas.controller;

import com.christiandstavares.vendas.dto.CategoriaDTO;
import com.christiandstavares.vendas.entity.Categoria;
import com.christiandstavares.vendas.service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "categorias")
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;

    @GetMapping(value = "{id}")
    public ResponseEntity<Categoria> buscarPorId(@PathVariable Long id) {
        Categoria categoria = categoriaService.buscarPorId(id);

        return ResponseEntity.ok(categoria);
    }

    @GetMapping
    public ResponseEntity<List<CategoriaDTO>> buscarTodos() {
        List<CategoriaDTO> categoriaDTOList = categoriaService.buscarTodos();

        return ResponseEntity.ok(categoriaDTOList);
    }

    @GetMapping(value = "paginacao")
    public ResponseEntity<Page<CategoriaDTO>> buscarComPaginacao(
            @RequestParam(value = "pagina", defaultValue = "0") Integer pagina,
            @RequestParam(value = "itensPorPagina", defaultValue = "10") Integer itensPorPagina,
            @RequestParam(value = "direcao", defaultValue = "ASC") String direcao,
            @RequestParam(value = "ordenacao", defaultValue = "nome") String ordenacao) {
        Page<CategoriaDTO> categoriaDTOPage = categoriaService.buscarComPaginacao(pagina, itensPorPagina, direcao, ordenacao);

        return ResponseEntity.ok(categoriaDTOPage);
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<Void> cadastrar(@Valid @RequestBody CategoriaDTO categoria) {
        CategoriaDTO novaCategoria = categoriaService.cadastrar(categoria);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(novaCategoria.getId()).toUri();

        return ResponseEntity.created(uri).build();
    }

    @PutMapping(value = "{id}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<Void> editar(@PathVariable Long id, @Valid @RequestBody CategoriaDTO categoria) {
        categoriaService.editar(id, categoria);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(value = "{id}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        categoriaService.excluir(id);

        return ResponseEntity.noContent().build();
    }
}
