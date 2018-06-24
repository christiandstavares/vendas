package com.christiandstavares.vendas;

import com.christiandstavares.vendas.entity.Categoria;
import com.christiandstavares.vendas.entity.Cidade;
import com.christiandstavares.vendas.entity.Estado;
import com.christiandstavares.vendas.entity.Produto;
import com.christiandstavares.vendas.service.CategoriaService;
import com.christiandstavares.vendas.service.CidadeService;
import com.christiandstavares.vendas.service.EstadoService;
import com.christiandstavares.vendas.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

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
	}
}
