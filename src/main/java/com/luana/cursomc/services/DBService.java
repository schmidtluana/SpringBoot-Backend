package com.luana.cursomc.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.luana.cursomc.domain.Categoria;
import com.luana.cursomc.domain.Cidade;
import com.luana.cursomc.domain.Cliente;
import com.luana.cursomc.domain.Endereco;
import com.luana.cursomc.domain.Estado;
import com.luana.cursomc.domain.ItemPedido;
import com.luana.cursomc.domain.Pagamento;
import com.luana.cursomc.domain.PagamentoComBoleto;
import com.luana.cursomc.domain.PagamentoComCartao;
import com.luana.cursomc.domain.Pedido;
import com.luana.cursomc.domain.Produto;
import com.luana.cursomc.domain.enums.EstadoPagamento;
import com.luana.cursomc.domain.enums.Perfil;
import com.luana.cursomc.domain.enums.TipoCliente;
import com.luana.cursomc.repositories.CategoriaRepository;
import com.luana.cursomc.repositories.CidadeRepository;
import com.luana.cursomc.repositories.ClienteRepository;
import com.luana.cursomc.repositories.EnderecoRepository;
import com.luana.cursomc.repositories.EstadoRepository;
import com.luana.cursomc.repositories.ItemPedidoRepository;
import com.luana.cursomc.repositories.PagamentoRepository;
import com.luana.cursomc.repositories.PedidoRepository;
import com.luana.cursomc.repositories.ProdutoRepository;

@Service
public class DBService {
 
	@Autowired
	private BCryptPasswordEncoder pe;
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
	@Autowired
	private PedidoRepository pedidoRepository;
	@Autowired
	private PagamentoRepository pagamentoRepository;
	@Autowired
	private ItemPedidoRepository itemPedidoRepository;
	
	public void instantiateTestDatabase() throws ParseException {
		Categoria cat1 = new Categoria(null, "Inform??tica");
		Categoria cat2 = new Categoria(null, "Escrit??rio");
		Categoria cat3 = new Categoria(null, "Cama mesa e banho");
		Categoria cat4 = new Categoria(null, "Eletr??nicos");
		Categoria cat5 = new Categoria(null, "Jardinagem");
		Categoria cat6 = new Categoria(null, "Decora????o");
		Categoria cat7 = new Categoria(null, "Perfumaria");
		
		
		Produto p1 = new Produto(null,"Computador", 2000.00);
		Produto p2 = new Produto(null,"Impressora", 800.00);
		Produto p3 = new Produto(null,"Mouse", 80.00);
		Produto p4 = new Produto(null, "Mesa de escrit??rio", 300.00);
		Produto p5 = new Produto(null, "Toalha", 50.00);
		Produto p6 = new Produto(null, "Colcha", 200.00);
		Produto p7 = new Produto(null, "TV true color", 1200.00);
		Produto p8 = new Produto(null, "Ro??adeira", 800.00);
		Produto p9 = new Produto(null, "Abajour", 100.00);
		Produto p10 = new Produto(null, "Pendente", 180.00);
		Produto p11 = new Produto(null, "Shampoo", 90.00);
		
		cat1.getProdutos().addAll(Arrays.asList(p1,p2,p3));
		cat2.getProdutos().addAll(Arrays.asList(p2,p4));
		cat3.getProdutos().addAll(Arrays.asList(p5, p6));
		cat4.getProdutos().addAll(Arrays.asList(p1, p2, p3, p7));
		cat5.getProdutos().addAll(Arrays.asList(p8));
		cat6.getProdutos().addAll(Arrays.asList(p9, p10));
		cat7.getProdutos().addAll(Arrays.asList(p11));
		
		p1.getCategorias().addAll(Arrays.asList(cat1, cat4));
		p2.getCategorias().addAll(Arrays.asList(cat1, cat2, cat4));
		p3.getCategorias().addAll(Arrays.asList(cat1, cat4));
		p4.getCategorias().addAll(Arrays.asList(cat2));
		p5.getCategorias().addAll(Arrays.asList(cat3));
		p6.getCategorias().addAll(Arrays.asList(cat3));
		p7.getCategorias().addAll(Arrays.asList(cat4));
		p8.getCategorias().addAll(Arrays.asList(cat5));
		p9.getCategorias().addAll(Arrays.asList(cat6));
		p10.getCategorias().addAll(Arrays.asList(cat6));
		p11.getCategorias().addAll(Arrays.asList(cat7));
		
		categoriaRepository.saveAll(Arrays.asList(cat1,cat2,cat3,cat4,cat5,cat6,cat7)); //array autom??tico
		produtoRepository.saveAll(Arrays.asList(p1,p2,p3, p4, p5, p6, p7, p8, p9, p10, p11));
		
		Estado e1 = new Estado(null,"Minas Gerais");
		Estado e2 = new Estado(null,"S??o Paulo");
		
		Cidade c1 = new Cidade(null,"Uberl??ndia", e1);
		Cidade c2 = new Cidade(null,"S??o Paulo", e2);
		Cidade c3 = new Cidade(null,"Campinas", e2);
		
		e1.getCidades().addAll(Arrays.asList(c1));
		e2.getCidades().addAll(Arrays.asList(c2,c3));
		
		estadoRepository.saveAll(Arrays.asList(e1,e2));
		cidadeRepository.saveAll(Arrays.asList(c1,c2,c3));
		
		Cliente cli1 = new Cliente(null,"Maria Silva", "maria@gmail.com", "7777777", TipoCliente.PESSSOAFISICA, pe.encode("123")  );
		cli1.getTelefones().addAll(Arrays.asList("333333","444444"));
		
		Cliente cli2 = new Cliente(null,"Ana Beatriz", "ana@gmail.com", "98118739090", TipoCliente.PESSSOAFISICA, pe.encode("123")  );
		cli2.getTelefones().addAll(Arrays.asList("45678896","45354343"));
		cli2.addPerfil(Perfil.ADMIN);
		
		Endereco en1 = new Endereco(null, "Rua Flores", "300", "Apto 203", "Jardim", "335644", cli1, c1);
		Endereco en2 = new Endereco(null, "Avenida Matos", "115", "Sala 100", "Centro", "335245", cli1, c2);
		Endereco en3 = new Endereco(null, "Avenida Floriano", "8915", "Andar 404", "Zona", "67890654", cli2, c2);

		cli1.getEnderecos().addAll(Arrays.asList(en1,en2));
		cli2.getEnderecos().addAll(Arrays.asList(en3));

		clienteRepository.saveAll(Arrays.asList(cli1,cli2));
		enderecoRepository.saveAll(Arrays.asList(en1,en2,en3));
		
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		
		Pedido ped1 = new Pedido(null, sdf.parse("30/09/2017 10:32"), cli1, en1);
		Pedido ped2 = new Pedido(null, sdf.parse("10/10/2017 19:35"), cli1, en2);
		
		Pagamento pagto1 = new PagamentoComCartao(null,EstadoPagamento.QUITADO, ped1, 6);
		ped1.setPagamento(pagto1);
		
		Pagamento pagto2 = new PagamentoComBoleto(null,EstadoPagamento.PENDENTE, ped2, sdf.parse("20/10/2017 00:00"), null);
		ped2.setPagamento(pagto2);
		
		cli1.getPedidos().addAll(Arrays.asList(ped1,ped2));
	
		pedidoRepository.saveAll(Arrays.asList(ped1,ped2));
		pagamentoRepository.saveAll(Arrays.asList(pagto1,pagto2));
	
		ItemPedido ip1 = new ItemPedido(ped1, p1, 0.00, 1, 2000.00);
		ItemPedido ip2 = new ItemPedido(ped1, p3, 0.00, 2, 80.00);
		ItemPedido ip3 = new ItemPedido(ped2, p2, 100.00, 1, 800.00);
	
		ped1.getItens().addAll(Arrays.asList(ip1,ip2));
		ped1.getItens().addAll(Arrays.asList(ip3));
		
		p1.getItens().addAll(Arrays.asList(ip1));
		p2.getItens().addAll(Arrays.asList(ip3));
		p3.getItens().addAll(Arrays.asList(ip2));
		
		itemPedidoRepository.saveAll(Arrays.asList(ip1,ip2, ip3));
	}
}
