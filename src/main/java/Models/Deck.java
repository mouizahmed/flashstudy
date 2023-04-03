package Models;

import java.util.ArrayList;
import java.util.UUID;

public class Deck {
	
	private static ArrayList<Deck> decks = new ArrayList<>();
	public String deckTitle;
	public String deckID;
	public String schoolName;
	public String facultyName;
	public String description;
	public String courseName;
	public ArrayList<Flashcard> flashcards = new ArrayList<>();
	public String createdBy;
	public boolean publicDeck;
	public int counter;
	
	public Deck(String deckTitle, ArrayList<Flashcard> flashcards, String createdBy, boolean publicDeck, String deckID) {
		this.deckTitle = deckTitle;
		this.flashcards = flashcards;
		this.createdBy = createdBy;
		this.publicDeck = publicDeck;
		this.deckID = deckID;
	}
	
	public Deck(String deckTitle, ArrayList<Flashcard> flashcards, String createdBy, boolean publicDeck, String deckID, String schoolName, String facultyName, String description, String courseName) {
		this.deckTitle = deckTitle;
		this.flashcards = flashcards;
		this.createdBy = createdBy;
		this.publicDeck = publicDeck;
		this.deckID = deckID;
		this.schoolName = schoolName;
		this.facultyName = facultyName;
		this.description = description;
		this.courseName = courseName;
	}
		
	public String getDeckID() {
		return deckID;
	}
	
	public void setDeckID() {
		this.deckID = UUID.randomUUID().toString();
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
	
	public String getCreatedBy() {
		return this.createdBy;
	}
	
	public void setCreatedBy(String newUser) {
		this.createdBy = newUser;
	}
	
	public void replaceFlashcards(ArrayList<Flashcard> flashcards) {
		this.flashcards = flashcards;
	}

	public int getCounter() {
		return this.counter;
	}

	public void setCounter(int counter) {
		this.counter = counter;
		
	}

	public static Deck findDeckByID(String deckID, ArrayList<Deck> decks) {
	    for (Deck deck : decks) {
	        if (deck.getDeckID().equals(deckID)) {
	            return deck;
	        }
	    }
	    return null;
	}
	
	public String getSchoolName() {
		return this.schoolName;
	}
	
	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}
	
	public String getFaultyName() {
		return this.facultyName;
	}
	
	public void setFacultyName(String facultyName) {
		this.facultyName = facultyName;
	}
	
	public String getDescription() {
		return this.description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getCourseName() {
		return this.courseName;
	}
	
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
}
