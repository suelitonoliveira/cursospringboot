package br.com.suelitoncursospringboot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.suelitoncursospringboot.domain.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

}
