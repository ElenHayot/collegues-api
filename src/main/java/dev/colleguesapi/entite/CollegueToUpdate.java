package dev.colleguesapi.entite;

public class CollegueToUpdate {

	private String email;
	private String photoUrl;
	
	public CollegueToUpdate() {
		super();
	}

	public CollegueToUpdate(String argument) {
		this.email = argument;
		this.photoUrl = argument;
	}
	
	public CollegueToUpdate(String email, String photoUrl) {
		this.email = email;
		this.photoUrl = photoUrl;
	}


	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhotoUrl() {
		return photoUrl;
	}

	public void setPhotoUrl(String photoUrl) {
		this.photoUrl = photoUrl;
	}
	
}
