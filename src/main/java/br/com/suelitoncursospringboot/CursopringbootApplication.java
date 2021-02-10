package br.com.suelitoncursospringboot;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.suelitoncursospringboot.domain.Categoria;
import br.com.suelitoncursospringboot.domain.Cidade;
import br.com.suelitoncursospringboot.domain.Cliente;
import br.com.suelitoncursospringboot.domain.Endereco;
import br.com.suelitoncursospringboot.domain.Estado;
import br.com.suelitoncursospringboot.domain.ItemPedido;
import br.com.suelitoncursospringboot.domain.Pagamento;
import br.com.suelitoncursospringboot.domain.PagamentoComBoleto;
import br.com.suelitoncursospringboot.domain.PagamentoComCartao;
import br.com.suelitoncursospringboot.domain.Pedido;
import br.com.suelitoncursospringboot.domain.Produto;
import br.com.suelitoncursospringboot.domain.enums.EstadoPagamento;
import br.com.suelitoncursospringboot.domain.enums.TipoCliente;
import br.com.suelitoncursospringboot.repositories.CategoriaRepository;
import br.com.suelitoncursospringboot.repositories.CidadeRepository;
import br.com.suelitoncursospringboot.repositories.ClienteRepository;
import br.com.suelitoncursospringboot.repositories.EnderecoRepository;
import br.com.suelitoncursospringboot.repositories.EstadoRepository;
import br.com.suelitoncursospringboot.repositories.ItemPedidoRepository;
import br.com.suelitoncursospringboot.repositories.PagamentoRepository;
import br.com.suelitoncursospringboot.repositories.PedidoRepository;
import br.com.suelitoncursospringboot.repositories.ProdutoRepository;

@SpringBootApplication
public class CursopringbootApplication implements CommandLineRunner {

	@Autowired
	private CategoriaRepository categoriaRepository;

	@Autowired
	private ProdutoRepository produtoRepository;

	@Autowired
	private EstadoRepository estadoRepository;

	@Autowired
	private CidadeRepository cidadeRepostory;

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

	public static void main(String[] args) {
		SpringApplication.run(CursopringbootApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		Categoria cat1 = new Categoria(null, "Informática","984848484","999999");
		Categoria cat2 = new Categoria(null, "Escritório","984848484","999999");
		/*
		 * Categoria cat3 = new Categoria(null,
		 * "Cama Mesa e Banho","984848484","999999"); Categoria cat4 = new
		 * Categoria(null, "Eletronicos","984848484","999999"); Categoria cat5 = new
		 * Categoria(null, "Jardinagem","984848484","999999"); Categoria cat6 = new
		 * Categoria(null, "Decoração","984848484","999999"); Categoria cat7 = new
		 * Categoria(null, "Perfumaria","984848484","999999");
		 */

		Produto p1 = new Produto(null, "Computador", 2000.00);
		Produto p2 = new Produto(null, "Impressora", 800.00);
		Produto p3 = new Produto(null, "Mouse", 800.00);

		cat1.getProdutos().addAll(Arrays.asList(p1, p2, p3));
		cat2.getProdutos().addAll(Arrays.asList(p2));

		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1, cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));

		categoriaRepository.saveAll(Arrays.asList(cat1, cat2));
		produtoRepository.saveAll(Arrays.asList(p1, p2, p3));

		Estado est1 = new Estado(null, "Minas Gerais");
		Estado est2 = new Estado(null, "São Paulo");

		Cidade c1 = new Cidade(null, "Uberlandia", est1);
		Cidade c2 = new Cidade(null, "São Paulo", est2);
		Cidade c3 = new Cidade(null, "Campinas", est2);

		est1.getCidades().addAll(Arrays.asList(c1));
		est2.getCidades().addAll(Arrays.asList(c2, c3));

		estadoRepository.saveAll(Arrays.asList(est1, est2));
		cidadeRepostory.saveAll(Arrays.asList(c1, c2, c3));

		Cliente cli1 = new Cliente(null, "Maria Silva", "61130604039", "maria@gmail.com", TipoCliente.PESSOAFISICA);
		cli1.getTelefones().addAll(Arrays.asList("9999999997", "98984984"));
		clienteRepository.save(cli1);

		Endereco e1 = new Endereco(null, "Rua Flores", "300", "Apto 303", "Jardim", "38220834", cli1, c1);
		Endereco e2 = new Endereco(null, "logradouro", "numero", "complemento", " bairro", "cep", cli1, c2);

		cli1.getEnderecos().addAll(Arrays.asList(e1, e2));
		enderecoRepository.saveAll(Arrays.asList(e1, e2));

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");

		Pedido ped1 = new Pedido(null, sdf.parse("30/09/2017 10:32"), cli1, e1);
		Pedido ped2 = new Pedido(null, sdf.parse("10/10/2017 10:32"), cli1, e2);

		Pagamento pagto1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO, ped1, 6);
		ped1.setPagamento(pagto1);

		Pagamento pagto2 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, ped2, sdf.parse("1220/10/2017 00:00"),
				null);
		ped2.setPagamento(pagto2);

		cli1.getPedidos().addAll(Arrays.asList(ped1, ped2));

		pedidoRepository.saveAll(Arrays.asList(ped1, ped2));
		pagamentoRepository.saveAll(Arrays.asList(pagto1, pagto2));
		
		ItemPedido ip1 = new ItemPedido(ped1, p1, 0.00, 1, 2000.00);
		ItemPedido ip2 = new ItemPedido(ped1, p3, 0.00, 2, 80.00);
		ItemPedido ip3 = new ItemPedido(ped2, p2, 100.00, 1, 800.00);
		
		ped1.getItens().addAll(Arrays.asList(ip1,ip2));
		ped2.getItens().addAll(Arrays.asList(ip3));
		
		p1.getItens().addAll(Arrays.asList(ip1));
		p2.getItens().addAll(Arrays.asList(ip3));
		p3.getItens().addAll(Arrays.asList(ip2));
	
		itemPedidoRepository.saveAll(Arrays.asList(ip1,ip2,ip3));
		
	}

}