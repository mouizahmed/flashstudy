package Models;

import java.util.ArrayList;

public interface DeckDatabase {
	void addDeck(Deck deck);
	Deck getDeck(String deckTitle);
	void updateDeck(String deckTitle);
	void deleteDeck(String deckTitle);
	ArrayList<Deck> getAllPublicDecks();
	ArrayList<Deck> getAllUserDecks();
}
