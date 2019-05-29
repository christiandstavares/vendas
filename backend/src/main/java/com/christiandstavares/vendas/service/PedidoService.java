package com.christiandstavares.vendas.service;

import com.christiandstavares.vendas.entity.Cliente;
import com.christiandstavares.vendas.entity.ItemPedido;
import com.christiandstavares.vendas.entity.PagamentoBoleto;
import com.christiandstavares.vendas.entity.Pedido;
import com.christiandstavares.vendas.enums.EstadoPagamento;
import com.christiandstavares.vendas.exception.AutorizacaoException;
import com.christiandstavares.vendas.exception.ObjectNotFoundException;
import com.christiandstavares.vendas.repository.PedidoRepository;
import com.christiandstavares.vendas.security.UserSS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private PagamentoService pagamentoService;

    @Autowired
    private ProdutoService produtoService;

    @Autowired
    private ItemPedidoService itemPedidoService;

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private EmailService emailService;

    public Pedido buscarPorId(Long id) {
        Optional<Pedido> pedido = pedidoRepository.findById(id);
        return pedido.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Classe: " + Pedido.class.getName()));
    }

    public List<Pedido> salvarLista(List<Pedido> pedidos) {
        return pedidoRepository.saveAll(pedidos);
    }

    @Transactional
    public Pedido cadastrar(Pedido pedido) {
        pedido.setId(null);
        pedido.setInstante(new Date());
        pedido.setCliente(clienteService.buscarPorId(pedido.getCliente().getId()));
        pedido.getPagamento().setEstado(EstadoPagamento.PENDENTE);
        pedido.getPagamento().setPedido(pedido);

        if (pedido.getPagamento() instanceof PagamentoBoleto) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(pedido.getInstante());
            calendar.add(Calendar.DAY_OF_MONTH, 7);

            PagamentoBoleto pagamentoBoleto = (PagamentoBoleto) pedido.getPagamento();
            pagamentoBoleto.setDataVencimento(calendar.getTime());
        }

        pedidoRepository.save(pedido);
        pagamentoService.salvar(pedido.getPagamento());

        for (ItemPedido itemPedido : pedido.getItens()) {
            itemPedido.setDesconto(0.0);
            itemPedido.setProduto(produtoService.buscarPorId(itemPedido.getProduto().getId()));
            itemPedido.setPreco(itemPedido.getProduto().getPreco());
            itemPedido.setPedido(pedido);
        }

        itemPedidoService.salvarLista(pedido.getItens());

        emailService.enviarEmailHtmlDeConfirmacaoDePedido(pedido);
        return pedido;
    }

    public Page<Pedido> buscarPorCliente(Integer pagina, Integer itensPorPagina, String direcao, String ordenacao) {
        UserSS user = UsuarioService.usuarioLogado();
        if (user == null) {
            throw new AutorizacaoException("Acesso negado");
        }

        PageRequest pageRequest = PageRequest.of(pagina, itensPorPagina, Sort.Direction.valueOf(direcao), ordenacao);
        Cliente cliente =  clienteService.buscarPorId(user.getId());

        return pedidoRepository.findAllByCliente(cliente, pageRequest);
    }
}
