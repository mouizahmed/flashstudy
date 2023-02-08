package Models;

import java.util.ArrayList;

public class User {

	public String username;
	public String email; 
	public ArrayList<Deck> userDeckList;
	
	public User(String username, String email, ArrayList<Deck> userDeckList) {
		this.username = username;
		this.email = email;
		this.userDeckList = userDeckList;
	}
	
	public String getUsername() {
		return this.username;
	}
	
	public String getEmail() {
		return this.email;
	}
	
	public ArrayList<Deck> userDeckList() {
		return this.userDeckList;
	}
	
}
