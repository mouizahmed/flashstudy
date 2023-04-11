package Models;

import static org.junit.jupiter.api.Assertions.*;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DeckListTest {

	private DeckList deckList;
	private User testUser;
	private UserList userDatabase;
	
	@BeforeEach
	public void setUp() throws NoSuchAlgorithmException, InvalidKeySpecException {
		userDatabase = new UserList();
		userDatabase.addUser("testuser", "testuser@gmail.com", "password", "password");
		userDatabase.login("testuser", "password");
		testUser = userDatabase.getCurrentUser();
		deckList = new DeckList(userDatabase);
	}

	@Test
	public void testAddDeck() {
		String deckID = UUID.randomUUID().toString();
		Flashcard f1 = new Flashcard("What is the capital of France?", "Paris", "username", deckID, UUID.randomUUID().toString());
		Flashcard f2 = new Flashcard("What is the capital of Spain?", "Madrid", "username", deckID, UUID.randomUUID().toString());
		ArrayList<Flashcard> flashcards = new ArrayList<>();
		flashcards.add(f1);
		flashcards.add(f2);
		Deck deck = new Deck("Deck Title", flashcards, "testuser", true, deckID, "Test School", "Faculty Name", "Description", "Course Name");
		deckList.addDeck(deck);
		ArrayList<Deck> decksByTitle = deckList.getDecksByTitle("Deck Title");
		assertEquals(1, decksByTitle.size());
		assertTrue(decksByTitle.contains(deck));
	}

	@Test
	public void testAddDeckUserNotLoggedIn() {
		userDatabase.logout();
		String deckID = UUID.randomUUID().toString();
		Flashcard f1 = new Flashcard("What is the capital of France?", "Paris", "username", deckID, UUID.randomUUID().toString());
		Flashcard f2 = new Flashcard("What is the capital of Spain?", "Madrid", "username", deckID, UUID.randomUUID().toString());
		ArrayList<Flashcard> flashcards = new ArrayList<>();
		flashcards.add(f1);
		flashcards.add(f2);
		Deck deck = new Deck("Deck Title", flashcards, "testuser", true, deckID, "Test School", "Faculty Name", "Description", "Course Name");
		assertThrows(IllegalArgumentException.class, ()-> {
			deckList.addDeck(deck);
		});
	}

	@Test
	public void testGetDecksByTitle() {
		String deckID = UUID.randomUUID().toString();
		Flashcard f1 = new Flashcard("What is the capital of France?", "Paris", "username", deckID, UUID.randomUUID().toString());
		Flashcard f2 = new Flashcard("What is the capital of Spain?", "Madrid", "username", deckID, UUID.randomUUID().toString());
		ArrayList<Flashcard> flashcards = new ArrayList<>();
		flashcards.add(f1);
		flashcards.add(f2);
		Deck deck = new Deck("Deck Title", flashcards, "testuser", true, deckID, "Test School", "Faculty Name", "Description", "Course Name");
		deckList.addDeck(deck);
		ArrayList<Deck> decksByTitle = deckList.getDecksByTitle("Deck Title");
		assertEquals(1, decksByTitle.size());
		assertTrue(decksByTitle.contains(deck));
	}

	@Test
	public void testUpdateDeck() {
		String deckID = UUID.randomUUID().toString();
		Flashcard f1 = new Flashcard("What is the capital of France?", "Paris", "username", deckID, UUID.randomUUID().toString());
		Flashcard f2 = new Flashcard("What is the capital of Spain?", "Madrid", "username", deckID, UUID.randomUUID().toString());
		ArrayList<Flashcard> flashcards = new ArrayList<>();
		flashcards.add(f1);
		flashcards.add(f2);
		Deck deck = new Deck("Deck Title", flashcards, "testuser", true, deckID, "Test School", "Faculty Name", "Description", "Course Name");
		deckList.addDeck(deck);
		flashcards.add(new Flashcard("Question", "Answer"));
		deckList.updateDeck(deck, flashcards);
		ArrayList<Deck> decksByTitle = deckList.getDecksByTitle("Deck Title");
		assertEquals(3, decksByTitle.get(0).getAllFlashcards().size());
		assertEquals(flashcards, decksByTitle.get(0).getAllFlashcards());
	}

	@Test
	public void testUpdateDeckNonExistent() {
		String deckID = UUID.randomUUID().toString();
		Flashcard f1 = new Flashcard("What is the capital of France?", "Paris", "username", deckID, UUID.randomUUID().toString());
		Flashcard f2 = new Flashcard("What is the capital of Spain?", "Madrid", "username", deckID, UUID.randomUUID().toString());
		ArrayList<Flashcard> flashcards = new ArrayList<>();
		flashcards.add(f1);
		flashcards.add(f2);
		Deck deck = new Deck("Deck Title", flashcards, "testuser", true, deckID, "Test School", "Faculty Name", "Description", "Course Name");
		flashcards.add(new Flashcard("Question", "Answer"));
		
		assertThrows(IllegalArgumentException.class, ()-> {
			deckList.updateDeck(deck, flashcards);
		});
	}

	@Test
	public void testDeleteDeck() {
		String deckID = UUID.randomUUID().toString();
		Flashcard f1 = new Flashcard("What is the capital of France?", "Paris", "username", deckID, UUID.randomUUID().toString());
		Flashcard f2 = new Flashcard("What is the capital of Spain?", "Madrid", "username", deckID, UUID.randomUUID().toString());
		ArrayList<Flashcard> flashcards = new ArrayList<>();
		flashcards.add(f1);
		flashcards.add(f2);
		Deck deck = new Deck("Deck Title", flashcards, "testuser", true, deckID, "Test School", "Faculty Name", "Description", "Course Name");
		deckList.addDeck(deck);
		deckList.deleteDeck(deck);
		ArrayList<Deck> decksByTitle = deckList.getDecksByTitle("Deck Title");
		assertEquals(0, decksByTitle.size());
	}

	@Test
	public void testDeleteDeckNonExistent() {
		String deckID = UUID.randomUUID().toString();
		Flashcard f1 = new Flashcard("What is the capital of France?", "Paris", "username", deckID, UUID.randomUUID().toString());
		Flashcard f2 = new Flashcard("What is the capital of Spain?", "Madrid", "username", deckID, UUID.randomUUID().toString());
		ArrayList<Flashcard> flashcards = new ArrayList<>();
		flashcards.add(f1);
		flashcards.add(f2);
		Deck deck = new Deck("Deck Title", flashcards, "testuser", true, deckID, "Test School", "Faculty Name", "Description", "Course Name");
		assertThrows(IllegalArgumentException.class, ()-> {
			deckList.deleteDeck(deck);
		});
	}
	
	@Test
    public void testSearchPublicDeck() {
       
      

		String deckID = UUID.randomUUID().toString();
		Flashcard f1 = new Flashcard("What is the capital of France?", "Paris", "username", deckID, UUID.randomUUID().toString());
		Flashcard f2 = new Flashcard("What is the capital of Spain?", "Madrid", "username", deckID, UUID.randomUUID().toString());
		ArrayList<Flashcard> flashcards = new ArrayList<>();
		flashcards.add(f1);
		flashcards.add(f2);
		Deck deck = new Deck("Deck Title", flashcards, "testuser", true, deckID, "Test School", "Faculty Name", "Description", "Course Name");
		
		String deckID1 = UUID.randomUUID().toString();
		Flashcard f11 = new Flashcard("What is the capital of France?", "Paris", "username", deckID1, UUID.randomUUID().toString());
		Flashcard f21 = new Flashcard("What is the capital of Spain?", "Madrid", "username", deckID1, UUID.randomUUID().toString());
		ArrayList<Flashcard> flashcards1 = new ArrayList<>();
		flashcards.add(f11);
		flashcards.add(f21);
		Deck deck1 = new Deck("Private Deck", flashcards1, "testuser", false, deckID1, "Test School", "Faculty Name", "Description", "Course Name");
        
		deckList.addDeck(deck);
        deckList.addDeck(deck1);

        // Search for a public deck
        ArrayList<Deck> searchResults = deckList.searchPublicDeck("Deck Title");
        ArrayList<Deck> searchResults1 = deckList.searchPublicDeck("Private Deck");
        // Verify the search results
        assertEquals(1, searchResults.size());
        assertEquals(0, searchResults1.size());
        assertEquals(1, deckList.getAllPublicDecks().size());
     
    }
	
	@Test
	public void testGetAllUserDecks() {
		String deckID = UUID.randomUUID().toString();
		Flashcard f1 = new Flashcard("What is the capital of France?", "Paris", "username", deckID, UUID.randomUUID().toString());
		Flashcard f2 = new Flashcard("What is the capital of Spain?", "Madrid", "username", deckID, UUID.randomUUID().toString());
		ArrayList<Flashcard> flashcards = new ArrayList<>();
		flashcards.add(f1);
		flashcards.add(f2);
		Deck deck = new Deck("Deck Title", flashcards, "testuser", true, deckID, "Test School", "Faculty Name", "Description", "Course Name");
		
		deckList.addDeck(deck);
		
		assertEquals(1, deckList.getAllCurrentUserDecks().size());
		assertEquals(1, deckList.getAllUserDecks(testUser).size());
	}
	
	
	
	

}
