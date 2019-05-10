package dev.colleguesapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.colleguesapi.entite.Comment;

public interface CommentRepository extends JpaRepository<Comment, String>{

	List<Comment> findByCollegueMatricule(String matricule);

	Comment findByMatriculeComment(int matricule);

}
