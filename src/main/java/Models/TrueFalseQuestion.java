package Models;

public class TrueFalseQuestion extends Question {

	private boolean answer;
	
	public TrueFalseQuestion(String question, boolean answer) {
		super("True or False", question);
		this.answer = answer;
		// TODO Auto-generated constructor stub
	}
	
	public void setAnswer(boolean answer) {
		this.answer = answer;
	}
	
	@Override
	public boolean checkAnswer(String userAnswer) {
		boolean userAnswerBool = Boolean.parseBoolean(userAnswer);
		return userAnswerBool == this.answer;
	}

}
