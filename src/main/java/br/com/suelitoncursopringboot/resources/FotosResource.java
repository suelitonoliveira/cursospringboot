package br.com.suelitoncursopringboot.resources;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.suelitoncursopringboot.domain.Foto;
import br.com.suelitoncursopringboot.services.FileUploadUtil;
import br.com.suelitoncursopringboot.storage.Disco;

@RestController
@RequestMapping("/fotos")
public class FotosResource {

	@Autowired
	private Disco disco;
	@Autowired
	private FileUploadUtil fotoService;

	/*
	 * @PostMapping("/fotos/save") public String uploadFoto(Foto
	 * foto, @RequestParam("file") MultipartFile file) { disco.salvarFoto(file);
	 * 
	 * //store the file //store file name to entitu class //save entity }
	 */

	@PostMapping("/fotos/save")
	public String updateFoto(@ModelAttribute(name = "foto") Foto foto, RedirectAttributes ra,
			@RequestParam("fileImage") MultipartFile multpartFile) throws IOException {
		String fileName = StringUtils.cleanPath(multpartFile.getOriginalFilename());
		foto.setLogo(fileName);
		Foto savedFoto =  fotoService.save(foto);
		String uploadDir = "/imagem-logos/" + savedFoto.getId();
		Path uploadPath = Paths.get(uploadDir);
		if (!Files.exists(uploadPath)) {
			Files.createDirectories(uploadPath);
		}
		try {
			InputStream inputStream = multpartFile.getInputStream();
			Path filePath = uploadPath.resolve(fileName);
			Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
			
		} catch (IOException e) {
			throw new IOException("Could not salve uloaded file: " + fileName);
		}
	
		
		ra.addFlashAttribute("message", "The brande has been saved successfully.");
		return "redirect:/fotos";
	}

}
