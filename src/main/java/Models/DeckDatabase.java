package Models;

import java.util.ArrayList;

public interface DeckDatabase {
	void addDeck(String title, ArrayList<Flashcard> flashcards, String createdBy, boolean publickDeck);
	ArrayList<Deck> getDecksByTitle(String deckTitle);
	void updateDeck(Deck deck, ArrayList<Flashcard> flashcards);
	void deleteDeck(Deck deck);
	ArrayList<Deck> getAllPublicDecks();
	ArrayList<Deck> getAllUserDecks(User currentUser);
	ArrayList<Deck> getAllCurrentUserDecks();
}
