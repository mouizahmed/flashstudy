package Models;

import java.util.ArrayList;

public class Leaderboard {
	
	private ArrayList<Player> players = new ArrayList<>();
	private Deck deck;
	
	public Leaderboard(Deck deck) {
		this.deck = deck;
	}
	
	public void setPlayers(ArrayList<Player> players) {
		this.players.addAll(players);
	}
	
	public void addPlayer(Player player) {
		this.players.add(player);
	}
	
	public ArrayList<Player> getPlayers() {
		return this.players;
	}
	
	public String getDeckID() {
		return this.deck.getDeckID();
	}
	
	public Deck getDeck() {
		return this.deck;
	}

	
	
	public void removeAllPlayers() {
        players.clear();
    }

	public Player getPlayer(int i) {
		return this.getPlayer(i);
	}
}
