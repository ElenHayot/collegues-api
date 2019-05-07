package dev.colleguesapi.test;

import java.time.LocalDate;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.Mockito;

import dev.colleguesapi.entite.Collegue;
import dev.colleguesapi.exception.CollegueNotFoundException;
import dev.colleguesapi.exception.InsertException;
import dev.colleguesapi.repository.CollegueRepository;
import dev.colleguesapi.service.CollegueService;

public class CollegueServiceTest {

	@Rule
	public ExpectedException expectedEx = ExpectedException.none();
	private CollegueRepository mock;
	private CollegueService collegueService;

	@Before
	public void init() {
		collegueService = new CollegueService();
		mock = Mockito.mock(CollegueRepository.class);
		collegueService.setRepo(mock);
	}

	@Test
	public void testAddCollegueWithBadName() throws Exception {

		expectedEx.expect(InsertException.class);
		expectedEx.expectMessage("Please enter a name that contains");

		Collegue addCollegue = new Collegue("c", "Rojies", LocalDate.of(1966, 06, 30), "rojies.raph@society.com",
				"http://photopath/photo");
		collegueService.addCollegue(addCollegue);
	}

	@Test
	public void testAddCollegueWithBadFirstname() throws Exception {

		expectedEx.expect(InsertException.class);
		expectedEx.expectMessage("Please enter a firstname that contains");

		Collegue addCollegue = new Collegue("Raph", "rf", LocalDate.of(1966, 06, 30), "rojies.raph@society.com",
				"http://photopath/photo");
		collegueService.addCollegue(addCollegue);
	}

	@Test
	public void testAddCollegueWithBadEmail() throws Exception {

		expectedEx.expect(InsertException.class);
		expectedEx.expectMessage("Please enter a convenient email");

		Collegue addCollegue = new Collegue("Raph", "klouklou", LocalDate.of(1966, 06, 30), "rojies.raphsociety.com",
				"http://photopath/photo");
		collegueService.addCollegue(addCollegue);
	}

	@Test
	public void testAddCollegueWithBadPhotoUrl() throws Exception {

		expectedEx.expect(InsertException.class);
		expectedEx.expectMessage("Please enter a URL path");

		Collegue addCollegue = new Collegue("Raph", "klouklou", LocalDate.of(1966, 06, 30), "rojies.raph@society.com",
				"ttp://photopath/photo");
		collegueService.addCollegue(addCollegue);
	}

	@Test
	public void testAddCollegueWithBadAge() throws Exception {

		expectedEx.expect(InsertException.class);
		expectedEx.expectMessage("Sorry but you must be more than 18");

		Collegue addCollegue = new Collegue("Raph", "klouklou", LocalDate.of(2002, 06, 30), "rojies.raph@society.com",
				"http://photopath/photo");
		collegueService.addCollegue(addCollegue);
	}

	@Test
	public void testAddCollegueWithoutBirthdate() throws Exception {

		expectedEx.expect(InsertException.class);
		expectedEx.expectMessage("Please enter a birthdate");

		LocalDate nullDate = null;

		Collegue addCollegue = new Collegue("Raph", "klouklou", nullDate, "rojies.raph@society.com",
				"http://photopath/photo");
		collegueService.addCollegue(addCollegue);
	}

	@Test
	public void testAddCollegueAllIsGood() throws Exception {

		CollegueService collegueService = new CollegueService();
		collegueService.setRepo(mock);

		Collegue addCollegue = new Collegue("RAHhhh", "Rojies", LocalDate.of(1966, 06, 30), "rojies.raph@society.com",
				"http://photopath/photo");
		collegueService.addCollegue(addCollegue);
		
		Mockito.verify(mock).save(addCollegue);

	}

	@Test
	public void testUpdateEmailWithBadMatricule() throws Exception {

		expectedEx.expect(CollegueNotFoundException.class);
		expectedEx.expectMessage("Sorry but there's no collegue");

		
		Collegue updateCollegue = new Collegue("RAHhhh", "Rojies", LocalDate.of(1966, 06, 30),
				"rojies.raph@society.com", "http://photopath/photo");
		collegueService.addCollegue(updateCollegue);
		collegueService.updateEmail("123456789", "jiji@guy.com");

	}

	@Test
	public void testUpdateEmailWithBadEmail() throws Exception {

		expectedEx.expect(InsertException.class);
		expectedEx.expectMessage("Please enter a convenient email");

		Mockito.when(mock.findByMatricule("A")).thenReturn( new Collegue("RAHhhh", "Rojies", LocalDate.of(1966, 06, 30),
				"rojies.raph@society.com", "http://photopath/photo"));
		
		collegueService.updateEmail("A", "jiji.com");

	}

	@Test
	public void testUpdatePhotoUrlWithBadMatricule() throws Exception {

		expectedEx.expect(CollegueNotFoundException.class);
		expectedEx.expectMessage("Sorry but there's no collegue");

		Collegue updateCollegue = new Collegue("RAHhhh", "Rojies", LocalDate.of(1966, 06, 30),
				"rojies.raph@society.com", "http://photopath/photo");
		collegueService.addCollegue(updateCollegue);
		collegueService.updatePhotoUrl("123456789", "http://photo/myPhoto.jpg");

	}

	@Test
	public void testUpdatePhotoUrlWithBadUrl() throws Exception {

		expectedEx.expect(InsertException.class);
		expectedEx.expectMessage("Please enter a URL path");

		Mockito.when(mock.findByMatricule("A")).thenReturn( new Collegue("RAHhhh", "Rojies", LocalDate.of(1966, 06, 30),
				"rojies.raph@society.com", "http://photopath/photo"));
		
		collegueService.updatePhotoUrl("A", "tp:/foto.jpg");

	}

}
