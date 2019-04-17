package dev.colleguesapi.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import dev.colleguesapi.entite.Collegue;

public class CollegueService {

	private Map<String, Collegue> data = new HashMap<>();
	
	public CollegueService() {
		
		Collegue col1 = new Collegue("Raph", "Rojies", "21/12/2012");
		Collegue col2 = new Collegue("juju", "francois", "13/11/1998");
		
		col1.setEmail(col1.getFirstname() + col1.getName() + "@society.com");
		col2.setEmail(col2.getFirstname() + col2.getName() + "@society.com");
		
		col1.setMatricule(UUID.randomUUID().toString());
		col2.setMatricule(UUID.randomUUID().toString());
		
		col1.setPhotoUrl("URLphoto1");
		col2.setPhotoUrl("URLphoto2");
		
		data.put(col1.getMatricule(), col1);
		data.put(col2.getMatricule(), col2);
	}
	
	public List<Collegue> findByName(String findName) {
		
		List<Collegue> resultList = new ArrayList<>();
		
		for(Map.Entry<String, Collegue> mapentry: data.entrySet()) {
			resultList.add(mapentry.getValue());
		}
		
		return resultList;
		
	}
	
}
