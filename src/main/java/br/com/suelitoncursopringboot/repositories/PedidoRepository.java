package br.com.suelitoncursopringboot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.suelitoncursopringboot.domain.Categoria;
import br.com.suelitoncursopringboot.domain.Pedido;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Integer> {

}
