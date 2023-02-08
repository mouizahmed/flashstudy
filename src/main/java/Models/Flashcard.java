package Models;

public class Flashcard {

	public String question;
	public String answer;
	public int flashcardID;
	public int deckID;
	public String createdBy;
	
	public Flashcard(String question, String answer, int flashcardID, int deckID, String createdBy) {
		this.question = question;
		this.answer = answer;
		this.flashcardID = flashcardID;
		this.deckID = deckID;
		this.createdBy = createdBy;
	}
}
