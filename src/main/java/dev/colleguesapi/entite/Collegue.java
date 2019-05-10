package dev.colleguesapi.entite;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name="COLLEGUE")
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
	@Column (name = "PHOTOURL", length=500)
	private String photoUrl;
	
	private String password;
	
	@ElementCollection(fetch = FetchType.EAGER)
	private List<String> roles = new ArrayList<>();
	
	@OneToMany(mappedBy="collegue")
	private Set<Comment> comment;
	
	
	
	public Collegue() {
		super();
		comment = new HashSet<Comment>();
	}

	public Collegue(String name, String firstname, LocalDate birthdate, String email, String photoUrl, String password, List<String> roles) {
		super();
		this.name = name.toUpperCase();
		this.firstname = firstname;
		this.birthdate = birthdate;
		this.email = email;
		this.photoUrl = photoUrl;
		this.password = password;
		this.roles = roles;
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

	public List<String> getRoles() {
		return roles;
	}

	public void setRoles(List<String> roles) {
		this.roles = roles;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Set<Comment> getComment() {
		return comment;
	}

	public void setComment(Set<Comment> comment) {
		this.comment = comment;
	}
	
	
	
}
