package Models;

public class TrueFalseQuestion extends Question {

	private boolean answer;
	private String fakeAnswer;
	
	public TrueFalseQuestion(String question, boolean answer, String fakeAnswer) {
		super("True or False", question);
		this.answer = answer;
		this.fakeAnswer = fakeAnswer;
		// TODO Auto-generated constructor stub
	}
	
	public void setAnswer(boolean answer) {
		this.answer = answer;
	}
	
	public String getFakeAnswer() {
		return fakeAnswer;
	}
	
	@Override
	public boolean checkAnswer(String userAnswer) {
		boolean userAnswerBool = Boolean.parseBoolean(userAnswer);
		
		if (userAnswerBool == this.answer) {
			setCorrect(true);
			return true;
		} else {
			setCorrect(false);
			return false;
		}
		
	}

}
