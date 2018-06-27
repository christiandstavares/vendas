package com.christiandstavares.vendas;

import com.christiandstavares.vendas.entity.Categoria;
import com.christiandstavares.vendas.entity.Cidade;
import com.christiandstavares.vendas.entity.Cliente;
import com.christiandstavares.vendas.entity.Endereco;
import com.christiandstavares.vendas.entity.Estado;
import com.christiandstavares.vendas.entity.ItemPedido;
import com.christiandstavares.vendas.entity.Pagamento;
import com.christiandstavares.vendas.entity.PagamentoBoleto;
import com.christiandstavares.vendas.entity.PagamentoCartao;
import com.christiandstavares.vendas.entity.Pedido;
import com.christiandstavares.vendas.entity.Produto;
import com.christiandstavares.vendas.enums.EstadoPagamento;
import com.christiandstavares.vendas.enums.TipoCliente;
import com.christiandstavares.vendas.service.CategoriaService;
import com.christiandstavares.vendas.service.CidadeService;
import com.christiandstavares.vendas.service.ClienteService;
import com.christiandstavares.vendas.service.EnderecoService;
import com.christiandstavares.vendas.service.EstadoService;
import com.christiandstavares.vendas.service.ItemPedidoService;
import com.christiandstavares.vendas.service.PagamentoService;
import com.christiandstavares.vendas.service.PedidoService;
import com.christiandstavares.vendas.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Collections;

@SpringBootApplication
public class VendasApplication implements CommandLineRunner {

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

	public static void main(String[] args) {
		SpringApplication.run(VendasApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		Categoria categoria1 = new Categoria(null, "Informática");
		Categoria categoria2 = new Categoria(null, "Escritório");

        Produto produto1 = new Produto(null, "Computador", 2000.00);
        Produto produto2 = new Produto(null, "Impressora", 800.00);
        Produto produto3 = new Produto(null, "Mouse", 80.00);

        categoria1.setProdutos(Arrays.asList(produto1, produto2, produto3));
        categoria2.setProdutos(Collections.singletonList(produto2));

        produto1.setCategorias(Collections.singletonList(categoria1));
        produto2.setCategorias(Arrays.asList(categoria1, categoria2));
        produto3.setCategorias(Collections.singletonList(categoria1));

		categoriaService.salvarLista(Arrays.asList(categoria1, categoria2));
		produtoService.salvarLista(Arrays.asList(produto1, produto2, produto3));

        Estado est1 = new Estado(null, "Minas Gerais");
        Estado est2 = new Estado(null, "São Paulo");

        Cidade c1 = new Cidade(null, "Uberlândia", est1);
        Cidade c2 = new Cidade(null, "São Paulo", est2);
        Cidade c3 = new Cidade(null, "Campinas", est2);

        est1.getCidades().addAll(Collections.singletonList(c1));
        est2.getCidades().addAll(Arrays.asList(c2, c3));

        estadoService.salvarLista(Arrays.asList(est1, est2));
        cidadeService.salvarLista(Arrays.asList(c1, c2, c3));

        Cliente cli1 = new Cliente(null, "Maria Silva", "maria@gmail.com", "36378912377", TipoCliente.PESSOA_FISICA);

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
