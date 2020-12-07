package br.com.suelitoncursopringboot.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.suelitoncursopringboot.domain.Categoria;
import br.com.suelitoncursopringboot.repositories.CategoriaRepository;
import br.com.suelitoncursopringboot.services.exceptions.ObjectNotFoundException;

@Service
public class CategoriaService {
	
	@Autowired
	private CategoriaRepository repo;

	public Categoria find(Integer id) {
		Optional<Categoria> obj = repo.findById(id);
		if (obj.isEmpty()) {
			throw new  ObjectNotFoundException("Objeto não encontrato! ID: " + id + ", Tipo: " + Categoria.class.getName());
		}
		return obj.orElseThrow(() -> new ObjectNotFoundException("Not found"));
	}
	
}
