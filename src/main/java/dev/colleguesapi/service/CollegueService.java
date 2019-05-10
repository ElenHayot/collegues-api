package dev.colleguesapi.service;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dev.colleguesapi.entite.Collegue;
import dev.colleguesapi.entite.Trombinoscope;
import dev.colleguesapi.exception.CollegueNotFoundException;
import dev.colleguesapi.exception.InsertException;
import dev.colleguesapi.repository.CollegueRepository;

@Service
public class CollegueService {
	
	@Autowired 
	CollegueRepository repo;
	private LocalDate now = LocalDate.now();
	
	public CollegueService() {
		//Constructor
	}
	
	public List<Collegue> findCollegueByName(String findName) throws Exception {
		
		List<Collegue> collegueFound = repo.findByName(findName.toUpperCase());
		if(collegueFound == null || collegueFound.isEmpty()) {
			throw new CollegueNotFoundException("Sorry but there's no collegue with such a name");
		}else{return collegueFound;}
		
	}
	
	public Collegue findCollegueByMatricule(String findMatricule) throws Exception {
		
		Collegue collegueFound = repo.findByMatricule(findMatricule);
		if(collegueFound == null) {
			throw new CollegueNotFoundException("Sorry but there's no collegue with such a matricule");
		}else{return collegueFound;}
		
	}
	
	public List<String> findCollegueByEmail(String email) throws Exception {
		
		Optional<Collegue> collegueFound = repo.findByEmail(email);
		List<String> listToReturn = new ArrayList<>();
		if(collegueFound == null) {
			throw new CollegueNotFoundException("Oo, no colleague found. Is your email address correct?");
		}else{
			listToReturn.add(collegueFound.get().getMatricule());
			listToReturn.add(collegueFound.get().getName());
			listToReturn.add(collegueFound.get().getFirstname());
			listToReturn.addAll(collegueFound.get().getRoles());
			return listToReturn;
			}
		
	}
	
	public void addCollegue(Collegue addCollegue) throws Exception {
		
		if(addCollegue.getName().length() < 3) {
			throw new InsertException("Please enter a name that contains more than 3 characters");
		}else if(addCollegue.getFirstname().length() < 3) {
			throw new InsertException("Please enter a firstname that contains more than 3 characters");
		}else if(!addCollegue.getEmail().contains("@")){
			throw new InsertException("Please enter a convenient email adress (with a @ character)");
		}else if(!addCollegue.getPhotoUrl().startsWith("http")) {
			throw new InsertException("Please enter a URL path for the photo (http://...)");
		}else if(addCollegue.getBirthdate() == null) {
			throw new InsertException("Please enter a birthdate");
		}else if(Period.between(addCollegue.getBirthdate(), now).getYears() < 18){
			throw new InsertException("Sorry but you must be more than 18 to access");
		}else {
			addCollegue.setMatricule(UUID.randomUUID().toString());
			repo.save(addCollegue);
		}
		
	}
	
	@Transactional
	public void updateEmail(String matricule, String newEmail) throws Exception {
		
		Collegue collegue = findCollegueByMatricule(matricule);
		if(!newEmail.contains("@")){
			throw new InsertException("Please enter a convenient email adress (with a @ character)");
		}else {
			collegue.setEmail(newEmail);
		}
		
	}
	
	@Transactional
	public void updatePhotoUrl(String matricule, String newPhotoUrl) throws Exception {
		
		Collegue collegue = findCollegueByMatricule(matricule);
		if(!newPhotoUrl.startsWith("http")){
			throw new InsertException("Please enter a URL path for the photo (http://...)");
		}else {
			collegue.setPhotoUrl(newPhotoUrl);
		}
		
	}

	public void setRepo(CollegueRepository repo) {
		this.repo = repo;
	}

	public List<Trombinoscope> sendPhoto() {
		
		List<Trombinoscope> trombi = new ArrayList<>();;
		for(Collegue col:repo.findAll()){
			trombi.add(new Trombinoscope(col.getMatricule(), col.getPhotoUrl()));
		}
		return trombi;
		
	}
	
	
	
	
}














