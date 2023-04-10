package Models;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.UUID;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.ResultSet;

class JDBCTest {
	static JDBC connection;
	static Exception exception;
	
	@BeforeAll
	static void init() {
		try {
			connection = new JDBC();
			connection.setAutoCommit(false);
		} catch (IOException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			exception = e;
		}
	}

	@Test
	void testConnection() {
		assertFalse(connection.connNull);
		assertTrue(connection.connIsValid);
		assertNull(exception);
		
	}
	
	@Test
	void testCreateNewUser() {
		
		Connection conn = JDBC.getConn();
		String username = "";
		try {
			User newUser = connection.createNewUser("newTestUser", "newTestUser@gmail.com", "12345678", "12345678");
		} catch (NoSuchAlgorithmException | InvalidKeySpecException | SQLException e) {
			e.printStackTrace();
		}
		
		try {
			Statement stmt = conn.createStatement();
			String sql = "SELECT * FROM Users WHERE username='" + "newTestUser'";
			ResultSet rs = stmt.executeQuery(sql);
			if (rs.next()) {
				username = rs.getString("username");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		assertEquals("newTestUser", username);
	}
	
	@Test
	void testCreateDuplicateUsername() {
	
		assertThrows(SQLException.class, ()-> {
			User newUser = connection.createNewUser("mouizahmed", "new@gmail.com", "12345678", "123456789");
		});
	}
	
	@Test
	void testCreateDuplicateEmail() {
		assertThrows(SQLException.class, ()-> {
			User newUser = connection.createNewUser("newName", "mouizahmed1@gmail.com", "12345678", "123456789");
		});
	}

	@Test
	void testVerifyUser() {
		try {
			User newUser = connection.createNewUser("newTestUser1", "newTestUser1@gmail.com", "12345678", "12345678");
			User getUser = connection.verifyUser("newTestUser1", "12345678");
			assertNotNull(getUser);
		} catch (NoSuchAlgorithmException | InvalidKeySpecException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	void testVerifyInvalidUser() {
		try {
			User newUser = connection.createNewUser("duplicateuser", "duplicateuser@gmail.com", "12345678", "12345678");
		} catch (NoSuchAlgorithmException | InvalidKeySpecException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// Sign in with a username that doesn't exist.
		assertThrows(SQLException.class, ()-> {
			User getUser = connection.verifyUser("duplicateUser1", "12345678");
		});
	}
	
	@Test
	void testVerifyInvalidPassword() {
		try {
			User newUser = connection.createNewUser("anotheruser", "anotheruser@gmail.com", "12345678", "12345678");
		} catch (NoSuchAlgorithmException | InvalidKeySpecException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// Sign in with an incorrect password.
		assertThrows(IllegalArgumentException.class, ()-> {
			User getUser = connection.verifyUser("anotheruser", "123456789");
		});
	}

	@Test
	void testAddDeckToProfile() {
		Connection conn = JDBC.getConn();
		String deckTitle = "";
		boolean result = false;
		
		User newUser = null;
		User newUser1 = null;
		try {
			newUser = connection.createNewUser("newerUser1", "newerUser1@gmail.com", "12345678", "12345678");
			newUser1 = connection.createNewUser("testerUser", "testerUser@gmail.com", "12345678", "12345678");
		} catch (NoSuchAlgorithmException | InvalidKeySpecException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String deckID = UUID.randomUUID().toString();
		Flashcard flashcard = new Flashcard("Question 1", "Answer 1", newUser.getUsername(), deckID, UUID.randomUUID().toString());
		ArrayList<Flashcard> flashcards = new ArrayList<>();
		flashcards.add(flashcard);
		
		
		try {
			Deck deck = connection.createDeck("New Title", flashcards, true, newUser, deckID, "Test University", "Test Faculty", "Test Description", "Test Course");
			Deck copiedDeck = connection.addDeckToProfile(deck, newUser1);
			Statement stmt = conn.createStatement();
			String sql = "SELECT * FROM Decks WHERE deckTitle='" + "New Title' AND createdBy='" + newUser1.getUsername() + "'";
			ResultSet rs = stmt.executeQuery(sql);
			if (rs.next()) {
				deckTitle = rs.getString("deckTitle");
				result = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		assertTrue(result);
	}

	@Test
	void testCreateDeck() {
		
		Connection conn = JDBC.getConn();
		String deckTitle = "";
		
		User newUser = null;
		try {
			newUser = connection.createNewUser("newerUser", "newerUser@gmail.com", "12345678", "12345678");
			String deckID = UUID.randomUUID().toString();
			Flashcard flashcard = new Flashcard("Question 1", "Answer 1", newUser.getUsername(), deckID, UUID.randomUUID().toString());
			ArrayList<Flashcard> flashcards = new ArrayList<>();
			flashcards.add(flashcard);
			Deck deck = connection.createDeck("Test Deck", flashcards, true, newUser, deckID, "Test University", "Test Faculty", "Test Description", "Test Course");
			Statement stmt = conn.createStatement();
			String sql = "SELECT * FROM Decks WHERE deckTitle='" + "Test Deck'";
			ResultSet rs = stmt.executeQuery(sql);
			if (rs.next()) {
				deckTitle = rs.getString("deckTitle");
			}
		} catch (NoSuchAlgorithmException | InvalidKeySpecException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		assertEquals("Test Deck", deckTitle);
	}
	
	@Test
	void testCreateDeckIncorrectFlashcards() {
		Connection conn = JDBC.getConn();
		String deckTitle = "";
		
//		User newUser = null;
		
			
			
			assertThrows(SQLException.class, ()-> {
				User newUser = connection.createNewUser("test6", "test6@gmail.com", "12345678", "12345678");
				String deckID = UUID.randomUUID().toString();
				Flashcard flashcard = new Flashcard("Question 1", "Answer 1", newUser.getUsername(), "gg", UUID.randomUUID().toString());
				ArrayList<Flashcard> flashcards = new ArrayList<>();
				flashcards.add(flashcard);
				Deck deck = connection.createDeck("Test Deck", flashcards, true, newUser, deckID, "Test University", "Test Faculty", "Test Description", "Test Course");
			});
			
		
		
		
	}

	@Test
	void testCreateQuiz() {
		
		Connection conn = JDBC.getConn();
		String quizID = "";
		int quizScore = 0;
		
		try {
			// Login
			
			User newUser = connection.createNewUser("user123", "user123@gmail.com", "12345678", "12345678");
			
			String deckID = UUID.randomUUID().toString();
			Flashcard flashcard = new Flashcard("Question 1", "Answer 1", newUser.getUsername(), deckID, UUID.randomUUID().toString());
			ArrayList<Flashcard> flashcards = new ArrayList<>();
			flashcards.add(flashcard);
			Deck deck = connection.createDeck("Test Deck", flashcards, true, newUser, deckID, "Test University", "Test Faculty", "Test Description", "Test Course");
			Statement stmt = conn.createStatement();
			String sql = "SELECT * FROM Decks WHERE deckTitle='" + "Test Deck'";
			ResultSet rs = stmt.executeQuery(sql);
			User getUser = connection.verifyUser("user123", "12345678");
			QuizCreator quizCreator = new QuizCreator(getUser.getUserDeckList().get(0));
			QuizSession quizSession = new QuizSession(quizCreator, getUser);
			for (int i = 0; i < quizCreator.getAllQuestions().size(); i++) {
	
				System.out.println(quizCreator.getAllQuestions().get(i).getQuestion());

				// quizCreator.getAllQuestions().get(i).getType();
				
				if (quizCreator.getAllQuestions().get(i).getType().equals("Multiple Choice")) {
					quizSession.answerQuestion(i, getUser.getUserDeckList().get(0).getAllFlashcards().get(i).getAnswer());
				} else if (quizCreator.getAllQuestions().get(i).getType().equals("True or False")) {
					quizSession.answerQuestion(i, "true");
				} else {
					quizSession.answerQuestion(i, "Answer");
					if (quizSession.getScore() == 0) {
						quizSession.answerQuestion(i, "1");
					}
				}
				connection.createQuiz(quizSession);
				
				Statement stmt1 = conn.createStatement();
				String sql1 = "SELECT * FROM QUIZZES WHERE quizID='" + quizSession.getQuizID() + "'";
				ResultSet rs1 = stmt1.executeQuery(sql1);
				if (rs1.next()) {
					quizID = rs1.getString("quizID");
					quizScore = rs1.getInt("avgScore");
				}
				System.out.println(quizScore);
				System.out.println(quizSession.getAvgScore());
				assertEquals(quizID, quizSession.getQuizID());
				assertEquals(quizScore, quizSession.getAvgScore());
				assertEquals(1, quizScore);
				assertEquals(1, quizSession.getAvgScore());
//				System.out.println(quizSession.getScore());
				
				
			}
		} catch (NoSuchAlgorithmException | InvalidKeySpecException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}

	@Test
	void testSetFlashcardDifficulty() {
		
		Connection conn = JDBC.getConn();
		String difficulty = "";
		
		User newUser = null;
		try {
			newUser = connection.createNewUser("newerUser2", "newerUser2@gmail.com", "12345678", "12345678");
			String deckID = UUID.randomUUID().toString();
			Flashcard flashcard = new Flashcard("Question 1", "Answer 1", newUser.getUsername(), deckID, UUID.randomUUID().toString());
			ArrayList<Flashcard> flashcards = new ArrayList<>();
			flashcards.add(flashcard);
			Deck deck = connection.createDeck("Test Deck", flashcards, true, newUser, deckID, "Test University", "Test Faculty", "Test Description", "Test Course");
			
			// Set Flashcard difficulty to hard
			connection.setFlashcardDifficulty(flashcard, "Hard");
			Statement stmt = conn.createStatement();
			String sql = "SELECT * FROM Flashcards WHERE flashcardID='" + flashcard.getFlashcardID() + "'";
			ResultSet rs = stmt.executeQuery(sql);
			if (rs.next()) {
				difficulty = rs.getString("difficultyColor");
			}
		} catch (NoSuchAlgorithmException | InvalidKeySpecException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		assertEquals("Hard", difficulty);
	}
	
	

	@Test
	void testGetQuizLeaderboard() {
		
		Connection conn = JDBC.getConn();
		String quizID = "";
		int quizScore = 0;
		Leaderboard leaderboard;
		
		try {
			// Login
			
			User newUser = connection.createNewUser("test3", "test3@gmail.com", "12345678", "12345678");
			
			String deckID = UUID.randomUUID().toString();
			Flashcard flashcard = new Flashcard("Question 1", "Answer 1", newUser.getUsername(), deckID, UUID.randomUUID().toString());
			ArrayList<Flashcard> flashcards = new ArrayList<>();
			flashcards.add(flashcard);
			Deck deck = connection.createDeck("Test Deck", flashcards, true, newUser, deckID, "Test University", "Test Faculty", "Test Description", "Test Course");
			Statement stmt = conn.createStatement();
			String sql = "SELECT * FROM Decks WHERE deckTitle='" + "Test Deck'";
			ResultSet rs = stmt.executeQuery(sql);
			User getUser = connection.verifyUser("test3", "12345678");
			QuizCreator quizCreator = new QuizCreator(getUser.getUserDeckList().get(0));
			QuizSession quizSession = new QuizSession(quizCreator, getUser);
			for (int i = 0; i < quizCreator.getAllQuestions().size(); i++) {
	
				System.out.println(quizCreator.getAllQuestions().get(i).getQuestion());

				// quizCreator.getAllQuestions().get(i).getType();
				
				if (quizCreator.getAllQuestions().get(i).getType().equals("Multiple Choice")) {
					quizSession.answerQuestion(i, getUser.getUserDeckList().get(0).getAllFlashcards().get(i).getAnswer());
				} else if (quizCreator.getAllQuestions().get(i).getType().equals("True or False")) {
					quizSession.answerQuestion(i, "true");
				} else {
					quizSession.answerQuestion(i, "Answer");
					if (quizSession.getScore() == 0) {
						quizSession.answerQuestion(i, "1");
					}
				}
				connection.createQuiz(quizSession);
				leaderboard = connection.getQuizLeaderboard(deck);
				
				assertEquals(1, leaderboard.getPlayers().size());
				
				
			}
		} catch (NoSuchAlgorithmException | InvalidKeySpecException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		
	}

	@Test
	void testQuizAttempts() {
		
		Connection conn = JDBC.getConn();
		String quizID = "";
		int quizScore = 0;
		int attempts = 0;
		
		try {
			// Login
			
			User newUser = connection.createNewUser("test4", "test4@gmail.com", "12345678", "12345678");
			
			String deckID = UUID.randomUUID().toString();
			Flashcard flashcard = new Flashcard("Question 1", "Answer 1", newUser.getUsername(), deckID, UUID.randomUUID().toString());
			ArrayList<Flashcard> flashcards = new ArrayList<>();
			flashcards.add(flashcard);
			Deck deck = connection.createDeck("Test Deck", flashcards, true, newUser, deckID, "Test University", "Test Faculty", "Test Description", "Test Course");
			Statement stmt = conn.createStatement();
			String sql = "SELECT * FROM Decks WHERE deckTitle='" + "Test Deck'";
			ResultSet rs = stmt.executeQuery(sql);
			User getUser = connection.verifyUser("test4", "12345678");
			QuizCreator quizCreator = new QuizCreator(getUser.getUserDeckList().get(0));
			QuizSession quizSession = new QuizSession(quizCreator, getUser);
			for (int i = 0; i < quizCreator.getAllQuestions().size(); i++) {
	
				System.out.println(quizCreator.getAllQuestions().get(i).getQuestion());

				// quizCreator.getAllQuestions().get(i).getType();
				
				if (quizCreator.getAllQuestions().get(i).getType().equals("Multiple Choice")) {
					quizSession.answerQuestion(i, getUser.getUserDeckList().get(0).getAllFlashcards().get(i).getAnswer());
				} else if (quizCreator.getAllQuestions().get(i).getType().equals("True or False")) {
					quizSession.answerQuestion(i, "true");
				} else {
					quizSession.answerQuestion(i, "Answer");
					if (quizSession.getScore() == 0) {
						quizSession.answerQuestion(i, "1");
					}
				}
				connection.createQuiz(quizSession);
				attempts = connection.quizAttempts(deck, getUser);
				assertEquals(1, attempts);
				
				
				
			}
		} catch (NoSuchAlgorithmException | InvalidKeySpecException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	
	}

	@Test
	void testPublicDeckList() {
		
		ArrayList<Deck> originalPop = connection.publicDeckList();
		ArrayList<Deck> afterPop = new ArrayList<>();
		int diff = 0;
		Connection conn = JDBC.getConn();
		String deckTitle = "";
		
		User newUser = null;
		try {
			newUser = connection.createNewUser("test1", "test1@gmail.com", "12345678", "12345678");
			String deckID = UUID.randomUUID().toString();
			String deck1ID = UUID.randomUUID().toString();
			Flashcard flashcard = new Flashcard("Question 1", "Answer 1", newUser.getUsername(), deckID, UUID.randomUUID().toString());
			Flashcard flashcard1 = new Flashcard("Test Question 1", "Test Answer 1", newUser.getUsername(), deck1ID, UUID.randomUUID().toString());
			ArrayList<Flashcard> flashcards = new ArrayList<>();
			ArrayList<Flashcard> flashcards1 = new ArrayList<>();
			flashcards.add(flashcard);
			flashcards1.add(flashcard1);
			Deck deck = connection.createDeck("Test Deck", flashcards, true, newUser, deckID, "Test University", "Test Faculty", "Test Description", "Test Course");
			Deck deck1 = connection.createDeck("Test Deck 2", flashcards1, false, newUser, deck1ID, "Test University", "Test Faculty", "Test Description", "Test Course 1");
			Statement stmt = conn.createStatement();
			afterPop = connection.publicDeckList();
			diff = afterPop.size() - originalPop.size();
//			String sql = "SELECT * FROM Decks WHERE deckTitle='" + "Test Deck'";
//			ResultSet rs = stmt.executeQuery(sql);
//			if (rs.next()) {
//				deckTitle = rs.getString("deckTitle");
//			}
			
			
		} catch (NoSuchAlgorithmException | InvalidKeySpecException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		assertEquals(1, diff);
	}


	@Test
	void testSearchPublicDeckQuery() {
		
		Connection conn = JDBC.getConn();
		String deckTitle = "";
		
		User newUser = null;
		try {
			newUser = connection.createNewUser("test2", "test2@gmail.com", "12345678", "12345678");
			String deckID = UUID.randomUUID().toString();
		
			Flashcard flashcard = new Flashcard("Question 1", "Answer 1", newUser.getUsername(), deckID, UUID.randomUUID().toString());
			
			ArrayList<Flashcard> flashcards = new ArrayList<>();
			ArrayList<Flashcard> flashcards1 = new ArrayList<>();
			flashcards.add(flashcard);
	
			Deck deck = connection.createDeck("Brand New Deck 123", flashcards, true, newUser, deckID, "Test University", "Test Faculty", "Test Description", "Test Course");
			ArrayList<Deck> searchedDeck = connection.searchPublicDeckQuery(deck.getDeckTitle());
			assertEquals(searchedDeck.get(0).getDeckTitle(), deck.getDeckTitle());
		} catch (NoSuchAlgorithmException | InvalidKeySpecException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	@Test
	void testSearchPublicDeckQueryNoResults() {
		
		Connection conn = JDBC.getConn();
		String deckTitle = "";
		
		User newUser = null;
		try {
			newUser = connection.createNewUser("test7", "test7@gmail.com", "12345678", "12345678");
			String deckID = UUID.randomUUID().toString();
		
			Flashcard flashcard = new Flashcard("Question 1", "Answer 1", newUser.getUsername(), deckID, UUID.randomUUID().toString());
			
			ArrayList<Flashcard> flashcards = new ArrayList<>();
			ArrayList<Flashcard> flashcards1 = new ArrayList<>();
			flashcards.add(flashcard);
	
			Deck deck = connection.createDeck("Brand New Deck 123", flashcards, true, newUser, deckID, "Test University", "Test Faculty", "Test Description", "Test Course");
			ArrayList<Deck> searchedDeck = connection.searchPublicDeckQuery("Brand New Deck 1234");
			assertEquals(0, searchedDeck.size());
		} catch (NoSuchAlgorithmException | InvalidKeySpecException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	@Test
	void testSearchPublicDeckQueryMultipleResults() {
		
		Connection conn = JDBC.getConn();
		String deckTitle = "";
		
		User newUser = null;
		try {
			newUser = connection.createNewUser("test8", "test8@gmail.com", "12345678", "12345678");
			String deckID = UUID.randomUUID().toString();
			String deckID1 = UUID.randomUUID().toString();
			
			Flashcard flashcard = new Flashcard("Question 1", "Answer 1", newUser.getUsername(), deckID, UUID.randomUUID().toString());
			Flashcard flashcard1 = new Flashcard("Test 2", "Test 2", newUser.getUsername(), deckID1, UUID.randomUUID().toString());
			ArrayList<Flashcard> flashcards = new ArrayList<>();
			ArrayList<Flashcard> flashcards1 = new ArrayList<>();
			flashcards.add(flashcard);
			flashcards1.add(flashcard1);
	
			Deck deck = connection.createDeck("Custom Deck 1", flashcards, true, newUser, deckID, "Test University", "Test Faculty", "Test Description", "Test Course");
			Deck deck1 = connection.createDeck("Custom Deck 2", flashcards1, true, newUser, deckID1, "Test University 1", "Test Faculty 1", "Test Description 1", "Test Course 1");
			ArrayList<Deck> searchedDeck = connection.searchPublicDeckQuery("Custom");
			assertEquals(2, searchedDeck.size());
		} catch (NoSuchAlgorithmException | InvalidKeySpecException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
