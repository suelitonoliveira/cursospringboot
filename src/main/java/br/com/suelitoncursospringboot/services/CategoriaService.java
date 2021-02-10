package br.com.suelitoncursospringboot.services;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import br.com.suelitoncursospringboot.domain.Categoria;
import br.com.suelitoncursospringboot.dto.CategoriaDTO;
import br.com.suelitoncursospringboot.repositories.CategoriaRepository;
import br.com.suelitoncursospringboot.services.exceptions.DataIntegrityException;
import br.com.suelitoncursospringboot.services.exceptions.ObjectNotFoundException;

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
	
	public Categoria insert(Categoria obj) {
		obj.setId(null);
		return repo.save(obj);
	}
	
	public Categoria update(Categoria obj) {
		find(obj.getId());
		return repo.save(obj);
	}
	
	@Transactional
	public void delete(Integer id) {
		find(id);
		try {
			repo.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possivel excluir uma categoria com produtos associados");
		}

	}
	
	public List<Categoria> findAll(){
		return repo.findAll();
	}
	
	public Page<Categoria> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
	}
	
	public Categoria fromDTO(CategoriaDTO objDto) {
		return new Categoria(objDto.getId(), objDto.getNome(),objDto.getFoneBrasil(),objDto.getFoneInternacional());
	}
	

}
