package Controller;


import java.awt.CardLayout;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.UUID;
import java.util.regex.Pattern;

import javax.swing.JPanel;

import Models.Deck;
import Models.Flashcard;
import Models.JDBC;
import Models.User;
import Views.BrowsePublicDeckPage;
import Views.CreateDeckPage;
import Views.LoginPage;
import Views.OpenDeckPage;
import Views.RegisterPage;
import Views.SessionPlayer;

/**
 * Controller without MySQL connection
 * @author Mouiz_Ahmed
 *
 */
public class Controller {

//	private UserList userDatabase;
//	private DeckList deckDatabase;
	private JPanel main;
	private CardLayout card;
	private JDBC mysql_database;
	private User currentUser;
	private static String regexPattern = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@" 
	        + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
	
	public Controller(JPanel main, CardLayout card) {
//		userDatabase = new UserList();
//		deckDatabase = new DeckList(userDatabase);
		this.main = main;
		this.card = card;
		
		this.mysql_database = new JDBC();
		
	}
	
	
	public boolean createNewUser(String username, String email, String password, String confirmPassword) {
		
		if (!password.equals(confirmPassword)) {
			throw new IllegalArgumentException("Passwords do not match");
		} else if (password.length() < 8) {
			throw new IllegalArgumentException("Password must be at least 8 characters");
		} else if (!Pattern.compile(regexPattern).matcher(email).matches()) {
			throw new IllegalArgumentException("Invalid email");
		} else if (username.length() < 5) {
			throw new IllegalArgumentException("Username must be at least 5 characters");
		} else {
			try {
				currentUser = this.mysql_database.createNewUser(username, email, password, confirmPassword);
				this.landingPage();
				return true;
			} catch(Exception e) {
				e.printStackTrace();
				return false;
			}
		}
		
		
		
	}
	
	public boolean login(String username, String password) {
		currentUser = this.mysql_database.verifyUser(username, password);
		if (currentUser == null) {
			return false;
		} else {
			this.landingPage();
			return true;
		}
	}
	
	public void logout() {
		currentUser = null;
		this.welcomePage();
	}
	
	public void previous() {
		card.previous(main);
	}
	
	public void welcomePage() {
		System.out.println("WELCOME!");
		card.show(main, "welcomePage");
	}
	
	public void loginPage() {
		System.out.println("LOGIN!");
		card.show(main, "loginPage");
	}
	
	public void registerPage() {
		System.out.println("REGISTER!");
		card.show(main, "registerPage");
	}
	
	public void landingPage() {
		System.out.println("LANDINGPAGE!");
		card.show(main, "landingPage");
	}
	
	public void createDeckPage() {
		String deckID = UUID.randomUUID().toString();
		System.out.println("CREATE NEW DECK!");
		CreateDeckPage createDeckPage = new CreateDeckPage(this, deckID);
		System.out.println(main.getComponentCount());
		main.add(createDeckPage, "createDeckPage" + main.getComponentCount());
		int num = main.getComponentCount() - 1;
		card.show(main, "createDeckPage" + num);
	}
	
	public void browse() {
		System.out.println("Browse");
		BrowsePublicDeckPage browsePublicDeckPage = new BrowsePublicDeckPage(this);
		main.add(browsePublicDeckPage, "browsePublicDeckPage" + main.getComponentCount());
		int num = main.getComponentCount() - 1;
		card.show(main, "browsePublicDeckPage" + num);
	}
	
	public Flashcard createFlashcard(String question, String answer, String deckID) {
		Flashcard flashcard = new Flashcard(question, answer, currentUser.getUsername(), deckID);
		return flashcard;
	}
	
	public void createDeck(String deckTitle, ArrayList<Flashcard> flashcards, boolean publicDeck, String deckID) {
		Deck deck = mysql_database.createDeck(deckTitle, flashcards, publicDeck, currentUser, deckID);
		deckPage(deck);
	}
	
	public ArrayList<Deck> searchPublicDecks(String deckTitle) {
		return mysql_database.searchPublicDeckQuery(deckTitle);
	}
	
	public ArrayList<Deck> allPublicDecks() {
		ArrayList<Deck> publicDecks = mysql_database.publicDeckList();
		System.out.println(publicDecks.size());
		return publicDecks;
	}
	
	public void deckPage(Deck deck) {
		OpenDeckPage deckPage = new OpenDeckPage(deck, this);
		main.add(deckPage, "deckPage" + main.getComponentCount());
		int num = main.getComponentCount() - 1;
		card.show(main, "deckPage" + num);
	}
	
	public void session(ArrayList<Flashcard> flashcards) {
		SessionPlayer sessionPage = new SessionPlayer(flashcards, this);
		main.add(sessionPage, "sessionPage" + main.getComponentCount());
		int num = main.getComponentCount() - 1;
		card.show(main, "sessionPage" + num);
	}
	
}
