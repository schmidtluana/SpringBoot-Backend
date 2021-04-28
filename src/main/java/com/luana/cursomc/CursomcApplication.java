package com.luana.cursomc;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.luana.cursomc.domain.Categoria;
import com.luana.cursomc.domain.Cidade;
import com.luana.cursomc.domain.Cliente;
import com.luana.cursomc.domain.Endereco;
import com.luana.cursomc.domain.Estado;
import com.luana.cursomc.domain.Produto;
import com.luana.cursomc.domain.enums.TipoCliente;
import com.luana.cursomc.repositories.CategoriaRepository;
import com.luana.cursomc.repositories.CidadeRepository;
import com.luana.cursomc.repositories.ClienteRepository;
import com.luana.cursomc.repositories.EnderecoRepository;
import com.luana.cursomc.repositories.EstadoRepository;
import com.luana.cursomc.repositories.ProdutoRepository;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner {

	@Autowired
	private CategoriaRepository categoriaRepository;
	@Autowired
	private ProdutoRepository produtoRepository;
	@Autowired
	private CidadeRepository cidadeRepository;
	@Autowired
	private EstadoRepository estadoRepository;
	@Autowired
	private ClienteRepository clienteRepository;
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	
	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Categoria cat1 = new Categoria(null, "Informática");
		Categoria cat2 = new Categoria(null, "Escritório");
		
		Produto p1 = new Produto(null,"Computador", 2000.00);
		Produto p2 = new Produto(null,"Impressora", 800.00);
		Produto p3 = new Produto(null,"Mouse", 80.00);
		
		cat1.getProdutos().addAll(Arrays.asList(p1,p2,p3));
		cat2.getProdutos().addAll(Arrays.asList(p2));
		
		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1,cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));
		
		
		categoriaRepository.saveAll(Arrays.asList(cat1,cat2)); //array automático
		produtoRepository.saveAll(Arrays.asList(p1,p2,p3));
		
		Estado e1 = new Estado(null,"Minas Gerais");
		Estado e2 = new Estado(null,"São Paulo");
		
		Cidade c1 = new Cidade(null,"Uberlândia", e1);
		Cidade c2 = new Cidade(null,"São Paulo", e2);
		Cidade c3 = new Cidade(null,"Campinas", e2);
		
		e1.getCidades().addAll(Arrays.asList(c1));
		e2.getCidades().addAll(Arrays.asList(c2,c3));
		
		estadoRepository.saveAll(Arrays.asList(e1,e2));
		cidadeRepository.saveAll(Arrays.asList(c1,c2,c3));
		
		Cliente cli1 = new Cliente(null,"Maria Silva", "maria@gmail.com", "7777777", TipoCliente.PESSSOAFISICA );
		
		cli1.getTelefones().addAll(Arrays.asList("333333","444444"));
		
		Endereco en1 = new Endereco(null, "Rua Flores", "300", "Apto 203", "Jardim", "335644", cli1, c1);
		Endereco en2 = new Endereco(null, "Avenida Matos", "115", "Sala 100", "Centro", "335245", cli1, c2);
	
		cli1.getEnderecos().addAll(Arrays.asList(en1,en2));
	
		clienteRepository.saveAll(Arrays.asList(cli1));
		enderecoRepository.saveAll(Arrays.asList(en1,en2));
		
	}

}
