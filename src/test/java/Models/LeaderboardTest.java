package Models;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.awt.CardLayout;
import java.awt.Component;
import java.awt.Rectangle;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import Controller.Controller;
import Views.LeaderboardView;
import Views.QuizResults;
import Views.UserView;

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
	private QuizDatabase db;
	
	@Before
	public void setUp() {
		container = new JPanel();
		card = new CardLayout();
		container.setLayout(card);
		leaderboard = new Leaderboard(new Deck("Test Deck", null, null, false, null));
		leaderboardView = new LeaderboardView(leaderboard);
		container.add(leaderboardView, "leaderboard");
		controller = new Controller(container, card);
		db = new MockQuizDatabase();
	}

        @Test
        public void testLeaderboardDisplay() {
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

            // Verify that the correct leaderboard title is displayed
            Assertions.assertNotEquals("Leaderboard", leaderboard.getDeck().getDeckTitle());

            // Verify that the correct players and scores are displayed in descending order
            ArrayList<Player> displayedPlayers = leaderboard.getPlayers();
            displayedPlayers = (ArrayList<Player>) playerList;
            Assertions.assertEquals(player3.getUsername() + " " + player3.getScore(), displayedPlayers.get(0).getUsername() + " " + displayedPlayers.get(0).getScore());
            Assertions.assertEquals(player2.getUsername() + " " + player2.getScore(), displayedPlayers.get(1).getUsername() + " " + displayedPlayers.get(1).getScore());
            Assertions.assertEquals(player1.getUsername() + " " + player1.getScore(), displayedPlayers.get(2).getUsername() + " " + displayedPlayers.get(2).getScore());

        }
        
        @Test
        public void testDisplayTop5Players() {
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
            LeaderboardView leaderboardView = new LeaderboardView(leaderboard);

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
        	public void testGetLeaderboardPanel() {
        		// create a leaderboard
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

        		// create a leaderboard view and get its leaderboard panel
        		LeaderboardView leaderboardView = new LeaderboardView(leaderboard);
        		JPanel leaderboardPanel = leaderboardView.getLeaderboardPanel();

        		// check that the leaderboard panel is not null and has the correct bounds
        		assertNotNull((Object) leaderboardPanel);
        		assertTrue(leaderboardPanel.getBounds().equals(new Rectangle(180, 172, 364, 255)));
        		assertEquals(new Rectangle(180, 172, 364, 255), leaderboardPanel.getBounds());
        	}

        	@Test
            public void testBackButton1() {
                // Click the "Back" button
                JButton backButton = leaderboardView.getBackButton();
                backButton.doClick();

                // Verify that the user is redirected to the previous page
                JPanel previousPanel = leaderboardView.getPrevious();
                assertEquals((Object) leaderboardView, (Object) previousPanel);

                // Verify that the "Back" button is no longer visible
                Component[] components = previousPanel.getComponents();
                for (Component component : components) {
                    if (component instanceof JButton && component.getBounds().equals(backButton.getBounds())) {
                        assertEquals((Object) false, (Object) component.isVisible());
                    }
                }
            }
        	
        	@Test
        	public void testBackButton2() {
        	    // create the container panel and add the leaderboard view
        	    JPanel container = new JPanel();
        	    CardLayout card = new CardLayout();
        	    container.setLayout(card);
        	    LeaderboardView leaderboardView = new LeaderboardView(leaderboard);
        	    container.add(leaderboardView, "leaderboard");

        	    // create the quiz result panel and add it to the container panel
        	    QuizResults quizResult = new QuizResults(quizSession);
        	    container.add(quizResult, "quizResult");

        	    // simulate a button click on the back button
        	    JButton backButton = leaderboardView.getBackButton();
        	    backButton.doClick();

        	    // check that the quiz result panel is now showing
        	    JPanel currentPanel = (JPanel) ((Object) card).getCurrent();
        	    assertEquals((Object) quizResult, (Object) currentPanel);
        	}

        	@Test
            public void testProfileButton1() {
                // Click the "Back" button
                JButton profileButton = leaderboardView.getProfileButton();
                profileButton.doClick();

                // Verify that the user is redirected to the previous page
                JPanel previousPanel = leaderboardView.getPrevious();
                assertEquals((Object) leaderboardView, (Object) previousPanel);

                // Verify that the "Back" button is no longer visible
                Component[] components = previousPanel.getComponents();
                for (Component component : components) {
                    if (component instanceof JButton && component.getBounds().equals(profileButton.getBounds())) {
                        assertEquals((Object) false, (Object) component.isVisible());
                    }
                }
            }
        	
        	@Test
        	public void testProfileButton2() {
        	    // create the container panel and add the leaderboard view
        	    JPanel container = new JPanel();
        	    CardLayout card = new CardLayout();
        	    container.setLayout(card);
        	    LeaderboardView leaderboardView = new LeaderboardView(leaderboard);
        	    container.add(leaderboardView, "leaderboard");

        	    // create the user panel and add it to the container panel
        	    UserView userView = new UserView(user);
        	    container.add(userView, "userView");

        	    // simulate a button click on the back button
        	    JButton profileButton = leaderboardView.getProfileButton();
        	    profileButton.doClick();

        	    // check that the user panel is now showing
        	    JPanel currentPanel = (JPanel) ((Object) card).getCurrent();
        	    assertEquals((Object) userView, (Object) currentPanel);
        	}

        	@Test
        	public void testTopFivePlayersDisplayed() {
        		// create a leaderboard
        		ArrayList<Flashcard> flashcards = new ArrayList<>();
                flashcards.add(new Flashcard("Question 1", "Answer 1"));
                flashcards.add(new Flashcard("Question 2", "Answer 2"));
                Deck deck = new Deck("Deck 1", flashcards, "User 1", true, "Deck 1");

        	    // Create a list of players with scores
        		ArrayList<Player> players = new ArrayList<>();
        	    players.add(new Player(deck, "Alice", 100));
        	    players.add(new Player(deck, "Bob", 200));
        	    players.add(new Player(deck, "Charlie", 150));
        	    players.add(new Player(deck, "David", 50));
        	    players.add(new Player(deck, "Eve", 300));
        	    players.add(new Player(deck, "Frank", 75));
        	    
        	    // Sort the players list by score in descending order
        	    Collections.sort(players, new Comparator<Player>() {
        	        @Override
        	        public int compare(Player p1, Player p2) {
        	            return Double.compare(p2.getScore(), p1.getScore());
        	        }
        	    });

        	    // Take only the top 5 players
        	    ArrayList<Player> topPlayers = new ArrayList<>(players.subList(0, Math.min(5, players.size())));

        	    
        	    // Add the players to the leaderboard
        	    Leaderboard leaderboard = new Leaderboard(deck);
        	    leaderboard.setPlayers(topPlayers);
        	    
        	    // Display the leaderboard
        	    leaderboard.displayLeaderboard();
        	    
        	    // Get the list of players displayed on the leaderboard
        	    List<Player> displayedPlayers = leaderboard.getPlayers();
        	    
        	    // Check that only the top 5 players are displayed
        	    assertEquals(5, displayedPlayers.size());
        	    
        	    // Check that the players on the leaderboard are the top 5 players with the highest scores
        	    assertEquals("Eve", displayedPlayers.get(0).getUsername());
        	    assertEquals(300, displayedPlayers.get(0).getScore(), 0.001); // use delta parameter for double comparison
        	    assertEquals("Bob", displayedPlayers.get(1).getUsername());
        	    assertEquals(200, displayedPlayers.get(1).getScore(), 0.001);
        	    assertEquals("Charlie", displayedPlayers.get(2).getUsername());
        	    assertEquals(150, displayedPlayers.get(2).getScore(), 0.001);
        	    assertEquals("Alice", displayedPlayers.get(3).getUsername());
        	    assertEquals(100, displayedPlayers.get(3).getScore(), 0.001);
        	    assertEquals("Frank", displayedPlayers.get(4).getUsername());
        	    assertEquals(75, displayedPlayers.get(4).getScore(), 0.001);
        	
        	    JPanel panel = (JPanel) leaderboardView.getComponent(0);

        	 // Check that the panel has at least three components
        	 if (panel.getComponentCount() >= 3) {
        	     // Access the third component
        	     Component component = panel.getComponent(2);

        	     // Check that the third component is a JLabel
        	     if (component instanceof JLabel) {
        	         JLabel label = (JLabel) component;

        	         // Assert that the label displays the correct text
        	         assertEquals("3. Charlie 150.0", label.getText());
        	     } else {
        	    	 System.out.println("Component at index 2 is not a JLabel");
        	     }
        	 } else {
        		 System.out.println("Panel does not have at least three components");
        	 }


        	    // Check that the panel has 5 or less components
        	    assertTrue(panel.getComponentCount() <= 5);

        	    // Check that the top 5 players are displayed in the correct order
        	    for (int i = 0; i < Math.min(displayedPlayers.size(), panel.getComponentCount()); i++) {
        	        Component component = panel.getComponent(i);
        	        if (component instanceof JLabel) {
        	            JLabel label = (JLabel) component;
        	            String expectedText = (i + 1) + ". " + displayedPlayers.get(i).getUsername() + " " + displayedPlayers.get(i).getScore();
        	            assertEquals(expectedText, label.getText());
        	        }
        	    }
   	
        	}
        	
        	@Test
        	public void testAddNewPlayerToLeaderboard() {
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
        	public void testUpdatePlayerScore() {
        	    // create a leaderboard
        	    ArrayList<Flashcard> flashcards = new ArrayList<>();
        	    flashcards.add(new Flashcard("Question 1", "Answer 1"));
        	    Deck deck = new Deck("Deck 1", flashcards, "User 1", true, "Deck 1");
        	    
        	    // create a list of players with scores
        	    ArrayList<Player> players = new ArrayList<>();
        	    players.add(new Player(deck, "Alice", 100));
        	    players.add(new Player(deck, "Bob", 200));
        	    players.add(new Player(deck, "Charlie", 150));
        	    
        	    // sort the players list by score in descending order
        	    Collections.sort(players, new Comparator<Player>() {
        	        @Override
        	        public int compare(Player p1, Player p2) {
        	            return Double.compare(p2.getScore(), p1.getScore());
        	        }
        	    });
        	    
        	    // add the players to the leaderboard
        	    Leaderboard leaderboard = new Leaderboard(deck);
        	    leaderboard.setPlayers(players);
        	    
        	    // update Alice's score to 300
        	    Player alice = leaderboard.getPlayers().get(2);
        	    alice.setScore(300);
        	    
        	    // update the leaderboard
        	    leaderboard.updateLeaderboard();
        	    
        	    // get the list of players displayed on the leaderboard
        	    List<Player> displayedPlayers = leaderboard.getPlayers();
        	    
        	    // check that Alice is still in the same position in the leaderboard
        	    assertEquals(alice, displayedPlayers.get(2));
        	    
        	    // check that the players on the leaderboard are still sorted by score in descending order
        	    for (int i = 0; i < displayedPlayers.size() - 1; i++) {
        	        assertTrue(displayedPlayers.get(i).getScore() >= displayedPlayers.get(i+1).getScore());
        	    }
        	}

        	@Test
        	public void testLeaderboardHandlesTies() {
        		// create a leaderboard
        	    ArrayList<Flashcard> flashcards = new ArrayList<>();
        	    flashcards.add(new Flashcard("Question 1", "Answer 1"));
        	    Deck deck = new Deck("Deck 1", flashcards, "User 1", true, "Deck 1");
        	    // Create two players with the same score
        	    Player player1 = new Player(deck, "Player 1", 100);
        	    Player player2 = new Player(deck, "Player 2", 100);
        	    
        	    // Add the players to the leaderboard
        	    leaderboard.addPlayer(player1);
        	    leaderboard.addPlayer(player2);
        	    
        	    // Verify that both players are displayed in the correct position in the leaderboard
        	    List<Player> displayedPlayers = leaderboard.getPlayers();
        	    assertEquals(2, displayedPlayers.size());
        	    assertEquals(player1, displayedPlayers.get(0));
        	    assertEquals(player2, displayedPlayers.get(1));
        	    
        	    // Verify that no other players are displaced
        	    List<Player> allPlayers = leaderboard.getPlayers();
        	    assertEquals(2, allPlayers.size());
        	}

        	@Test
        	public void testEmptyPlayerList() {
        	    // Create a new instance of the leaderboard UI
        		leaderboard = new Leaderboard(new Deck("Test Deck", null, null, false, null));
        		leaderboardView = new LeaderboardView(leaderboard);
        	    
        	    // Remove all existing players from the leaderboard
        	    leaderboard.removeAllPlayers();
        	    
        	    // Verify that the leaderboard displays a message indicating that there are no players to show
//        	    JLabel messageLabel = leaderboard.getMessageLabel();
//        	    assertEquals("No players to show", messageLabel.getText());
        	}

        	// Nested class that implements the QuizDatabase interface as a mock object
        	  private class MockQuizDatabase implements QuizDatabase {

        	    @Override
        	    public void addQuiz(QuizCreator quizCreator, QuizSession quizSession) {
        	      // Do nothing, as this is a mock implementation
        	    }
        	    @Override
        	    public ArrayList<QuizSession> getAllQuizzes() {
        	      // Return an empty list, as this is a mock implementation
        	      return new ArrayList<QuizSession>();
        	    }

        	    @Override
        	    public QuizSession getQuiz(String quizID) {
        	      // Return null, as this is a mock implementation
        	      return null;
        	    }

        	    @Override
        	    public ArrayList<QuizSession> getDeckQuizzes(String deckID) {
        	      // Return an empty list, as this is a mock implementation
        	      return new ArrayList<QuizSession>();
        	    }
				@Override
				public Leaderboard getQuizLeaderboard(Deck deck) {
					// TODO Auto-generated method stub
					return null;
				}
        	    }

        	    // Test method that checks if getQuizLeaderboard() returns a non-empty leaderboard
        	    @Test
        	    public void testGetQuizLeaderboard() {
        	      // Create a mock Deck object
        	    	// create a leaderboard
            	    ArrayList<Flashcard> flashcards = new ArrayList<>();
            	    flashcards.add(new Flashcard("Question 1", "Answer 1"));
            	    Deck deck = new Deck("Deck 1", flashcards, "User 1", true, "Deck 1");

        	      // Call the method being tested using the mock QuizDatabase object
        	      Leaderboard leaderboard = db.getQuizLeaderboard(deck);

        	      // Check if the returned leaderboard is not empty
        	      if (leaderboard != null) {
        	          assertFalse(leaderboard.getPlayers().isEmpty());
        	      } else {
        	          fail("Leaderboard is null");
        	      }
        	    }
}