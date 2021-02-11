package br.com.suelitoncursospringboot.services;

import org.springframework.web.multipart.MultipartFile;

import br.com.suelitoncursospringboot.domain.UploadedFile;

public interface FileUploadService {
	
	public void uploadToLocal(MultipartFile file);
	public UploadedFile uploadToDb(MultipartFile file);
	public UploadedFile downloadFile(String fileId);

}
