package dev.colleguesapi.entite;

public class Collegue {

	private String matricule;
	private String name;
	private String firstname;
	private String email;
	private String birthdate;
	private String photoUrl;
	
	
	
	public Collegue() {
		super();
	}

	public Collegue(String name, String firstname, String birthdate) {
		super();
		this.name = name;
		this.firstname = firstname;
		this.birthdate = birthdate;
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getBirthdate() {
		return birthdate;
	}
	public void setBirthdate(String birthdate) {
		this.birthdate = birthdate;
	}
	public String getPhotoUrl() {
		return photoUrl;
	}
	public void setPhotoUrl(String photoUrl) {
		this.photoUrl = photoUrl;
	}
	
	
	
}
