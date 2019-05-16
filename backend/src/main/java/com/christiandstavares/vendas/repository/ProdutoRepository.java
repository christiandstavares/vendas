package com.christiandstavares.vendas.repository;

import com.christiandstavares.vendas.entity.Produto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {

    @Query("SELECT DISTINCT p FROM Produto p JOIN p.categorias c WHERE c.id IN :categorias AND p.nome LIKE %:nome%")
    Page<Produto> buscar(@Param("nome") String nome, @Param("categorias") List<Long> categorias, Pageable pageable);
}
