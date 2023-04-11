package Models;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.UUID;

import org.junit.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class QuizCreatorTest {

	private Deck deck;
	private QuizCreator quizCreator;

	@BeforeEach
	public void setUp() {
		String deckID = UUID.randomUUID().toString();
		Flashcard f1 = new Flashcard("What is the capital of France?", "Paris", "username", deckID, UUID.randomUUID().toString());
		Flashcard f2 = new Flashcard("What is the largest country in the world by area?", "Russia", "username", deckID, UUID.randomUUID().toString());
		Flashcard f3 = new Flashcard("What is the tallest mammal?", "Giraffe", "username", deckID, UUID.randomUUID().toString());
		ArrayList<Flashcard> flashcards = new ArrayList<>();
		flashcards.add(f1);
		flashcards.add(f2);
		flashcards.add(f3);
		deck = new Deck("Deck Title", flashcards, "username", true, deckID, "Test School", "Faculty Name", "Description", "Course Name");
		
		quizCreator = new QuizCreator(deck);
	}

	@Test
	public void testGetAllQuestions() {
		ArrayList<Question> questions = quizCreator.getAllQuestions();
		assertEquals(3, questions.size());
	}

	@Test
	public void testGetQuizID() {
		String quizID = quizCreator.getQuizID();
		assertNotNull(quizID);
	}

	@Test
	public void testGetDeck() {
		Deck deck = quizCreator.getDeck();
		assertNotNull(deck);
		assertEquals(3, deck.getAllFlashcards().size());
	}

}
