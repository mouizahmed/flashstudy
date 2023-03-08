package Models;

public class QuizSession {
	
	private QuizCreator quizCreator;
	
	private User user;
	private int score;
	private int avgScore;
	
	
	public QuizSession(QuizCreator quizCreator, User user) {
		this.quizCreator = quizCreator;
		this.user = user;
	}
	
	
	

}
