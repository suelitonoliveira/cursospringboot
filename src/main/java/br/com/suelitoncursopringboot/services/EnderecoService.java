package br.com.suelitoncursopringboot.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.suelitoncursopringboot.domain.Endereco;
import br.com.suelitoncursopringboot.repositories.EnderecoRepository;
import br.com.suelitoncursopringboot.services.exceptions.ObjectNotFoundException;

@Service
public class EnderecoService {
	
	@Autowired
	private EnderecoRepository repo;

	public Endereco find(Integer id) {
		Optional<Endereco> obj = repo.findById(id);
		if (obj.isEmpty()) {
			throw new  ObjectNotFoundException("Objeto não encontrato! ID: " + id + ", Tipo: " + Endereco.class.getName());
		}
		return obj.orElseThrow(() -> new ObjectNotFoundException("Not found"));
	}
	
}
