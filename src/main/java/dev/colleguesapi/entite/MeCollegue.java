package dev.colleguesapi.entite;

import java.util.List;

public class MeCollegue {

	private String matricule;
	private String name;
	private String firstname;
	private List<String> role;
	private String photoUrl;
	private String email;
	
	public MeCollegue() {
		super();
	}

	public MeCollegue(String matricule, String name, String firstname, List<String> role) {
		super();
		this.matricule = matricule;
		this.name = name;
		this.firstname = firstname;
		this.role = role;
	}
	
	public MeCollegue(String matricule, String name, String firstname, List<String> role, String photoUrl) {
		super();
		this.matricule = matricule;
		this.name = name;
		this.firstname = firstname;
		this.role = role;
		this.photoUrl = photoUrl;
	}

	public MeCollegue(String matricule, String name, String firstname, List<String> role, String photoUrl,
			String email) {
		super();
		this.matricule = matricule;
		this.name = name;
		this.firstname = firstname;
		this.role = role;
		this.photoUrl = photoUrl;
		this.email = email;
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
		this.name = name;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public List<String> getRole() {
		return role;
	}
	public void setRole(List<String> role) {
		this.role = role;
	}

	public String getPhotoUrl() {
		return photoUrl;
	}

	public void setPhotoUrl(String photoUrl) {
		this.photoUrl = photoUrl;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
}
