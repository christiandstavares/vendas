package com.christiandstavares.vendas.service;

import com.christiandstavares.vendas.dto.ClienteDTO;
import com.christiandstavares.vendas.dto.NovoClienteDTO;
import com.christiandstavares.vendas.entity.Cliente;
import com.christiandstavares.vendas.exception.IntegridadeDadoVioladaException;
import com.christiandstavares.vendas.exception.ObjectNotFoundException;
import com.christiandstavares.vendas.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private EnderecoService enderecoService;

    public Cliente buscarPorId(Long id) {
        Optional<Cliente> cliente = clienteRepository.findById(id);
        return cliente.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Classe: " + Cliente.class.getName()));
    }

    public List<Cliente> salvarLista(List<Cliente> clientes) {
        return clienteRepository.saveAll(clientes);
    }

    public List<ClienteDTO> buscarTodos() {
        List<Cliente> clientes = clienteRepository.findAll();

        return clientes.stream().map(c -> new ClienteDTO(c)).collect(Collectors.toList());
    }

    public Page<ClienteDTO> buscarComPaginacao(Integer pagina, Integer itensPorPagina, String direcao, String ordenacao) {
        PageRequest pageRequest = PageRequest.of(pagina, itensPorPagina, Sort.Direction.valueOf(direcao), ordenacao);
        Page<Cliente> clientes = clienteRepository.findAll(pageRequest);

        return clientes.map(c -> new ClienteDTO(c));
    }

    @Transactional
    public ClienteDTO cadastrar(NovoClienteDTO novoClienteDTO) {
        Cliente cliente = novoClienteDTO.toEntity();

        salvar(cliente);
        enderecoService.salvarLista(cliente.getEnderecos());

        return new ClienteDTO(cliente);
    }

    public ClienteDTO editar(Long id, ClienteDTO clienteDTO) {
        Cliente cliente = buscarPorId(id);

        atualizarDados(cliente, clienteDTO);
        salvar(cliente);

        return new ClienteDTO(cliente);
    }

    public void excluir(Long id) {
        buscarPorId(id);

        try {
            clienteRepository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new IntegridadeDadoVioladaException("Não é possível excluir um cliente que possui pedidos");
        }
    }

    private Cliente salvar(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    private void atualizarDados(Cliente cliente, ClienteDTO clienteDTO) {
        cliente.setNome(clienteDTO.getNome());
        cliente.setEmail(clienteDTO.getEmail());
    }
}
