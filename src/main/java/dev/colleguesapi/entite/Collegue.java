package dev.colleguesapi.entite;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;


@Entity
public class Collegue {

	@Id
	private String matricule;
	@Column (name = "NAME")
	private String name;
	@Column (name = "FIRSTNAME")
	private String firstname;
	@Column (name = "EMAIL")
	private String email;
	@Column (name = "BIRTHDATE")
	private LocalDate birthdate;
	@Column (name = "PHOTOURL")
	private String photoUrl;
	
	
	
	public Collegue() {
		super();
	}

	public Collegue(String name, String firstname, LocalDate birthdate, String email, String photoUrl) {
		super();
		this.name = name.toUpperCase();
		this.firstname = firstname;
		this.birthdate = birthdate;
		this.email = email;
		this.photoUrl = photoUrl;
	}
	
	public String getMatricule() {
		return matricule;
	}
	public void setMatricule(String matricule) {
		this.matricule = matricule;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name.toUpperCase();
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public LocalDate getBirthdate() {
		return birthdate;
	}
	public void setBirthdate(LocalDate birthdate) {
		this.birthdate = birthdate;
	}
	public String getPhotoUrl() {
		return photoUrl;
	}
	public void setPhotoUrl(String photoUrl) {
		this.photoUrl = photoUrl;
	}
	
	
	
}
