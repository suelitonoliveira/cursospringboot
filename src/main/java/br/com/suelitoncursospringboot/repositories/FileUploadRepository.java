package br.com.suelitoncursospringboot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.suelitoncursospringboot.domain.UploadedFile;

@Repository
public interface FileUploadRepository extends JpaRepository<UploadedFile, String> {

}
