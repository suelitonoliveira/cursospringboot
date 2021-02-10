package br.com.suelitoncursospringboot.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "users")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(nullable = true, length = 64)
	private String photos;

	public User() {

	}

	public User(String photos) {
		super();
		this.photos = photos;
	}

	public User(String photos, Integer id) {
		super();
		this.photos = photos;
		this.id = id;
	}

	public String getPhotos() {
		return photos;
	}

	public void setPhotos(String photos) {
		this.photos = photos;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Transient
	public String getPhotosImagePath() {
		if (photos == null || id == null)
			return null;

		return "/user-photos/" + id + "/" + photos;
	}

}
