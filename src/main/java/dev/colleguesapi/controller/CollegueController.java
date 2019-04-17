package dev.colleguesapi.controller;

import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import dev.colleguesapi.entite.Collegue;
import dev.colleguesapi.service.CollegueService;

@RestController
@RequestMapping("/collegues")
public class CollegueController {

	CollegueService service = new CollegueService();
	
	@RequestMapping(method = RequestMethod.GET)
	public List<Collegue> listCollegues(@RequestParam(value= "name") String name) {
		
		return service.findByName(name);
				
		
	}
	
}
