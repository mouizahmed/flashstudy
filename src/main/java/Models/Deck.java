package Models;

import java.util.ArrayList;

public class Deck {
	
	public String title;
	public int deckID;
	public ArrayList<Flashcard> flashcards;
	public String createdBy;
	
	public Deck(int deckID, String title, ArrayList<Flashcard> flashcards, String createdBy) {
		this.title = title;
		this.deckID = deckID;
		this.flashcards = flashcards;
		this.createdBy = createdBy;
	}
	
	public void addFlashcard(Flashcard flashcard) {
		flashcards.add(flashcard);
		
	}
	
	public ArrayList<Flashcard> getAllFlashcards() {
		
		return flashcards;
	}
	
}
