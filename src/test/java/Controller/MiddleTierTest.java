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
//		System.out.println(userDecks.get(0).flashcards.size());
//		
//		
//		for (int i = 0; i < userDecks.size(); i++) {
//				System.out.println(userDecks.get(i).title);
//			for (int j = 0; j < userDecks.get(i).flashcards.size(); j++) {
//				System.out.println(userDecks.get(i).flashcards.get(j).question);
//			}
//		}
		assertEquals(expected, actual);
		
	}

}
