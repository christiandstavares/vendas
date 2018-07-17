package com.christiandstavares.vendas.service;

import com.christiandstavares.vendas.dto.CategoriaDTO;
import com.christiandstavares.vendas.dto.parser.CategoriaParser;
import com.christiandstavares.vendas.entity.Categoria;
import com.christiandstavares.vendas.exception.IntegridadeDadoVioladaException;
import com.christiandstavares.vendas.exception.ObjectNotFoundException;
import com.christiandstavares.vendas.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    public List<CategoriaDTO> buscarTodos() {
        List<Categoria> categorias = categoriaRepository.findAll();

        return categorias.stream().map(CategoriaParser::toDTO).collect(Collectors.toList());
    }

    public Page<CategoriaDTO> buscarComPaginacao(Integer pagina, Integer itensPorPagina, String direcao, String ordenacao) {
        PageRequest pageRequest = PageRequest.of(pagina, itensPorPagina, Sort.Direction.valueOf(direcao), ordenacao);

        Page<Categoria> categorias = categoriaRepository.findAll(pageRequest);

        return categorias.map(CategoriaParser::toDTO);
    }

    public Categoria buscarPorId(Long id) {
        Optional<Categoria> categoria = categoriaRepository.findById(id);
        return categoria.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Classe: " + Categoria.class.getName()));
    }

    public List<Categoria> salvarLista(List<Categoria> categorias) {
        return categoriaRepository.saveAll(categorias);
    }

    public Categoria salvar(Categoria categoria) {
        return categoriaRepository.save(categoria);
    }

    public CategoriaDTO cadastrar(CategoriaDTO categoriaDTO) {
        Categoria categoria = CategoriaParser.toEntity(categoriaDTO);

        return CategoriaParser.toDTO(salvar(categoria));
    }

    public CategoriaDTO editar(Long id, CategoriaDTO categoriaDTO) {
        buscarPorId(id);

        Categoria categoria = CategoriaParser.toEntity(categoriaDTO);
        categoria.setId(id);

        return CategoriaParser.toDTO(salvar(categoria));
    }

    public void excluir(Long id) {
        buscarPorId(id);

        try {
            categoriaRepository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new IntegridadeDadoVioladaException("Não é possível excluir uma categoria que possui produtos");
        }
    }
}
