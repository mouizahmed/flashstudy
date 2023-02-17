package Models;

import java.awt.*;

public class Flashcard {

	public String question;
	public String answer;
	public int flashcardID;
	public String deckID;
	public String createdBy;

	public String difficultyLevel;

	public Color color;
	
	public Flashcard(String question, String answer, int flashcardID, String deckID, String createdBy) {
		this.question = question;
		this.answer = answer;
		this.flashcardID = flashcardID;
		this.deckID = deckID;
		this.createdBy = createdBy;
	}

	public Flashcard(String question, String answer, int flashcardID, String deckID, String createdBy, String difficultyLevel) {
		this.question = question;
		this.answer = answer;
		this.flashcardID = flashcardID;
		this.deckID = deckID;
		this.createdBy = createdBy;
		this.difficultyLevel = difficultyLevel;
		this.color = ColorCoding.getColorForDifficultyLevel(difficultyLevel);
	}
	public Flashcard(String question, String answer){
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
}
