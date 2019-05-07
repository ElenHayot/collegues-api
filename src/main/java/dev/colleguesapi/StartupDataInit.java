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
		
		Collegue sloubi = new Collegue("Le Gallois", "Perceval", LocalDate.of(1839, 04, 05), "perceval.legaulois@sloubi.com", "http://image.noelshack.com/fichiers/2016/48/1480815516-chante-sloubi.png");
		Collegue col2 = new Collegue("juju", "francois", LocalDate.of(1994, 07, 07), "franc.juju@society.com", "https://media.makeameme.org/created/say-my-name-bo20nr.jpg");
		Collegue col3 = new Collegue("juju", "David", LocalDate.of(1987, 04, 21), "davidson@society.com", "https://media.makeameme.org/created/say-my-name-bo20nr.jpg");
		Collegue snaky = new Collegue("SIR", "Sissinissi", LocalDate.of(1970, 03, 03), "sissinissi@snaky.fr", "https://bridoz.com/wp-content/uploads/2015/11/138.jpg");
		Collegue wizard = new Collegue("Coco", "L'Asticot", LocalDate.of(1219, 1, 1), "cocolasticot@merlin.com", "http://www.mypokecard.com/my/galery/fnumJ08vPgDQ.jpg");
		
		sloubi.setMatricule(UUID.randomUUID().toString());
		col2.setMatricule(UUID.randomUUID().toString());
		col3.setMatricule(UUID.randomUUID().toString());
		snaky.setMatricule(UUID.randomUUID().toString());
		wizard.setMatricule(UUID.randomUUID().toString());
		
		collegueRepo.save(sloubi);
		collegueRepo.save(col2);
		collegueRepo.save(col3);
		collegueRepo.save(snaky);
		collegueRepo.save(wizard);

	}
	
}
