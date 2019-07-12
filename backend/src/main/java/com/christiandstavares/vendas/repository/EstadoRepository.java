package com.christiandstavares.vendas.repository;

import com.christiandstavares.vendas.entity.Estado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EstadoRepository extends JpaRepository<Estado, Long> {

    List<Estado> findAllByOrderByNome();
}
