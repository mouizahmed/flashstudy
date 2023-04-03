package Models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
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

	public void submitQuiz() {
		Date quizEndTime = new Date();
		long timeTaken = quizEndTime.getTime() - quizStartTime.getTime();
		int durationInSeconds = (int) (timeTaken / 1000);

		try {

			String url = "jdbc:mysql://us-cdbr-east-06.cleardb.net:3306/heroku_957a5ec054245a7?reconnect=true";
			String username = "b94e112144658f";
			String password = "7af4eb5c";
			Connection conn = DriverManager.getConnection(url, username, password);

			// Create a prepared statement to insert the quiz result into the quiz_results table
			String sql = "INSERT INTO quiz_results (username, quiz_id, score, time_taken, date_created) VALUES (?, ?, ?, ?, ?)";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, user.getUsername());
			stmt.setString(2, quizID);
			stmt.setInt(3, score);
			stmt.setLong(4, timeTaken);
			stmt.setTimestamp(5, new java.sql.Timestamp(dateCreated.getTime()));

			stmt.executeUpdate();

			stmt.close();
			conn.close();
		} catch (SQLException e) {
			System.out.println("Error: " + e.getMessage());
		}
	}
}
