package br.com.suelitoncursospringboot.services;

import java.net.URI;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import br.com.suelitoncursospringboot.domain.Cliente;
import br.com.suelitoncursospringboot.repositories.ClienteRepository;
import br.com.suelitoncursospringboot.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository repo;

	public Cliente find(Integer id) {
		Optional<Cliente> obj = repo.findById(id);
		if (obj.isEmpty()) {
			throw new  ObjectNotFoundException("Objeto não encontrato! ID: " + id + ", Tipo: " + Cliente.class.getName());
		}
		return obj.orElseThrow(() -> new ObjectNotFoundException("Not found"));
	}
	
	
}
