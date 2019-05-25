package com.christiandstavares.vendas.service;

import com.christiandstavares.vendas.entity.*;
import com.christiandstavares.vendas.enums.EstadoPagamento;
import com.christiandstavares.vendas.enums.TipoCliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Collections;

@Service
public class DBService {

    @Autowired
    private CategoriaService categoriaService;

    @Autowired
    private ProdutoService produtoService;

    @Autowired
    private EstadoService estadoService;

    @Autowired
    private CidadeService cidadeService;

    @Autowired
    private EnderecoService enderecoService;

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private PedidoService pedidoService;

    @Autowired
    private PagamentoService pagamentoService;

    @Autowired
    private ItemPedidoService itemPedidoService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public void instantiateTestDatabase() throws ParseException {
        Categoria categoria1 = new Categoria(null, "Informática");
        Categoria categoria2 = new Categoria(null, "Escritório");
        Categoria categoria3 = new Categoria(null, "Cama, mesa e banho");
        Categoria categoria4 = new Categoria(null, "Eletrônicos");
        Categoria categoria5 = new Categoria(null, "Jardinagem");
        Categoria categoria6 = new Categoria(null, "Decoração");
        Categoria categoria7 = new Categoria(null, "Perfumaria");

        Produto produto1 = new Produto(null, "Computador", 2000.00);
        Produto produto2 = new Produto(null, "Impressora", 800.00);
        Produto produto3 = new Produto(null, "Mouse", 80.00);
        Produto produto4 = new Produto(null, "Mesa de escritório", 300.00);
        Produto produto5 = new Produto(null, "Toalha", 50.00);
        Produto produto6 = new Produto(null, "Colcha", 200.00);
        Produto produto7 = new Produto(null, "TV true color", 1200.00);
        Produto produto8 = new Produto(null, "Roçadeira", 800.00);
        Produto produto9 = new Produto(null, "Abajour", 100.00);
        Produto produto10 = new Produto(null, "Pendente", 180.00);
        Produto produto11 = new Produto(null, "Shampoo", 90.00);

        categoria1.setProdutos(Arrays.asList(produto1, produto2, produto3));
        categoria2.setProdutos(Arrays.asList(produto2, produto4));
        categoria3.setProdutos(Arrays.asList(produto5, produto6));
        categoria4.setProdutos(Arrays.asList(produto1, produto2, produto3, produto7));
        categoria5.setProdutos(Arrays.asList(produto8));
        categoria6.setProdutos(Arrays.asList(produto9, produto10));
        categoria7.setProdutos(Arrays.asList(produto11));

        produto1.setCategorias(Arrays.asList(categoria1, categoria4));
        produto2.setCategorias(Arrays.asList(categoria1, categoria2, categoria4));
        produto3.setCategorias(Arrays.asList(categoria1, categoria4));
        produto4.setCategorias(Arrays.asList(categoria2));
        produto5.setCategorias(Arrays.asList(categoria3));
        produto6.setCategorias(Arrays.asList(categoria3));
        produto7.setCategorias(Arrays.asList(categoria4));
        produto8.setCategorias(Arrays.asList(categoria5));
        produto9.setCategorias(Arrays.asList(categoria6));
        produto10.setCategorias(Arrays.asList(categoria6));
        produto11.setCategorias(Arrays.asList(categoria7));

        categoriaService.salvarLista(Arrays.asList(categoria1, categoria2, categoria3, categoria4, categoria5, categoria6, categoria7));
        produtoService.salvarLista(Arrays.asList(produto1, produto2, produto3, produto4, produto5, produto6, produto7, produto8, produto9, produto10, produto11));

        Estado est1 = new Estado(null, "Minas Gerais");
        Estado est2 = new Estado(null, "São Paulo");

        Cidade c1 = new Cidade(null, "Uberlândia", est1);
        Cidade c2 = new Cidade(null, "São Paulo", est2);
        Cidade c3 = new Cidade(null, "Campinas", est2);

        est1.getCidades().addAll(Collections.singletonList(c1));
        est2.getCidades().addAll(Arrays.asList(c2, c3));

        estadoService.salvarLista(Arrays.asList(est1, est2));
        cidadeService.salvarLista(Arrays.asList(c1, c2, c3));

        Cliente cli1 = new Cliente(null, "Maria Silva", "christiandstavares@gmail.com", bCryptPasswordEncoder.encode("123"), "36378912377", TipoCliente.PESSOA_FISICA);

        cli1.getTelefones().addAll(Arrays.asList("27363323", "93838393"));

        Endereco e1 = new Endereco(null, "38220834", "Jardim", "Rua Flores", "300", "Apto 303", cli1, c1);
        Endereco e2 = new Endereco(null, "38777012", "Centro", "Avenida Matos", "105", "Sala 800", cli1, c2);

        cli1.getEnderecos().addAll(Arrays.asList(e1, e2));

        clienteService.salvarLista(Collections.singletonList(cli1));
        enderecoService.salvarLista(Arrays.asList(e1, e2));

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");

        Pedido ped1 = new Pedido(null, sdf.parse("30/09/2017 10:32"), cli1, e1);
        Pedido ped2 = new Pedido(null, sdf.parse("10/10/2017 19:35"), cli1, e2);

        Pagamento pagto1 = new PagamentoCartao(null, EstadoPagamento.QUITADO, ped1, 6);
        Pagamento pagto2 = new PagamentoBoleto(null, EstadoPagamento.PENDENTE, ped2, sdf.parse("20/10/2017 00:00"), null);

        ped1.setPagamento(pagto1);
        ped2.setPagamento(pagto2);

        cli1.getPedidos().addAll(Arrays.asList(ped1, ped2));

        pedidoService.salvarLista(Arrays.asList(ped1, ped2));
        pagamentoService.salvarLista(Arrays.asList(pagto1, pagto2));

        ItemPedido ip1 = new ItemPedido(ped1, produto1, 1, 2000.0, 0.0);
        ItemPedido ip2 = new ItemPedido(ped1, produto3, 2, 80.0, 0.0);
        ItemPedido ip3 = new ItemPedido(ped2, produto2, 1, 800.0, 100.0);

        ped1.getItens().addAll(Arrays.asList(ip1, ip2));
        ped2.getItens().addAll(Collections.singletonList(ip3));

        produto1.getItens().addAll(Collections.singletonList(ip1));
        produto2.getItens().addAll(Collections.singletonList(ip3));
        produto3.getItens().addAll(Collections.singletonList(ip2));

        itemPedidoService.salvarLista(Arrays.asList(ip1, ip2, ip3));
    }
}
