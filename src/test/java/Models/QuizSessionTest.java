package Models;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class QuizSessionTest {
	
	private Deck deck;
	private QuizCreator quizCreator;
	private QuizSession quizSession;
	private User user;
	private ArrayList<Question> questions;

	@BeforeEach
	public void setUp() {
		String deckID = UUID.randomUUID().toString();
		Flashcard f1 = new Flashcard("What is the capital of France?", "Paris", "username", deckID, UUID.randomUUID().toString());
		Flashcard f2 = new Flashcard("What is the capital of Spain?", "Madrid", "username", deckID, UUID.randomUUID().toString());
		ArrayList<Flashcard> flashcards = new ArrayList<>();
		flashcards.add(f1);
		flashcards.add(f2);
		user = new User("username", "user@gmail.com", "12345678", LocalDate.of(2023, 04, 10));
		deck = new Deck("Deck Title", flashcards, "username", true, deckID, "Test School", "Faculty Name", "Description", "Course Name");
		quizCreator = new QuizCreator(deck);
		quizSession = new QuizSession(quizCreator, user);
		questions = quizCreator.getAllQuestions();
	}
	
	@Test
	public void testAnswerQuestion() {
		
		for (int i = 0; i < quizCreator.getAllQuestions().size(); i++) {
			
			System.out.println(quizCreator.getAllQuestions().get(i).getQuestion());

			// quizCreator.getAllQuestions().get(i).getType();
			
			if (quizCreator.getAllQuestions().get(i).getType().equals("Multiple Choice")) {
				quizSession.answerQuestion(i, deck.getAllFlashcards().get(i).getAnswer());
			} else if (quizCreator.getAllQuestions().get(i).getType().equals("True or False")) {
				int currentScore = quizSession.getScore();
				quizSession.answerQuestion(i, "true");
				if (quizSession.getScore() == currentScore) {
					quizSession.answerQuestion(i, "false");
				}
			} else {
				quizSession.answerQuestion(i, deck.getAllFlashcards().get(i).getAnswer());
				if (quizSession.getScore() == 0) {
					quizSession.answerQuestion(i, deck.getAllFlashcards().get(i).getAnswer());
				}
			}
		}
		
		assertEquals(2, quizSession.getScore());
	}
	
	@Test
	public void testGetScore() {
		quizSession.answerQuestion(0, "Paris");
		quizSession.answerQuestion(1, "Barcelona");
		
		assertEquals(1, quizSession.getScore());
	}
	
	@Test
	public void testGetAvgScore() {
		quizSession.answerQuestion(0, "Paris");
		quizSession.answerQuestion(1, "Madrid");
		
		assertEquals(1.0, quizSession.getAvgScore(), 0.0);
	}
	
	@Test
	public void testGetAllQuestions() {
		assertEquals(questions, quizSession.getAllQuestions());
	}
	
	@Test
	public void testGetNumQuestions() {
		assertEquals(2, quizSession.getNumQuestions());
	}
	
	@Test
	public void testGetQuizID() {
		assertEquals(quizCreator.getQuizID(), quizSession.getQuizID());
	}
	
	@Test
	public void testGetUser() {
		assertEquals(user, quizSession.getUser());
	}
	
	@Test
	public void testGetDeck() {
		assertEquals(deck, quizSession.getDeck());
	}

}
