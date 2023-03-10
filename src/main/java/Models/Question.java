package Models;

import java.util.UUID;

public class Question {
	private String type;
	private String question;
	private String questionID;
	protected boolean correct = false;
	
	public Question(String type, String question) {
		this.type = type;
		this.question = question;
		this.questionID = UUID.randomUUID().toString();
	}
	
	public String getType() {
		return this.type;
	}
	
	public String getID() {
		return this.questionID;
	}
	
	public String getQuestion() {
		return this.question;
	}
	
	public boolean checkAnswer(String userAnswer) {
		return false;
	}
	
	public void setCorrect(boolean attempt) {
		this.correct = attempt;
	}
	
	public boolean getCorrect() {
		return this.correct;
	}
	
	
	

}


