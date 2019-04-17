package dev.colleguesapi.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import dev.colleguesapi.entite.Collegue;
import dev.colleguesapi.exception.CollegueNotFoundException;
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
	public  ResponseEntity<?> collegueComplete(@PathVariable String matricule) throws Exception {
		
		try {
			return ResponseEntity.ok(service.findByMatricule(matricule));
		}catch(CollegueNotFoundException e){
			return ResponseEntity.status(404).body(e.getMessage());
		}
		
		
	}
	
}
