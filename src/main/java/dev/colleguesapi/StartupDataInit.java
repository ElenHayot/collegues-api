package dev.colleguesapi;

import java.time.LocalDate;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import dev.colleguesapi.entite.Collegue;
import dev.colleguesapi.repository.CollegueRepository;

@Component
public class StartupDataInit {

	@Autowired CollegueRepository collegueRepo;
	
	@EventListener(ContextRefreshedEvent.class)
	public void init(){
		
		Collegue col1 = new Collegue("Raph", "Rojies", LocalDate.of(2012, 06, 30), "rojies.raph@society.com", "http://photopath/photo");
		Collegue col2 = new Collegue("juju", "francois", LocalDate.of(1994, 07, 07), "franc.juju@society.com", "photopath/photo");
		Collegue col3 = new Collegue("Son", "David", LocalDate.of(1987, 04, 21), "davidson@society.com", "http://photopath/photo");
				
		col1.setMatricule(UUID.randomUUID().toString());
		col2.setMatricule(UUID.randomUUID().toString());
		col3.setMatricule(UUID.randomUUID().toString());
		
		collegueRepo.save(col1);
		collegueRepo.save(col2);
		collegueRepo.save(col3);
		
	}
	
}
