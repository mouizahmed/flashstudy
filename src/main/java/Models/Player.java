package Models;

public class Player {
	private String username;
	private Deck deck;
	private double score;
	
	public Player(Deck deck, String username, double score) {
		this.deck = deck;
		this.username = username;
		this.score = score;
	}
	
	public double getScore() {
		return this.score;
	}
	
	public String getUsername() {
		return this.username;
	}
	
	
}
