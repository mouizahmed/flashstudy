package Models;

import java.util.ArrayList;

public interface QuizDatabase {
	void addQuiz(QuizCreator quizCreator, QuizSession quizSession);
	ArrayList<QuizSession> getAllQuizzes();
	QuizSession getQuiz(String quizID);
	ArrayList<QuizSession> getDeckQuizzes(String deckID);
	
	
}
