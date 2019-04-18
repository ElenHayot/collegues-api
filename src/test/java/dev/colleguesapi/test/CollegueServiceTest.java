package dev.colleguesapi.test;

import java.time.LocalDate;
import java.util.List;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;

import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;

import dev.colleguesapi.entite.Collegue;
import dev.colleguesapi.exception.CollegueNotFoundException;
import dev.colleguesapi.exception.InsertException;
import dev.colleguesapi.service.CollegueService;

@SpringBootTest
public class CollegueServiceTest {
	
	@Rule public ExpectedException expectedEx = ExpectedException.none();
	

	@Test
	public void testAddCollegueWithBadName() throws Exception {
		
		expectedEx.expect(InsertException.class);
		expectedEx.expectMessage("Please enter a name that contains");
		
		
		CollegueService collegueService = new CollegueService();
		Collegue addCollegue = new Collegue("c", "Rojies", LocalDate.of(1966, 06, 30), "rojies.raph@society.com", "http://photopath/photo");
		collegueService.addCollegue(addCollegue);
	}	
	
	@Test
	public void testAddCollegueWithBadFirstname() throws Exception {
		
		expectedEx.expect(InsertException.class);
		expectedEx.expectMessage("Please enter a firstname that contains");
		
		CollegueService collegueService = new CollegueService();
		Collegue addCollegue = new Collegue("Raph", "rf", LocalDate.of(1966, 06, 30), "rojies.raph@society.com", "http://photopath/photo");
		collegueService.addCollegue(addCollegue);
	}
	
	@Test
	public void testAddCollegueWithBadEmail() throws Exception {
		
		expectedEx.expect(InsertException.class);
		expectedEx.expectMessage("Please enter a convenient email");
		
		CollegueService collegueService = new CollegueService();
		Collegue addCollegue = new Collegue("Raph", "klouklou", LocalDate.of(1966, 06, 30), "rojies.raphsociety.com", "http://photopath/photo");
		collegueService.addCollegue(addCollegue);
	}
	
	@Test
	public void testAddCollegueWithBadPhotoUrl() throws Exception {
		
		expectedEx.expect(InsertException.class);
		expectedEx.expectMessage("Please enter a URL path");
		
		CollegueService collegueService = new CollegueService();
		Collegue addCollegue = new Collegue("Raph", "klouklou", LocalDate.of(1966, 06, 30), "rojies.raph@society.com", "ttp://photopath/photo");
		collegueService.addCollegue(addCollegue);
	}
	
	@Test
	public void testAddCollegueWithBadAge() throws Exception {
		
		expectedEx.expect(InsertException.class);
		expectedEx.expectMessage("Sorry but you must be more than 18");
		
		CollegueService collegueService = new CollegueService();
		Collegue addCollegue = new Collegue("Raph", "klouklou", LocalDate.of(2002, 06, 30), "rojies.raph@society.com", "http://photopath/photo");
		collegueService.addCollegue(addCollegue);
	}
	
	@Test
	public void testAddCollegueWithoutBirthdate() throws Exception {
		
		expectedEx.expect(InsertException.class);
		expectedEx.expectMessage("Please enter a birthdate");
		
		LocalDate nullDate = null;
		CollegueService collegueService = new CollegueService();
		Collegue addCollegue = new Collegue("Raph", "klouklou", nullDate, "rojies.raph@society.com", "http://photopath/photo");
		collegueService.addCollegue(addCollegue);
	}
	
	@Test
	public void testAddCollegueAllIsGood() throws Exception {
		
		CollegueService collegueService = new CollegueService();
		Collegue addCollegue = new Collegue("RAHhhh", "Rojies", LocalDate.of(1966, 06, 30), "rojies.raph@society.com", "http://photopath/photo");
		collegueService.addCollegue(addCollegue);
		
		List<Collegue> listeColleguesFound = collegueService.findByName("RAHhhh");
		
		Assert.assertTrue(listeColleguesFound.size() > 0);
	}
	
	@Test
	public void testUpdateEmailWithBadMatricule() throws Exception {
		
		expectedEx.expect(CollegueNotFoundException.class);
		expectedEx.expectMessage("Sorry but there's no collegue");
		
		CollegueService collegueService = new CollegueService();
		Collegue updateCollegue = new Collegue("RAHhhh", "Rojies", LocalDate.of(1966, 06, 30), "rojies.raph@society.com", "http://photopath/photo");
		collegueService.addCollegue(updateCollegue);
		collegueService.updateEmail("123456789", "jiji@guy.com");

		
	}
	
	@Test
	public void testUpdateEmailWithBadEmail() throws Exception {
		
		expectedEx.expect(InsertException.class);
		expectedEx.expectMessage("Please enter a convenient email");
		
		CollegueService collegueService = new CollegueService();
		Collegue updateCollegue = new Collegue("RAHhhh", "Rojies", LocalDate.of(1966, 06, 30), "rojies.raph@society.com", "http://photopath/photo");
		collegueService.addCollegue(updateCollegue);
		collegueService.updateEmail(updateCollegue.getMatricule(), "jiji.com");

		
	}
	
	@Test
	public void testUpdatePhotoUrlWithBadMatricule() throws Exception {
		
		expectedEx.expect(CollegueNotFoundException.class);
		expectedEx.expectMessage("Sorry but there's no collegue");
		
		CollegueService collegueService = new CollegueService();
		Collegue updateCollegue = new Collegue("RAHhhh", "Rojies", LocalDate.of(1966, 06, 30), "rojies.raph@society.com", "http://photopath/photo");
		collegueService.addCollegue(updateCollegue);
		collegueService.updatePhotoUrl("123456789", "http://photo/myPhoto.jpg");

		
	}
	
	@Test
	public void testUpdatePhotoUrlWithBadUrl() throws Exception {
		
		expectedEx.expect(InsertException.class);
		expectedEx.expectMessage("Please enter a URL path");
		
		CollegueService collegueService = new CollegueService();
		Collegue updateCollegue = new Collegue("RAHhhh", "Rojies", LocalDate.of(1966, 06, 30), "rojies.raph@society.com", "http://photopath/photo");
		collegueService.addCollegue(updateCollegue);
		collegueService.updatePhotoUrl(updateCollegue.getMatricule(), "tp:/foto.jpg");

		
	}
	
}
