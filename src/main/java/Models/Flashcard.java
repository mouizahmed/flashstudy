package Models;

import java.util.UUID;
import javax.swing.ImageIcon;
public class Flashcard {

	public String question;
	public String answer;
	public String flashcardID;
	public String deckID;
	public String createdBy;
	public String difficultyColour = "";
	public byte[] cardImgData;
	
	public Flashcard(String question, String answer, String createdBy, String deckID) {
		this.question = question;
		this.answer = answer;
		this.createdBy = createdBy;
		this.deckID = deckID;
		this.flashcardID = UUID.randomUUID().toString();
	}
	
	public Flashcard(String question, String answer, String createdBy, String deckID, byte[] flashCardImgData) {
		this.question = question;
		this.answer = answer;
		this.createdBy = createdBy;
		this.deckID = deckID;
		this.flashcardID = UUID.randomUUID().toString();
		this.cardImgData = flashCardImgData;
	}
	
	public Flashcard(String question, String answer, String createdBy, String deckID, String flashcardID) {
		this.question = question;
		this.answer = answer;
		this.createdBy = createdBy;
		this.deckID = deckID;
		this.flashcardID = flashcardID;
	}
	
	public Flashcard(String question, String answer, String createdBy, String deckID, String flashcardID, String difficultyColour, byte[] flashCardImgData) {
		this.question = question;
		this.answer = answer;
		this.createdBy = createdBy;
		this.deckID = deckID;
		this.flashcardID = flashcardID;
		this.difficultyColour = difficultyColour;
		this.cardImgData = flashCardImgData;
	}
	

	public Flashcard(String question, String answer) {
		this.question = question;
		this.answer = answer;
	}

	//Setters and Getters
	
	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}
	
	public String getDeckID() {
		return deckID;

	}
	
	public String getFlashcardID() {
		return this.flashcardID;
	}
	
	public void setNewFlashcardID() {
		this.flashcardID = UUID.randomUUID().toString();
	}
	
	public void setNewUser(String newUser) {
		this.createdBy = newUser;
	}
	
	public void setDeckID(String deckID) {
		this.deckID = deckID;
	}
	
	public void setDifficultyColour(String colour) {
		this.difficultyColour = colour;
	}
	
	public String getDifficultyColour() {
		return this.difficultyColour;
	}

	public byte[] getFlashCardImgData() {
		// TODO Auto-generated method stub
		return this.cardImgData;
	}
}
