package Controller;


import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import Models.Deck;
import Models.DeckList;
import Models.Flashcard;
import Models.UserList;

/**
 * Controller without MySQL connection
 * @author Mouiz_Ahmed
 *
 */
public class Controller {

	private UserList userDatabase;
	private DeckList deckDatabase;
	
	public Controller() {
		userDatabase = new UserList();
		deckDatabase = new DeckList(userDatabase);
	}
	
	
	public boolean createNewUser(String username, String email, String password, String confirmPassword) {
		try {
			userDatabase.addUser(username, email, password, confirmPassword);
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
	
	
	
	
}
