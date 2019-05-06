package dev.colleguesapi.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
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
@CrossOrigin
public class CollegueController {

	@Autowired
	private CollegueService service;
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<?> listCollegues(@RequestParam(value= "name") String name) throws Exception {
		try{List<String> result = new ArrayList<>();
			for(Collegue c: service.findCollegueByName(name)) {
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
			return ResponseEntity.ok(service.findCollegueByMatricule(matricule));
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
			if(updatingCollegue.getPhotoUrl() != null && updatingCollegue.getEmail() != null){
				service.updatePhotoUrl(matricule, updatingCollegue.getPhotoUrl());
				service.updateEmail(matricule, updatingCollegue.getEmail());
				return ResponseEntity.ok("The colleague's profile is up to date!");
				
			}else {
				return ResponseEntity.status(400).body("Please enter the argument you want to update");
			}
			
		}catch(CollegueNotFoundException | InsertException e) {
			return ResponseEntity.status(400).body(e.getMessage());
		}
		
	}
	
	
	
}
























