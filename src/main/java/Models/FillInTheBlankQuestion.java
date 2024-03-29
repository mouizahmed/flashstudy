package Models;

public class FillInTheBlankQuestion extends Question {

	private String answer;
	
	
	public FillInTheBlankQuestion(String question, String answer) {
		super("Fill in the Blank", question);
		this.answer = answer;
		// TODO Auto-generated constructor stub
	}
	
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	
	@Override
	public boolean checkAnswer(String userAnswer) {

		if (userAnswer.equals(this.answer)) {
			this.setCorrect(true);
			return true;
		} else {
			this.setCorrect(false);
			return false;
		}
	}

}
