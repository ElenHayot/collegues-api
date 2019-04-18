package dev.colleguesapi.service;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.stereotype.Service;

import dev.colleguesapi.entite.Collegue;
import dev.colleguesapi.exception.CollegueNotFoundException;
import dev.colleguesapi.exception.InsertException;

@Service
public class CollegueService {

	private Map<String, Collegue> data = new HashMap<>();
	private LocalDate now = LocalDate.now();
	
	public CollegueService() {
		
		Collegue col1 = new Collegue("Raph", "Rojies", LocalDate.of(2012, 06, 30), "rojies.raph@society.com", "http://photopath/photo");
		Collegue col2 = new Collegue("juju", "francois", LocalDate.of(1994, 07, 07), "franc.juju@society.com", "photopath/photo");
		Collegue col3 = new Collegue("Son", "David", LocalDate.of(1987, 04, 21), "davidson@society.com", "http://photopath/photo");
				
		col1.setMatricule(UUID.randomUUID().toString());
		col2.setMatricule(UUID.randomUUID().toString());
		col3.setMatricule(UUID.randomUUID().toString());
		
		data.put(col1.getMatricule(), col1);
		data.put(col2.getMatricule(), col2);
		data.put(col3.getMatricule(), col3);
	}
	
	public List<Collegue> findByName(String findName) throws Exception {
		
		List<Collegue> resultList = new ArrayList<>();
		
		for(Map.Entry<String, Collegue> mapentry: data.entrySet()) {
			if (mapentry.getValue().getName().equalsIgnoreCase(findName))
				resultList.add(mapentry.getValue());
		}
		if(resultList == null || resultList.isEmpty()) {
			throw new CollegueNotFoundException("Sorry but there's no collegue with such a name");
		}else{return resultList;}
	}
	
	public Collegue findByMatricule(String findMatricule) throws Exception {
		
		Collegue result = data.get(findMatricule);
		
		if(result == null) {
			throw new CollegueNotFoundException("Sorry but there's no collegue with such a matricule");
		}else{return result;}
		
		
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
			data.put(addCollegue.getMatricule(), addCollegue);
		}
		
	}
	
	
	public void updateEmail(String matricule, String newEmail) throws Exception {
		
		Collegue collegue = findByMatricule(matricule);
		if(!newEmail.contains("@")){
			throw new InsertException("Please enter a convenient email adress (with a @ character)");
		}else {
			collegue.setEmail(newEmail);
		}
		
	}
	
	
	public void updatePhotoUrl(String matricule, String newPhotoUrl) throws Exception {
		
		Collegue collegue = findByMatricule(matricule);
		if(!newPhotoUrl.startsWith("http://")){
			throw new InsertException("Please enter a URL path for the photo (http://...)");
		}else {
			collegue.setPhotoUrl(newPhotoUrl);
		}
		
	}
	
	
}














