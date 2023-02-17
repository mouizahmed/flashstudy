package Controller;


import java.awt.CardLayout;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;

import javax.swing.JPanel;

import Models.Deck;
import Models.DeckList;
import Models.Flashcard;
import Models.UserList;
import Views.BrowsePublicDeckPage;
import Views.CreateDeckPage;
import Views.LoginPage;
import Views.OpenDeckPage;
import Views.RegisterPage;

/**
 * Controller without MySQL connection
 * @author Mouiz_Ahmed
 *
 */
public class Controller {

	private UserList userDatabase;
	private DeckList deckDatabase;
	private JPanel main;
	private CardLayout card;
	
	public Controller(JPanel main, CardLayout card) {
		userDatabase = new UserList();
		deckDatabase = new DeckList(userDatabase);
		this.main = main;
		this.card = card;
	}
	
	
	public boolean createNewUser(String username, String email, String password, String confirmPassword) {
		try {
			userDatabase.addUser(username, email, password, confirmPassword);
			this.landingPage();
			return true;
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			return false;
			//e.printStackTrace();
		} catch (InvalidKeySpecException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			return false;
		}
		
		
	}
	
	public boolean login(String username, String password) {
		try {
			userDatabase.login(username, password);
			this.landingPage();
			return true;
		} catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	
	public void logout() {
		
		userDatabase.logout();
		this.welcomePage();
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
		System.out.println("CREATE NEW DECK!");
		CreateDeckPage createDeckPage = new CreateDeckPage(this);
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
	
	public Flashcard createFlashcard(String question, String answer) {
		Flashcard flashcard = new Flashcard(question, answer, userDatabase.getCurrentUser().getUsername());
		return flashcard;
	}
	
	public void createDeck(String deckTitle, ArrayList<Flashcard> flashcards, boolean publicDeck) {
		Deck deck = new Deck(deckTitle, flashcards, userDatabase.getCurrentUser().getUsername(), publicDeck);
		userDatabase.getCurrentUser().addDeck(deck);
		deckDatabase.addDeck(deck);
		
		System.out.println(deckDatabase.getAllPublicDecks());
		//System.out.println("U " + userDatabase.getCurrentUser().userDeckList().get(0).getPublicity());
	}
	
	public ArrayList<Deck> searchPublicDecks(String deckTitle) {
		return deckDatabase.searchPublicDeck(deckTitle);
	}
	
	public void deckPage(Deck deck) {
		OpenDeckPage deckPage = new OpenDeckPage(deck, this);
		main.add(deckPage, "deckPage" + main.getComponentCount());
		int num = main.getComponentCount() - 1;
		card.show(main, "deckPage" + num);
	}
	
}
