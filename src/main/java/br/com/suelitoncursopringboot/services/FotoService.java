package br.com.suelitoncursopringboot.services;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;

import javax.management.RuntimeErrorException;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;




@Service
public class FotoService {
	
	
	public URI uploadFile(MultipartFile multipartFile) {
		
		try {
			InputStream is = multipartFile.getInputStream();
			String contentType = multipartFile.getContentType();
			String fileName = multipartFile.getOriginalFilename();
			return uploadFile(is, fileName, contentType);
		} catch (IOException e) {
				throw new RuntimeErrorException(null, "Erro de IO: " + e.getMessage());
		}
	
	}
	
	public URI uploadFile(InputStream is, String fileName, String contentType) {
		return null;
		
	}

}

