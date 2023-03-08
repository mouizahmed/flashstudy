package Models;

public class Question {
	private String type;
	private String question;
	
	public Question(String type, String question) {
		this.type = type;
		this.question = question;
	}
	
	public String getType() {
		return this.type;
	}
	
	public String getQuestion() {
		return this.question;
	}
	
	public boolean checkAnswer(String userAnswer) {
		return false;
	}

}


