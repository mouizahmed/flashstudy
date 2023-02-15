package Models;

import java.util.ArrayList;

public interface DeckDatabase {
	void addDeck(String title, ArrayList<Flashcard> flashcards, String createdBy, boolean publickDeck);
	ArrayList<Deck> getDecksByTitle(String deckTitle);
	void updateDeck(String deckTitle);
	void deleteDeck(String deckTitle);
	ArrayList<Deck> getAllPublicDecks();
	ArrayList<Deck> getAllUserDecks(User currentUser);
	Deck createDeck(String title, String createdBy);
}
