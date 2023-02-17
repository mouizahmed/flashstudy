package Controller;


import java.awt.CardLayout;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import javax.swing.JPanel;

import Models.Deck;
import Models.DeckList;
import Models.Flashcard;
import Models.UserList;
import Views.LoginPage;
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
			//this.setVisible(false);
			//LoginPage loginPage = new LoginPage(main, card);
			//main.add(loginPage, "login");
			card.show(main, "loginPage");
			
			//loginPage.setVisible(true);
		
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
		card.show(main, "createDeckPage");
	}
	
//	public void registerPage() {
//		
//			System.out.println("REGISTER!");
//			//this.setVisible(false);
//			RegisterPage registerPage = new RegisterPage(main, card);
//			main.add(registerPage, "register");
//			card.show(main, "register");
//		
//	}
	
	
	
	
}
