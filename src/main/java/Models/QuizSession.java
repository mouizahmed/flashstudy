package Models;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

public class QuizSession {

	private QuizCreator quizCreator;
	long durationInMins;

	long durationInSeconds;

	private User user;
	private int score = 0;
	private double avgScore;
	private ArrayList<Question> questions;
	private String quizID;
	private Deck deck;
	private Date dateCreated;
	private Date quizStartTime;
	private JDBC mysql_database;

	public QuizSession(QuizCreator quizCreator, User user) {
		this.quizCreator = quizCreator;
		this.user = user;
		this.questions = quizCreator.getAllQuestions();
		this.quizID = quizCreator.getQuizID();
		this.deck = quizCreator.getDeck();
		this.dateCreated = new Date();
		this.quizStartTime = new Date();
	}

	public void answerQuestion(int index, String answer) {
		this.questions.get(index).checkAnswer(answer);
	}

	public int getScore() {
		this.score = 0;
		for (int i = 0; i < questions.size(); i++) {
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
	public long getdurationInMins(){
		return this.durationInMins;
	}
	public long getDurationInSeconds(){
		return this.durationInSeconds;
	}
	public Date getDateCreated() {
		return this.dateCreated;
	}

	public void submitQuiz() {
		Date quizEndTime = new Date();
		long timeTaken = quizEndTime.getTime() - quizStartTime.getTime();
		this.durationInSeconds = (timeTaken / 1000);


		try {
			this.mysql_database = new JDBC();
		} catch (IOException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
