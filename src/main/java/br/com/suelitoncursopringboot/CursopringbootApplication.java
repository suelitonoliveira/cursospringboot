package br.com.suelitoncursopringboot;


import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.suelitoncursopringboot.domain.Categoria;
import br.com.suelitoncursopringboot.domain.Cidade;
import br.com.suelitoncursopringboot.domain.Cliente;
import br.com.suelitoncursopringboot.domain.Endereco;
import br.com.suelitoncursopringboot.domain.Estado;
import br.com.suelitoncursopringboot.domain.Produto;
import br.com.suelitoncursopringboot.domain.enums.TipoCliente;
import br.com.suelitoncursopringboot.repositories.CategoriaRepository;
import br.com.suelitoncursopringboot.repositories.CidadeRepository;
import br.com.suelitoncursopringboot.repositories.ClienteRepository;
import br.com.suelitoncursopringboot.repositories.EstadoRepository;
import br.com.suelitoncursopringboot.repositories.ProdutoRepository;

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
	

	public static void main(String[] args) {
		SpringApplication.run(CursopringbootApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		Categoria cat1 = new Categoria(null, "Informática");
		Categoria cat2 = new Categoria(null, "Escritório");
		
		Produto p1 = new Produto(null, "Computador", 2000.00);
		Produto p2 = new Produto(null, "Impressora", 800.00);
		Produto p3 = new Produto(null, "Mouse", 800.00);
		
		cat1.getProdutos().addAll(Arrays.asList(p1,p2,p3));
		cat2.getProdutos().addAll(Arrays.asList(p2));
		
		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1, cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));
		
		categoriaRepository.saveAll(Arrays.asList(cat1,cat2));
		produtoRepository.saveAll(Arrays.asList(p1,p2,p3));
		
		Estado est1 = new Estado(null, "Minas Gerais");
		Estado est2 = new Estado(null, "São Paulo");
		
		Cidade c1 = new Cidade(null, "Uberlandia", est1);
		Cidade c2 = new Cidade(null, "São Paulo", est2);
		Cidade c3 = new Cidade(null, "Campinas", est2);
		
		est1.getCidades().addAll(Arrays.asList(c1));
		est2.getCidades().addAll(Arrays.asList(c2, c3));
		
		estadoRepository.saveAll(Arrays.asList(est1,est2));
		cidadeRepostory.saveAll(Arrays.asList(c1,c2,c3));
		
		Cliente cli1 = new Cliente(null, "Maria Silva", "61130604039", "maria@gmail.com", TipoCliente.PESSOAFISICA);
		cli1.getTelefones().addAll(Arrays.asList("9999999997","98984984"));
		
		clienteRepository.save(cli1);
		
		
		/*
		 * Endereco e1= new Endereco(null, "Rua Flores", "300", "Apto 303", "Jardim",
		 * "38220834", cli1, c1); Endereco e2 = new Endereco(id, logradouro, numero,
		 * complemento, bairro, cep, cliente, cidade)
		 */
		
	}
	
	

}
