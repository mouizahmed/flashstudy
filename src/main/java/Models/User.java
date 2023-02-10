package Models;

import java.util.ArrayList;

public class User {

	private String username;
	private String email; 
	private ArrayList<Deck> userDeckList = new ArrayList<>();;
	private String password;
	private String confirmPassword;
	
	public User(String username, String email, String password, String confirmPassword) {
		this.username = username;
		this.email = email;
		this.password = password;
		this.confirmPassword = confirmPassword;
	}
	
	public String getUsername() {
		return this.username;
	}
	
	public String getEmail() {
		return this.email;
	}
	
	public boolean checkPassword(String password) {
		if (this.password.equals(password)) {
			return true;
		} else return false;
	}
	
	public boolean confirmPassword() {
		if (this.password != this.confirmPassword) {
			return false;
		} else return true;
	}
	
	public boolean passwordLength() {
		if (this.password.length() < 8) {
			return false;
		} else return true;
	}
	
	
	
	public ArrayList<Deck> userDeckList() {
		return this.userDeckList;
	}
	
	public void addDeck(Deck deck) {
		userDeckList.add(deck);
	}
	
	
}
