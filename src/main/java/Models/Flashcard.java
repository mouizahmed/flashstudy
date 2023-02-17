package Models;

import java.util.UUID;

public class Flashcard {

	public String question;
	public String answer;
	public String flashcardID;
	public String deckID;
	public String createdBy;
	
	public Flashcard(String question, String answer, String createdBy) {
		this.question = question;
		this.answer = answer;
		this.createdBy = createdBy;
		this.flashcardID = UUID.randomUUID().toString();
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
}
