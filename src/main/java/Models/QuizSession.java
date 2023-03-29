package Models;

import java.util.ArrayList;
import java.util.Date;

public class QuizSession {
	
	private QuizCreator quizCreator;
	
	private User user;
	private int score = 0;
	private double avgScore;
	private ArrayList<Question> questions;
	private String quizID;
	private Deck deck;
	private Date dateCreated;
	
	public QuizSession(QuizCreator quizCreator, User user) {
		this.quizCreator = quizCreator;
		this.user = user;
		this.questions = quizCreator.getAllQuestions();
		this.quizID = quizCreator.getQuizID();
		this.deck = quizCreator.getDeck();
	}
	
	public void answerQuestion(int index, String answer) {
//		if (this.questions.get(index).checkAnswer(answer)) {
//			this.score += 1;
//		}
		this.questions.get(index).checkAnswer(answer);
	}

	public int getScore() {
		this.score = 0;
		for (int i=0; i<questions.size();  i++) {
			if (questions.get(i).getCorrect() == true) {
				this.score += 1;
			}
		}
		
		return this.score;
	}
	
	
	public double getAvgScore() {
		return this.avgScore = (double) this.getScore() / (double) this.getNumQuestions();
	}
	
	public ArrayList<Question> getAllQuestions() {
		return this.questions;
	}
	
	public int getNumQuestions() {
		return quizCreator.getAllQuestions().size();
	}
	
	public String getQuizID() {
		return this.quizID;
	}
	
	public User getUser() {
		return user;
	}
	
	public Deck getDeck() {
		return this.deck;
	}
	
//	public void submitQuiz() {
//		this.dateCreated = new java.util.Date();
//		// link to sql
//	}
	

}
