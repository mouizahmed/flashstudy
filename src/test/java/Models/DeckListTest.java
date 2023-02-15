package Models;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DeckListTest {
	
	private DeckList deckDatabase;
	private UserList userDatabase;
	
	@BeforeEach
	void setUp() throws Exception {
		deckDatabase = new DeckList(userDatabase);
	}
	@Test
	void test() {
		fail("Not yet implemented");
	}

}
