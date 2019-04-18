package dev.colleguesapi.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import dev.colleguesapi.entite.Collegue;
import dev.colleguesapi.entite.CollegueToUpdate;
import dev.colleguesapi.exception.CollegueNotFoundException;
import dev.colleguesapi.exception.InsertException;
import dev.colleguesapi.service.CollegueService;

@RestController
@RequestMapping("/collegues")
public class CollegueController {

	CollegueService service = new CollegueService();
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<?> listCollegues(@RequestParam(value= "name") String name) throws Exception {
		
		try{List<String> result = new ArrayList<>();
			for(Collegue c: service.findByName(name)) {
				result.add(c.getMatricule());
			}
			return ResponseEntity.ok(result);
		}catch(CollegueNotFoundException e){
			return ResponseEntity.status(404).body(e.getMessage());
		}
		
	}
	
	@RequestMapping(value = "/{matricule}", method = RequestMethod.GET)
	public  ResponseEntity<?> collegueByMatricule(@PathVariable String matricule) throws Exception {
		
		try {
			return ResponseEntity.ok(service.findByMatricule(matricule));
		}catch(CollegueNotFoundException e){
			return ResponseEntity.status(404).body(e.getMessage());
		}
		
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<?> addingCollegue(@RequestBody Collegue addCollegue) throws Exception {
		
		try{
			service.addCollegue(addCollegue);
			return ResponseEntity.ok("A new collegue has been saved with success!");
		}catch(InsertException e) {
			return ResponseEntity.status(400).body(e.getMessage());
		}
		
	}
	
	@RequestMapping(value = "/{matricule}", method = RequestMethod.PATCH)
	public ResponseEntity<?> updatingEmail(@PathVariable String matricule, @RequestBody CollegueToUpdate  updatingCollegue) throws Exception {
		
		try {
			if(updatingCollegue.getPhotoUrl() != null){
				service.updatePhotoUrl(matricule, updatingCollegue.getPhotoUrl());
				return ResponseEntity.ok("The collegue's photo is up to date!");
				
			}else if(updatingCollegue.getEmail() != null){
				service.updateEmail(matricule, updatingCollegue.getEmail());
				return ResponseEntity.ok("The collegue's mail is up to date!");
				
			} else {
				return ResponseEntity.status(400).body("Please enter the argument you want to update");
			}
		}catch(CollegueNotFoundException | InsertException e) {
			return ResponseEntity.status(400).body(e.getMessage());
		}
		
	}
	
	
	
}
























