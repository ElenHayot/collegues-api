package dev.colleguesapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import dev.colleguesapi.entite.Comment;
import dev.colleguesapi.exception.CollegueNotFoundException;
import dev.colleguesapi.exception.CommentNotFoundException;
import dev.colleguesapi.exception.InsertException;
import dev.colleguesapi.service.CommentService;

@RestController
@RequestMapping("/collegues/gallerie")
@CrossOrigin
public class CommentController {
	
	@Autowired
	private CommentService service;
	
	@RequestMapping(value="/{matricule}/comments", method = RequestMethod.POST)
	public ResponseEntity<?> addingComment(@PathVariable String matricule, @RequestBody Comment addCommment) throws Exception {
		
		try{
			service.addNewComment(matricule, addCommment);
			return ResponseEntity.ok(addCommment);
		}catch(InsertException e) {
			return ResponseEntity.status(400).body(e.getMessage());
		}
		
	}
	
	
	@RequestMapping(value ="/{colMatricule}/comments", method = RequestMethod.GET)
	public ResponseEntity<?> sendingComment(@PathVariable String colMatricule) throws Exception {
		
		try {
			return ResponseEntity.ok(service.sendComment(colMatricule));
		}catch(CollegueNotFoundException e){
			return ResponseEntity.status(404).body(e.getMessage());
		}
	}
	
	@RequestMapping(value = "/{matricule}/comments/{commentMatricule}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deletingComment(@PathVariable int commentMatricule) throws Exception {
		
		try {
			service.deleteComment(commentMatricule);
			return ResponseEntity.ok(true);
			
		}catch(CommentNotFoundException e) {
			return ResponseEntity.status(404).body(e.getMessage());
		}
		
	}

}
