package Models;

import java.util.ArrayList;
import java.util.UUID;

public class Deck {
	
	public String deckTitle;
	public String deckID;
	public ArrayList<Flashcard> flashcards = new ArrayList<>();
	public String createdBy;
	public boolean publicDeck;
	
//	public Deck(String deckID, String title, ArrayList<Flashcard> flashcards, String createdBy, boolean publickDeck) {
//		this.title = title;
//		this.deckID = deckID;
//		this.flashcards = flashcards;
//		this.createdBy = createdBy;
//	}
	
	public Deck(String createdBy, String deckTitle) {
		this.createdBy = createdBy;
		this.deckTitle = deckTitle;
		this.deckID = UUID.randomUUID().toString();
	}
	
	
	public String getDeckID() {
		return deckID;
	}
	
	public void addFlashcard(Flashcard flashcard) {
		if (!flashcard.getDeckID().equals(this.getDeckID())) {
			throw new IllegalArgumentException("Flashcard ID does not match Deck ID");
		} else {
			flashcards.add(flashcard);
		}
	}
	
	public void setDeckTitle(String deckTitle) {
		this.deckTitle = deckTitle;
	}
	
	public String getDeckTitle() {
		return this.deckTitle;
	}
	
	public ArrayList<Flashcard> getAllFlashcards() {
		return flashcards;
	}
	
}
