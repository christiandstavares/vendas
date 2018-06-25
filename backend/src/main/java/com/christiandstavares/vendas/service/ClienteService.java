package com.christiandstavares.vendas.service;

import com.christiandstavares.vendas.entity.Cliente;
import com.christiandstavares.vendas.exception.ObjectNotFoundException;
import com.christiandstavares.vendas.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public Cliente buscarPorId(Long id) {
        Optional<Cliente> cliente = clienteRepository.findById(id);
        return cliente.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Classe: " + Cliente.class.getName()));
    }

    public List<Cliente> salvarLista(List<Cliente> clientes) {
        return clienteRepository.saveAll(clientes);
    }
}
