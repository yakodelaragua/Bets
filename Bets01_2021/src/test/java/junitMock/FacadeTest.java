package junitMock;
/**
 * FacadeTest: Some JUnit example for Facade
 */

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import businessLogic.BLFacadeImplementation;
import configuration.ConfigXML;
import dataAccess.DataAccess;
import domain.Event;
import domain.Question;
import exceptions.EventFinished;
import exceptions.QuestionAlreadyExist;
import test.businessLogic.TestFacadeImplementation;

class FacadeTest {
	static BLFacadeImplementation sut;
	static TestFacadeImplementation testBL;

	private String queryText = "A question";
	private Float betMinimum = 2.0f;
	private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	private Event ev;

	@BeforeAll
	public static void ini() {
		// you can parametrize the DataAccess used by BLFacadeImplementation
		DataAccess da = new DataAccess(ConfigXML.getInstance().getDataBaseOpenMode().equals("initialize"));
		sut = new BLFacadeImplementation(da);
		testBL = new TestFacadeImplementation();
	}

	@Test
	@DisplayName("The event has one question with a queryText")
	void createQuestionBLTest1() {

		try {
			Date oneDate = sdf.parse("05/10/2022");

			// configure the state of the system (create object in the database)
			ev = testBL.addEvent(queryText, oneDate);
			sut.createQuestion(ev, queryText, betMinimum);

			// invoke System Under Test (sut)
			assertThrows(QuestionAlreadyExist.class, () -> sut.createQuestion(ev, queryText, betMinimum));

		} catch (ParseException | EventFinished | QuestionAlreadyExist e) {
			// if the program goes to this point fail
			fail("No problems should arise: ParseException/EventFinished/QuestionaAlreadyExist");

		} finally {
			// Remove the created objects in the database (cascade removing)
			boolean b = testBL.removeEvent(ev);
			assertTrue(b);
		}
	}

	@Test
	@DisplayName("The event has NOT one question with a queryText")
	void createQuestionBLTest2() {

		try {
			Date oneDate = sdf.parse("05/10/2022");

			// configure the state of the system (create object in the dabatase)
			ev = testBL.addEvent(queryText, oneDate);

			// invoke System Under Test (sut)
			Question q = sut.createQuestion(ev, queryText, betMinimum);

			// verify the results
			assertNotNull(q);
			assertEquals(queryText, q.getQuestion());
			assertEquals(betMinimum, q.getBetMinimum(), 0.05);

		} catch (ParseException | QuestionAlreadyExist | EventFinished e) {
			// if the program goes to this point fail
			fail("No problems should arise: ParseException/EventFinished/QuestionaAlreadyExist");

		} finally {
			// Remove the created objects in the database (cascade removing)
			boolean b = testBL.removeEvent(ev);
			System.out.println("Finally " + b);
		}
	}

}
