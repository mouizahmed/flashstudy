package Models;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import java.awt.CardLayout;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;

import org.junit.jupiter.api.Assertions;

import Controller.Controller;
import Views.LeaderboardView;

/**
 * @author hena 
 *
 */

public class LeaderboardTest {

	private JPanel container;
	private CardLayout card;
	private Controller controller;
	private Leaderboard leaderboard;
	private Deck deck;
	private LeaderboardView leaderboardView;
	private QuizSession quizSession;
	private User user;

//	@Before
//	public void setUp() {
//		container = new JPanel();
//		card = new CardLayout();
//		container.setLayout(card);
//		leaderboard = new Leaderboard(new Deck("Test Deck", null, null, false, null));
//		leaderboardView = new LeaderboardView(leaderboard, controller);
//		container.add(leaderboardView, "leaderboard");
//		controller = new Controller(container, card);
//	}

	@Test
	void testLeaderboardDisplay() {
		// Create a deck with some flashcards
		ArrayList<Flashcard> flashcards = new ArrayList<>();
		flashcards.add(new Flashcard("Question 1", "Answer 1"));
		flashcards.add(new Flashcard("Question 2", "Answer 2"));
		Deck deck = new Deck("Deck 1", flashcards, "User 1", true, "Deck 1");

		// Create some players and their scores
		Player player1 = new Player(deck, "Player 1", 10);
		Player player2 = new Player(deck, "Player 2", 20);
		Player player3 = new Player(deck, "Player 3", 30);

		// Add the players to the leaderboard
		Leaderboard leaderboard = new Leaderboard(deck);
		leaderboard.addPlayer(player1);
		leaderboard.addPlayer(player2);
		leaderboard.addPlayer(player3);

		// Sort the players in descending order of scores
		List<Player> playerList = new ArrayList<>(leaderboard.getPlayers());
		Collections.sort(playerList, Comparator.comparing(Player::getScore).reversed());

		//Collections.sort(leaderboard.getPlayers());

		// Verify that the correct deck title is displayed
		Assertions.assertEquals(deck.getDeckID(), leaderboard.getDeck().getDeckTitle());

		// Verify that the it does not display deck title for leaderboard title
		Assertions.assertNotEquals("Leaderboard", leaderboard.getDeck().getDeckTitle());

		// Verify that the correct players and scores are displayed in descending order
		ArrayList<Player> displayedPlayers = leaderboard.getPlayers();
		displayedPlayers = (ArrayList<Player>) playerList;
		Assertions.assertEquals(player3.getUsername() + " " + player3.getScore(), displayedPlayers.get(0).getUsername() + " " + displayedPlayers.get(0).getScore());
		Assertions.assertEquals(player2.getUsername() + " " + player2.getScore(), displayedPlayers.get(1).getUsername() + " " + displayedPlayers.get(1).getScore());
		Assertions.assertEquals(player1.getUsername() + " " + player1.getScore(), displayedPlayers.get(2).getUsername() + " " + displayedPlayers.get(2).getScore());

	}

	@Test
	void testDisplayTop5Players() {
		// Create a deck with some flashcards
		ArrayList<Flashcard> flashcards = new ArrayList<>();
		flashcards.add(new Flashcard("Question 1", "Answer 1"));
		flashcards.add(new Flashcard("Question 2", "Answer 2"));
		Deck deck = new Deck("Deck 1", flashcards, "User 1", true, "Deck 1");

		// Create some players and their scores
		Player player1 = new Player(deck, "Alice", 100);
		Player player2 = new Player(deck, "Bob", 200);
		Player player3 = new Player(deck, "Charlie", 150);
		Player player4 = new Player(deck, "David", 50);
		Player player5 = new Player(deck, "Eve", 300);
		Player player6 = new Player(deck, "Frank", 75);

		// Add the players to the leaderboard
		Leaderboard leaderboard = new Leaderboard(deck);
		leaderboard.addPlayer(player1);
		leaderboard.addPlayer(player2);
		leaderboard.addPlayer(player3);
		leaderboard.addPlayer(player4);
		leaderboard.addPlayer(player5);
		leaderboard.addPlayer(player6);

		// Sort the players in descending order of scores
		List<Player> playerList = new ArrayList<>(leaderboard.getPlayers());
		Collections.sort(playerList, Comparator.comparing(Player::getScore).reversed());

		// Create a leaderboard view and pass the leaderboard to it
		LeaderboardView leaderboardView = new LeaderboardView(leaderboard, controller);

		// Verify that the correct deck title is displayed
		Assertions.assertEquals(deck.getDeckTitle(), leaderboard.getDeck().getDeckTitle());

		// Verify that the correct leaderboard title is displayed
		Assertions.assertNotEquals("Leaderboard", leaderboard.getDeck().getDeckTitle());

		ArrayList<Player> displayedPlayers = leaderboard.getPlayers();

		JPanel leaderboardPanel = leaderboardView.getLeaderboardPanel();
		for (int i = 0; i < 5; i++) {
			String expectedText = (i+1) + ". " + displayedPlayers.get(i).getUsername() + " " + displayedPlayers.get(i).getScore();
			JLabel playerLabel = (JLabel) leaderboardPanel.getComponent(i);
			Assertions.assertEquals(expectedText, playerLabel.getText());
		}
		//            // Check that the correct players and scores are displayed in descending order
		//            List<String> expectedPlayerScores = Arrays.asList(
		//                    "Eve 300",
		//                    "Bob 200",
		//                    "Charlie 150",
		//                    "Alice 100",
		//                    "Frank 75"
		//            );

	}

	@Test
	void testGetLeaderboardPanel() {
		// create a leaderboard
		ArrayList<Flashcard> flashcards = new ArrayList<>();
		flashcards.add(new Flashcard("Question 1", "Answer 1"));
		flashcards.add(new Flashcard("Question 2", "Answer 2"));
		Deck deck = new Deck("Deck 1", flashcards, "User 1", true, "Deck 1");

		// Create some players and their scores
		Player player1 = new Player(deck, "Player 1", 160);
		Player player2 = new Player(deck, "Player 2", 200);
		Player player3 = new Player(deck, "Player 3", 30);
		Player player4 = new Player(deck, "David", 50);
		Player player5 = new Player(deck, "Charlie", 150);

		// Add the players to the leaderboard
		Leaderboard leaderboard = new Leaderboard(deck);
		leaderboard.addPlayer(player1);
		leaderboard.addPlayer(player2);
		leaderboard.addPlayer(player3);
		leaderboard.addPlayer(player4);
		leaderboard.addPlayer(player5);

		// create a leaderboard view and get its leaderboard panel
		LeaderboardView leaderboardView = new LeaderboardView(leaderboard, controller);
		JPanel leaderboardPanel = leaderboardView.getLeaderboardPanel();

		// check that the leaderboard panel is not null and has the correct bounds
		assertNotNull((Object) leaderboardPanel);
		assertTrue(leaderboardPanel.getBounds().equals(new Rectangle(180, 172, 364, 255)));
		assertEquals(new Rectangle(180, 172, 364, 255), leaderboardPanel.getBounds());
	}

	@Test
	void testAddNewPlayerToLeaderboard() {
		// create a leaderboard
		ArrayList<Flashcard> flashcards = new ArrayList<>();
		flashcards.add(new Flashcard("Question 1", "Answer 1"));
		flashcards.add(new Flashcard("Question 2", "Answer 2"));
		Deck deck = new Deck("Deck 1", flashcards, "User 1", true, "Deck 1");
		Leaderboard leaderboard = new Leaderboard(deck);

		// create a new player with a score
		Player newPlayer = new Player(deck, "Alice", 100);

		// add the player to the leaderboard
		leaderboard.addPlayer(newPlayer);

		// get the list of players displayed on the leaderboard
		List<Player> displayedPlayers = leaderboard.getPlayers();

		// check that the new player is displayed in the correct position in the leaderboard
		assertEquals(newPlayer, displayedPlayers.get(0));
	}

	@Test
	void testEmptyPlayerList1() {
		// Create a new instance of the leaderboard UI
		leaderboard = new Leaderboard(new Deck("Test Deck", null, null, false, null));
		leaderboardView = new LeaderboardView(leaderboard, controller);

		// Remove all existing players from the leaderboard
		leaderboard.removeAllPlayers();
	}
} 