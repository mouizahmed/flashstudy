package Models;


import java.time.LocalDate;
import java.util.ArrayList;


public class User {

	private String username;
	private String email; 
	private ArrayList<Deck> userDeckList = new ArrayList<>();
	private String password;
	private LocalDate regDate;

	
	public User(String username, String email, String password, LocalDate regDate) {
		this.username = username;
		this.email = email;	
		this.password = password;
		this.regDate = regDate;
	}
	
	public String getUsername() {
		return this.username;
	}
	
	public String getPassword() {
		return this.password;
	}
	
	public String getEmail() {
		return this.email;
	}
	
	public ArrayList<Deck> getUserDeckList() {
		return this.userDeckList;
	}
	
	public void setUserDeckList(ArrayList<Deck> decks) {
		this.userDeckList.addAll(decks);
	}
	
	public void addDeck(Deck deck) {
		userDeckList.add(deck);
	}
	
	
}
