package Models;

import java.util.ArrayList;
import java.util.UUID;

public class Deck {
	
	public String deckTitle;
	public String deckID;
	public ArrayList<Flashcard> flashcards = new ArrayList<>();
	public String createdBy;
	public boolean publicDeck;
	
	public Deck(String deckTitle, ArrayList<Flashcard> flashcards, String createdBy, boolean publicDeck, String deckID) {
		this.deckTitle = deckTitle;
		this.flashcards = flashcards;
		this.createdBy = createdBy;
		this.publicDeck = publicDeck;
		this.deckID = deckID;
	}
		
	public String getDeckID() {
		return deckID;
	}
	
	public void addFlashcard(Flashcard flashcard) {
//		if (!flashcard.getDeckID().equals(this.getDeckID())) {
//			throw new IllegalArgumentException("Flashcard ID does not match Deck ID");
//		} else {
//			flashcards.add(flashcard);
//		}
		this.flashcards.add(flashcard);
	}
	
	public void setFlashcards(ArrayList<Flashcard> flashcards) {
		this.flashcards = flashcards;
	}
	
	public void setDeckTitle(String deckTitle) {
		this.deckTitle = deckTitle;
	}
	
	public String getDeckTitle() {
		return this.deckTitle;
	}
	
	public void setPublicDeck(boolean publicDeck) {
		this.publicDeck = publicDeck;
	}
	
	public boolean getPublicity() {
		return this.publicDeck;
	}
	
	public ArrayList<Flashcard> getAllFlashcards() {
		return flashcards;
	}
	
}
