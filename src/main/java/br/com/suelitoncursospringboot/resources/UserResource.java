package br.com.suelitoncursospringboot.resources;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.view.RedirectView;

import br.com.suelitoncursospringboot.domain.User;
import br.com.suelitoncursospringboot.repositories.UserRepository;
import br.com.suelitoncursospringboot.util.FileUploadUtil;

@RestController
public class UserResource {

	@Autowired
	private UserRepository repo;

	@PostMapping("/users/save")
	public RedirectView saveUser(User user, @RequestParam("image") MultipartFile multipartFile) throws IOException {

		String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
		user.setPhotos(fileName);

		User savedUser = repo.save(user);

		String uploadDir = "user-photos/" + savedUser.getId();

		FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);

		return new RedirectView("/users", true);
	}
}
