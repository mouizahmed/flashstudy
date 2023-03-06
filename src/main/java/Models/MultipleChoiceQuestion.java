package Models;

public class MultipleChoiceQuestion extends Question {

	private String answer;
	private String[] options = new String[4];
	
	public MultipleChoiceQuestion(String question, String answer, String[] options) {
		super("Multiple Choice", question);
		this.answer = answer;
		this.options = options;
		// TODO Auto-generated constructor stub
	}
	
	public String[] getAllOptions() {
		return this.options;
	}
	
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	
	@Override
	public boolean checkAnswer(String userAnswer) {
		return userAnswer.equals(this.answer);
	}

}
