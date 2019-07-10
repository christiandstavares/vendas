package com.christiandstavares.vendas.service;

import com.christiandstavares.vendas.dto.ClienteDTO;
import com.christiandstavares.vendas.dto.NovoClienteDTO;
import com.christiandstavares.vendas.entity.Cliente;
import com.christiandstavares.vendas.enums.Perfil;
import com.christiandstavares.vendas.exception.AutorizacaoException;
import com.christiandstavares.vendas.exception.IntegridadeDadoVioladaException;
import com.christiandstavares.vendas.exception.ObjectNotFoundException;
import com.christiandstavares.vendas.repository.ClienteRepository;
import com.christiandstavares.vendas.security.UserSS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.awt.image.BufferedImage;
import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private EnderecoService enderecoService;

    @Autowired
    private S3Service s3Service;

    @Autowired
    private ImagemService imagemService;

    @Value("${img.prefix.client.profile}")
    private String prefixo;

    @Value("${img.profile.size}")
    private Integer size;

    public Cliente buscarPorId(Long id) {
        UserSS user = UsuarioService.usuarioLogado();

        if (user == null || !user.hasRole(Perfil.ADMIN) && !id.equals(user.getId())) {
            throw new AutorizacaoException("Acesso negado");
        }

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

    public Cliente salvar(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    private void atualizarDados(Cliente cliente, ClienteDTO clienteDTO) {
        cliente.setNome(clienteDTO.getNome());
        cliente.setEmail(clienteDTO.getEmail());
    }

    @Transactional(readOnly = true)
    public Cliente buscarPorEmail(String email) {
        return clienteRepository.findByEmail(email);
    }

    public URI uploadFotoPerfil(MultipartFile multipartFile) {
        UserSS user = UsuarioService.usuarioLogado();
        if (user == null) {
            throw new AutorizacaoException("Acesso negado");
        }

        BufferedImage jpgImage = imagemService.getJpgImageFromFile(multipartFile);
        jpgImage = imagemService.cropSquare(jpgImage);
        jpgImage = imagemService.resize(jpgImage, size);
        String fileName = prefixo + user.getId() + ".jpg";

        return s3Service.uploadFile(imagemService.getInputStream(jpgImage, "jpg"), fileName, "image");
    }
}
