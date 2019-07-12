package com.christiandstavares.vendas.repository;

import com.christiandstavares.vendas.entity.Cidade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CidadeRepository extends JpaRepository<Cidade, Long> {

    List<Cidade> findAllByEstado_IdOrderByNome(Long idEstado);
}
