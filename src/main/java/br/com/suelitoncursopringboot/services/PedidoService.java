package br.com.suelitoncursopringboot.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.suelitoncursopringboot.domain.Pedido;
import br.com.suelitoncursopringboot.repositories.PedidoRepository;
import br.com.suelitoncursopringboot.services.exceptions.ObjectNotFoundException;

@Service
public class PedidoService {
	
	@Autowired
	private PedidoRepository repo;

	public Pedido find(Integer id) {
		Optional<Pedido> obj = repo.findById(id);
		if (obj.isEmpty()) {
			throw new  ObjectNotFoundException("Objeto não encontrato! ID: " + id + ", Tipo: " + Pedido.class.getName());
		}
		return obj.orElseThrow(() -> new ObjectNotFoundException("Not found"));
	}
	
}
