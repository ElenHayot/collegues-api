package dev.colleguesapi.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.colleguesapi.entite.Collegue;
import dev.colleguesapi.entite.Comment;
import dev.colleguesapi.exception.CollegueNotFoundException;
import dev.colleguesapi.exception.CommentNotFoundException;
import dev.colleguesapi.exception.InsertException;
import dev.colleguesapi.repository.CommentRepository;

@Service
public class CommentService {
	
	@Autowired 
	CollegueService service;
	@Autowired
	CommentRepository commentRepo; 
	
	public CommentService() {}

	public void addNewComment(String matricule, Comment comment ) throws Exception {
		
		if(comment == null || comment.getText().isEmpty()) {
			throw new InsertException("Oo, didn't find any comment to register ! Is your comment empty?");
		}else {
			Collegue collegue = service.findCollegueByMatricule(matricule);
			comment.setCollegue(collegue);
			comment.setDateEmit(LocalDateTime.now());
			commentRepo.save(comment);
		}
		
	}
	
	public List<Comment> sendComment(String colMatricule) throws Exception{
		
		List<Comment> collegueComments = new ArrayList<>();
		if(colMatricule == null) {
			throw new CollegueNotFoundException("Bad colleague's matricule = Bad request, you little trickster !");
		}else{
			collegueComments = commentRepo.findByCollegueMatricule(colMatricule);
			return collegueComments;
			}
		
	}
	
	public void deleteComment(int commentMatricule) throws Exception {
		
		if(commentRepo.findByMatriculeComment(commentMatricule) == null) {
			throw new CommentNotFoundException("Oo, no comment found, sorry ! Try again !");
		}else{
			Comment comToDelete = commentRepo.findByMatriculeComment(commentMatricule);
			commentRepo.delete(comToDelete);
		}
		
	}
	
}
