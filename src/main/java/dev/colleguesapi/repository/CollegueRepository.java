package dev.colleguesapi.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.colleguesapi.entite.Collegue;

public interface CollegueRepository extends JpaRepository<Collegue, String> {

	//send a query like "select c from Collegue c where name=?"
	List<Collegue> findByName(String name);
	
	Collegue findByMatricule(String matricule);

	Optional<Collegue> findByEmail(String usermail);
	
	
}
