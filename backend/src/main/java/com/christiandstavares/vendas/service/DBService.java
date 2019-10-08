package com.christiandstavares.vendas.service;

import com.christiandstavares.vendas.entity.*;
import com.christiandstavares.vendas.enums.EstadoPagamento;
import com.christiandstavares.vendas.enums.Perfil;
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

        Produto p12 = new Produto(null, "Produto 12", 10.00);
        Produto p13 = new Produto(null, "Produto 13", 10.00);
        Produto p14 = new Produto(null, "Produto 14", 10.00);
        Produto p15 = new Produto(null, "Produto 15", 10.00);
        Produto p16 = new Produto(null, "Produto 16", 10.00);
        Produto p17 = new Produto(null, "Produto 17", 10.00);
        Produto p18 = new Produto(null, "Produto 18", 10.00);
        Produto p19 = new Produto(null, "Produto 19", 10.00);
        Produto p20 = new Produto(null, "Produto 20", 10.00);
        Produto p21 = new Produto(null, "Produto 21", 10.00);
        Produto p22 = new Produto(null, "Produto 22", 10.00);
        Produto p23 = new Produto(null, "Produto 23", 10.00);
        Produto p24 = new Produto(null, "Produto 24", 10.00);
        Produto p25 = new Produto(null, "Produto 25", 10.00);
        Produto p26 = new Produto(null, "Produto 26", 10.00);
        Produto p27 = new Produto(null, "Produto 27", 10.00);
        Produto p28 = new Produto(null, "Produto 28", 10.00);
        Produto p29 = new Produto(null, "Produto 29", 10.00);
        Produto p30 = new Produto(null, "Produto 30", 10.00);
        Produto p31 = new Produto(null, "Produto 31", 10.00);
        Produto p32 = new Produto(null, "Produto 32", 10.00);
        Produto p33 = new Produto(null, "Produto 33", 10.00);
        Produto p34 = new Produto(null, "Produto 34", 10.00);
        Produto p35 = new Produto(null, "Produto 35", 10.00);
        Produto p36 = new Produto(null, "Produto 36", 10.00);
        Produto p37 = new Produto(null, "Produto 37", 10.00);
        Produto p38 = new Produto(null, "Produto 38", 10.00);
        Produto p39 = new Produto(null, "Produto 39", 10.00);
        Produto p40 = new Produto(null, "Produto 40", 10.00);
        Produto p41 = new Produto(null, "Produto 41", 10.00);
        Produto p42 = new Produto(null, "Produto 42", 10.00);
        Produto p43 = new Produto(null, "Produto 43", 10.00);
        Produto p44 = new Produto(null, "Produto 44", 10.00);
        Produto p45 = new Produto(null, "Produto 45", 10.00);
        Produto p46 = new Produto(null, "Produto 46", 10.00);
        Produto p47 = new Produto(null, "Produto 47", 10.00);
        Produto p48 = new Produto(null, "Produto 48", 10.00);
        Produto p49 = new Produto(null, "Produto 49", 10.00);
        Produto p50 = new Produto(null, "Produto 50", 10.00);

        categoria1.getProdutos().addAll(Arrays.asList(p12, p13, p14, p15, p16, p17, p18, p19, p20,
                p21, p22, p23, p24, p25, p26, p27, p28, p29, p30, p31, p32, p34, p35, p36, p37, p38,
                p39, p40, p41, p42, p43, p44, p45, p46, p47, p48, p49, p50));

        p12.getCategorias().add(categoria1);
        p13.getCategorias().add(categoria1);
        p14.getCategorias().add(categoria1);
        p15.getCategorias().add(categoria1);
        p16.getCategorias().add(categoria1);
        p17.getCategorias().add(categoria1);
        p18.getCategorias().add(categoria1);
        p19.getCategorias().add(categoria1);
        p20.getCategorias().add(categoria1);
        p21.getCategorias().add(categoria1);
        p22.getCategorias().add(categoria1);
        p23.getCategorias().add(categoria1);
        p24.getCategorias().add(categoria1);
        p25.getCategorias().add(categoria1);
        p26.getCategorias().add(categoria1);
        p27.getCategorias().add(categoria1);
        p28.getCategorias().add(categoria1);
        p29.getCategorias().add(categoria1);
        p30.getCategorias().add(categoria1);
        p31.getCategorias().add(categoria1);
        p32.getCategorias().add(categoria1);
        p33.getCategorias().add(categoria1);
        p34.getCategorias().add(categoria1);
        p35.getCategorias().add(categoria1);
        p36.getCategorias().add(categoria1);
        p37.getCategorias().add(categoria1);
        p38.getCategorias().add(categoria1);
        p39.getCategorias().add(categoria1);
        p40.getCategorias().add(categoria1);
        p41.getCategorias().add(categoria1);
        p42.getCategorias().add(categoria1);
        p43.getCategorias().add(categoria1);
        p44.getCategorias().add(categoria1);
        p45.getCategorias().add(categoria1);
        p46.getCategorias().add(categoria1);
        p47.getCategorias().add(categoria1);
        p48.getCategorias().add(categoria1);
        p49.getCategorias().add(categoria1);
        p50.getCategorias().add(categoria1);

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

        produtoService.salvarLista(Arrays.asList(p12, p13, p14, p15, p16, p17, p18, p19, p20,
                p21, p22, p23, p24, p25, p26, p27, p28, p29, p30, p31, p32, p33, p34, p35, p36, p37, p38,
                p39, p40, p41, p42, p43, p44, p45, p46, p47, p48, p49, p50));

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
        Cliente cli2 = new Cliente(null, "Ana Costa", "christiandstavares@hotmail.com", bCryptPasswordEncoder.encode("123"), "04260284061", TipoCliente.PESSOA_FISICA);

        cli2.addPerfil(Perfil.ADMIN);

        cli1.getTelefones().addAll(Arrays.asList("27363323", "93838393"));
        cli2.getTelefones().addAll(Arrays.asList("33398574", "91658472"));

        Endereco e1 = new Endereco(null, "38220834", "Jardim", "Rua Flores", "300", "Apto 303", cli1, c1);
        Endereco e2 = new Endereco(null, "38777012", "Centro", "Avenida Matos", "105", "Sala 800", cli1, c2);
        Endereco e3 = new Endereco(null, "72586023", "Centro", "Avenida Jardim", "60", "Apto 201", cli2, c2);

        cli1.getEnderecos().addAll(Arrays.asList(e1, e2));
        cli2.getEnderecos().addAll(Arrays.asList(e3));

        clienteService.salvarLista(Arrays.asList(cli1, cli2));
        enderecoService.salvarLista(Arrays.asList(e1, e2, e3));

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
