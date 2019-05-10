package dev.colleguesapi;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import dev.colleguesapi.entite.Collegue;
import dev.colleguesapi.repository.CollegueRepository;

@Component
public class StartupDataInit {

	@Autowired CollegueRepository collegueRepo;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@EventListener(ContextRefreshedEvent.class)
	public void init(){
		
		String imgsloubi = "http://image.noelshack.com/fichiers/2016/48/1480815516-chante-sloubi.png";
		String imgcol2 = "https://media.makeameme.org/created/say-my-name-bo20nr.jpg";
		String imgcol3 = "https://media.makeameme.org/created/say-my-name-bo20nr.jpg";
		String imgsnaky = "https://bridoz.com/wp-content/uploads/2015/11/138.jpg";
		String imgwizard = "http://www.mypokecard.com/my/galery/fnumJ08vPgDQ.jpg";
		
		Collegue sloubi = new Collegue("Le Gallois", "Perceval", LocalDate.of(1839, 04, 05), "perceval.legaulois@sloubi.com", imgsloubi, passwordEncoder.encode("sloubi"), Arrays.asList("ROLE_USER","ROLE_ADMIN"));
		Collegue col2 = new Collegue("juju", "francois", LocalDate.of(1994, 07, 07), "franc.juju@society.com", imgcol2, passwordEncoder.encode("col2"), Arrays.asList("ROLE_USER"));
		Collegue col3 = new Collegue("juju", "David", LocalDate.of(1987, 04, 21), "davidson@society.com", imgcol3, passwordEncoder.encode("col3"), Arrays.asList("ROLE_USER"));
		Collegue snaky = new Collegue("SIR", "Sissinissi", LocalDate.of(1970, 03, 03), "sissinissi@snaky.fr", imgsnaky, passwordEncoder.encode("snaky"), Arrays.asList("ROLE_USER"));
		Collegue wizard = new Collegue("Coco", "L'Asticot", LocalDate.of(1219, 1, 1), "cocolasticot@merlin.com", imgwizard, passwordEncoder.encode("wizard"), Arrays.asList("ROLE_USER"));
		
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
