package Controller;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import Models.Deck;


class MiddleTierTest {

	
	
	@Test
	void userDecks() {
		MiddleTier app = new MiddleTier();
		ArrayList<Deck> userDecks = MiddleTier.getUserDecks("stax");
		int expected = 2;
		int actual = userDecks.size();
		assertEquals(expected, actual);
		
	}
	
	/**
	 * Tests registration of new user
	 */
	@Test
	void testCreateUser() {
		
	}
	

}
